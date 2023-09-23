package center.unit.letter.application.letter;

import center.unit.letter.domain.letter.Letter;
import center.unit.letter.domain.letter.type.LetterType;
import center.unit.letter.domain.letter.type.MediumType;
import center.unit.letter.domain.user.User;
import center.unit.letter.domain.user.service.UserFacade;
import center.unit.letter.infrastructure.cyworld.CyworldService;
import center.unit.letter.infrastructure.persistence.letter.LetterRepository;
import center.unit.letter.presentation.letter.dto.request.SendLetterRequest;
import center.unit.letter.presentation.letter.dto.response.SendLetterResponse;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SendLetterService {

    private final CyworldService cyworldService;
    private final UserFacade userFacade;
    private final LetterRepository letterRepository;

    public SendLetterResponse execute(
        User from,
        SendLetterRequest request
    ) {
        Letter letter = letterRepository.save(
            new Letter(
                convertText(request.getText(), request.getType()),
                getRandomMediumType(),
                request.getType(),
                userFacade.getUser(request.getTargetPhoneNumber()),
                from
            )
        );

        return new SendLetterResponse(letter);
    }

    private MediumType getRandomMediumType() {
        MediumType[] values = MediumType.values();
        return values[new Random().nextInt(values.length)];
    }

    private String convertText(String text, LetterType type) {
        return type.equals(LetterType.CODE) ?
            cyworldService.execute(text) :
            text;
    }
}

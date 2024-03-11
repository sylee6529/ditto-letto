package center.unit.letter.application.letter;

import center.unit.letter.domain.user.User;
import center.unit.letter.domain.user.service.UserFacade;
import center.unit.letter.infrastructure.persistence.letter.LetterRepository;
import center.unit.letter.presentation.letter.dto.response.LetterCountResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class QueryLetterCountService {

    private final UserFacade userFacade;
    private final LetterRepository letterRepository;


    public LetterCountResponse execute(User user, String phoneNumber) {
        User target = userFacade.getUser(phoneNumber);

        return new LetterCountResponse(
                letterRepository.countByFromAndTo(user.getId(), target.getId()),
                letterRepository.countByFromAndTo(target.getId(), user.getId())
        );
    }
}

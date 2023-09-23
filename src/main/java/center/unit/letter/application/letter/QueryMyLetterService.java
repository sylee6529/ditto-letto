package center.unit.letter.application.letter;

import center.unit.letter.domain.letter.type.DirectionType;
import center.unit.letter.domain.letter.type.LetterType;
import center.unit.letter.domain.letter.type.MediumType;
import center.unit.letter.domain.user.User;
import center.unit.letter.infrastructure.persistence.letter.LetterRepository;
import center.unit.letter.presentation.letter.dto.response.MyLetterListResponse;
import center.unit.letter.presentation.letter.dto.response.MyLetterResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class QueryMyLetterService {
    private final LetterRepository letterRepository;


    public MyLetterListResponse execute(User user) {
        List<Object[]> results = letterRepository.findAllByUserId(user.getId());

        List<MyLetterResponse> inBoxLetters = new ArrayList<>();
        List<MyLetterResponse> outBoxLetters = new ArrayList<>();

        for (Object[] result: results) {
            String phoneNumber = (String) result[0];
            String rawLetterType = (String) result[1];
            String rawMediumType = (String) result[2];
            int progressLevel = ((BigDecimal) result[3]).intValue();
            String previewText = (String) result[4];
            String rawDirectionType = (String) result[5];
            boolean arrived = (boolean) result[6];

            DirectionType directionType = DirectionType.valueOf(rawDirectionType);
            LetterType letterType = LetterType.valueOf(rawLetterType);
            MediumType mediumType = MediumType.valueOf(rawMediumType);

            MyLetterResponse myLetter = new MyLetterResponse(phoneNumber, mediumType, letterType, progressLevel, previewText, directionType, arrived);

            if(directionType.equals(DirectionType.OUT)) {
                outBoxLetters.add(myLetter);
            }
            else {
                inBoxLetters.add(myLetter);
            }
        }

        return new MyLetterListResponse(inBoxLetters, outBoxLetters);

    }
}

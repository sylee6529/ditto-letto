package center.unit.letter.application.letter;

import center.unit.letter.domain.letter.type.DirectionType;
import center.unit.letter.domain.letter.type.LetterType;
import center.unit.letter.domain.letter.type.MediumType;
import center.unit.letter.domain.user.User;
import center.unit.letter.infrastructure.persistence.letter.LetterRepository;
import center.unit.letter.presentation.letter.dto.response.MyLetterResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class QueryMyLetterService {
    private final LetterRepository letterRepository;


    public List<MyLetterResponse> execute(User user) {
        List<Object[]> results = letterRepository.findAllByUserId(user.getId());

        List<MyLetterResponse> myLetters= new ArrayList<>();

        for (Object[] result: results) {
            String phoneNumber = (String) result[0];
            String rawLetterType = (String) result[1];
            String rawMediumType = (String) result[2];
            int progressLevel = ((BigDecimal) result[3]).intValue();
            String previewText = (String) result[4];
            String rawDirectionType = (String) result[5];
            Timestamp timestamp = (Timestamp) result[6];

            DirectionType directionType = DirectionType.valueOf(rawDirectionType);
            LetterType letterType = LetterType.valueOf(rawLetterType);
            MediumType mediumType = MediumType.valueOf(rawMediumType);

            LocalDateTime arriveAt = timestamp.toLocalDateTime();

            MyLetterResponse myLetter = new MyLetterResponse(phoneNumber, mediumType, letterType, progressLevel, previewText, directionType, arriveAt);
            myLetters.add(myLetter);
        }

        return myLetters;

    }
}

package center.unit.letter.application.letter;

import center.unit.letter.domain.letter.type.DirectionType;
import center.unit.letter.domain.user.User;
import center.unit.letter.domain.user.service.UserFacade;
import center.unit.letter.infrastructure.persistence.letter.LetterRepository;
import center.unit.letter.presentation.letter.dto.response.LetterCountResponse;
import center.unit.letter.presentation.letter.dto.response.LetterSimpleResponse;
import center.unit.letter.presentation.letter.dto.response.MyLetterResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class QueryMyLetterService {
    private final UserFacade userFacade;
    private final LetterRepository letterRepository;


    public List<MyLetterResponse> execute(User user) {
        List<Object[]> results = letterRepository.findAllByUserId(user.getId());

        List<MyLetterResponse> myLetters= new ArrayList<>();

        for (Object[] result: results) {
            String phoneNumber= (String) result[0];
            String type= (String) result[1];
            String mediumType = (String) result[2];
            int progressLevel = ((BigDecimal) result[3]).intValue();
            String previewText = (String) result[4];
            String direction = (String) result[5];
            Timestamp timestamp = (Timestamp) result[6];

            DirectionType directionType;
            if(direction.equals("IN")) {
                directionType = DirectionType.IN;
            }
            else {
                directionType = DirectionType.OUT;
            }

            LocalDateTime arriveAt = timestamp.toLocalDateTime();

            MyLetterResponse myLetter = new MyLetterResponse(phoneNumber,type,mediumType,progressLevel, previewText, directionType, arriveAt);
            myLetters.add(myLetter);
        }

        return myLetters;

    }
}

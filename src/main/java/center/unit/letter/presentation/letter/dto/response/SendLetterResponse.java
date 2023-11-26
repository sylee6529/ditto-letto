package center.unit.letter.presentation.letter.dto.response;

import center.unit.letter.domain.letter.Letter;
import center.unit.letter.domain.letter.type.MediumType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SendLetterResponse {

    private String arriveAt;
    private MediumType mediumType;
    private String createdAt;
    private String imgURL;
}

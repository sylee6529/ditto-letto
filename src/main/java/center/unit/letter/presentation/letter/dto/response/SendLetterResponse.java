package center.unit.letter.presentation.letter.dto.response;

import center.unit.letter.domain.letter.Letter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SendLetterResponse {

    private LocalDateTime arriveAt;

    public SendLetterResponse(Letter letter) {
        this.arriveAt = letter.getArriveAt();
    }
}

package center.unit.letter.presentation.letter.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LetterCountResponse {

    private int toCount;
    private int fromCount;
}

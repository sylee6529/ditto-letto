package center.unit.letter.presentation.letter.dto.response;

import center.unit.letter.presentation.letter.dto.MyLetterVO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class MyLetterListResponse {
    private List<MyLetterVO> myLetters;
}

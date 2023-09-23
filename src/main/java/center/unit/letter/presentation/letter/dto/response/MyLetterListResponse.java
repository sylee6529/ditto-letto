package center.unit.letter.presentation.letter.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class MyLetterListResponse {
    private List<MyLetterResponse> inBoxLetters;
    private List<MyLetterResponse> outBoxLetters;
}

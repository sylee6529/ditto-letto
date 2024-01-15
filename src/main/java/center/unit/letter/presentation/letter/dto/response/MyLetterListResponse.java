package center.unit.letter.presentation.letter.dto.response;

import center.unit.letter.presentation.letter.dto.MyLetterDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class MyLetterListResponse {
    private List<MyLetterDto> myLetters;

    public void addMyLetter(MyLetterDto myLetterDto) {
        myLetters.add(myLetterDto);
    }

    public MyLetterListResponse() {
        this.myLetters = new ArrayList<>();
    }
}

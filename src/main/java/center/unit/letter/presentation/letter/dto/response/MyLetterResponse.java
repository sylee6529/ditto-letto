package center.unit.letter.presentation.letter.dto.response;

import center.unit.letter.domain.letter.type.DirectionType;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class MyLetterResponse {

    private String phoneNumber;
    private String mediumType;
    private String type;
    private int progressLevel;
    private String previewText;
    private DirectionType direction;
    private LocalDate arrivedAt;
}

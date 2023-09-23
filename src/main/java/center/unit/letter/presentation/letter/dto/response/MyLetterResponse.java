package center.unit.letter.presentation.letter.dto.response;

import center.unit.letter.domain.letter.type.DirectionType;
import center.unit.letter.domain.letter.type.LetterType;
import center.unit.letter.domain.letter.type.MediumType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class MyLetterResponse {

    private String phoneNumber;
    private MediumType mediumType;
    private LetterType type;
    private int progressLevel;
    private String previewText;
    private DirectionType direction;
    private LocalDateTime arrivedAt;
}

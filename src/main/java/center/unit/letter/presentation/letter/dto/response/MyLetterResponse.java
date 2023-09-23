package center.unit.letter.presentation.letter.dto.response;

import center.unit.letter.domain.letter.type.DirectionType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class MyLetterResponse {

    private String phoneNumber;
    private String mediumType;
    private String type;
    private int progressLevel;
    private String previewText;
    private DirectionType direction;
    private LocalDateTime arrivedAt;
}

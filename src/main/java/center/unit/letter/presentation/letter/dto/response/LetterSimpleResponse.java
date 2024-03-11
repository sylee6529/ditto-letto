package center.unit.letter.presentation.letter.dto.response;

import center.unit.letter.domain.letter.Letter;
import center.unit.letter.domain.letter.type.LetterType;
import center.unit.letter.domain.letter.type.MediumType;
import lombok.Value;

import java.time.LocalDate;

@Value
public class LetterSimpleResponse {

    Long id;
    LocalDate sentAt;
    LetterType type;
    MediumType mediumType;
    String text;

    public LetterSimpleResponse(Letter letter) {
        this.id = letter.getId();
        this.sentAt = letter.getCreatedAt().toLocalDate();
        this.type = letter.getType();
        this.mediumType = letter.getMediumType();
        this.text = letter.getText();
    }
}

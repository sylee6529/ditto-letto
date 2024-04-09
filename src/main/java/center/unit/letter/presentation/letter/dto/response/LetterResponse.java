package center.unit.letter.presentation.letter.dto.response;

import center.unit.letter.domain.contact.Contact;
import center.unit.letter.domain.letter.Letter;
import center.unit.letter.domain.letter.type.LetterType;
import center.unit.letter.domain.letter.type.MediumType;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class LetterResponse {

    private Long id;
    private String text;
    private LocalDate sentAt;
    private MediumType mediumType;
    private LetterType type;
    private Integer toCount;
    private Integer fromCount;
    private String phoneNumber;
    private Contact contact;

    public LetterResponse(Letter letter, String phoneNumber, Contact contact, Integer toCount, Integer fromCount) {
        this.id = letter.getId();
        this.text = letter.getText();
        this.sentAt = letter.getCreatedAt().toLocalDate();
        this.phoneNumber = phoneNumber;
        this.contact = contact;
        this.mediumType = letter.getMediumType();
        this.type = letter.getType();
        this.toCount = toCount;
        this.fromCount = fromCount;
    }
}

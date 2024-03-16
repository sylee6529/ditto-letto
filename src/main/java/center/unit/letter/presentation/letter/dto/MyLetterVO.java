package center.unit.letter.presentation.letter.dto;

import center.unit.letter.domain.contact.Contact;
import center.unit.letter.domain.letter.type.MediumType;
import center.unit.letter.domain.letter.type.MyLetterType;
import center.unit.letter.presentation.contact.dto.ContactDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MyLetterVO {
    private Long id;
    private String myLetterType;
    private MediumType mediumType;
    private LocalDateTime createdAt;
    private LocalDateTime arriveAt;
    private ContactDto contact;
    private String previewText;
    private String phoneNumber;

    public MyLetterVO(Long id, String myLetterType, MediumType mediumType, LocalDateTime createdAt, LocalDateTime arriveAt, Contact contact, String previewText, String phoneNumber) {
        this.id = id;
        this.myLetterType = myLetterType;
        this.mediumType = mediumType;
        this.createdAt = createdAt;
        this.arriveAt = arriveAt;
        this.contact = (contact != null) ? new ContactDto(contact): null;
        this.previewText = previewText;
        this.phoneNumber = phoneNumber;
    }

    public MyLetterVO(Long id, String myLetterType, MediumType mediumType, LocalDateTime createdAt, LocalDateTime arriveAt, String previewText) {
        this.id = id;
        this.myLetterType = myLetterType;
        this.mediumType = mediumType;
        this.createdAt = createdAt;
        this.arriveAt = arriveAt;
        this.previewText = previewText;
    }
}

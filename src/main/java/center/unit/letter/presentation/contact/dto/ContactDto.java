package center.unit.letter.presentation.contact.dto;

import center.unit.letter.domain.contact.Contact;
import center.unit.letter.domain.contact.type.Character;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ContactDto {
    private Long id;

    private String phoneNumber;

    private String name;

    private Character character;

    public ContactDto(Contact contact) {
        this.id = contact.getId();
        this.phoneNumber = contact.getPhoneNumber();
        this.name = contact.getName();
        this.character = contact.getCharacter();
    }
}

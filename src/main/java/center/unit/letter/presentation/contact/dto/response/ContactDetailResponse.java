package center.unit.letter.presentation.contact.dto.response;

import center.unit.letter.domain.contact.Contact;
import center.unit.letter.domain.contact.type.Character;
import lombok.Value;

@Value
public class ContactDetailResponse {

    Long id;
    String name;
    Character character;
    String phoneNumber;

    public ContactDetailResponse(Contact contact) {
        this.id = contact.getId();
        this.name = contact.getName();
        this.character = contact.getCharacter();
        this.phoneNumber = contact.getPhoneNumber();
    }
}

package center.unit.letter.presentation.contact.dto.response;

import center.unit.letter.domain.contact.Contact;
import center.unit.letter.domain.contact.type.Character;
import lombok.Value;

@Value
public class ContactResponse {

    Long id;
    String name;
    Character character;
    String phoneNumber;

    public ContactResponse(Contact contact) {
        this.id = contact.getId();
        this.name = contact.getName();
        this.character = contact.getCharacter();
        this.phoneNumber = contact.getPhoneNumber();
    }
}

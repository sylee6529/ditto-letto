package center.unit.letter.domain.contact.exception;

import center.unit.letter.domain.contact.exception.base.ContactErrorCode;
import center.unit.letter.domain.contact.exception.base.ContactException;

public class ContactNotFoundException extends ContactException {

    public ContactNotFoundException() {
        super(ContactErrorCode.NOT_FOUND);
    }
}

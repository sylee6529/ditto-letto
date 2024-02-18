package center.unit.letter.domain.contact.exception;

import center.unit.letter.domain.contact.exception.base.ContactErrorCode;
import center.unit.letter.domain.contact.exception.base.ContactException;

public class ContactAlreadySavedException extends ContactException {

    public ContactAlreadySavedException() {
        super(ContactErrorCode.ALREADY_SAVED);
    }
}

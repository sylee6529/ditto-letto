package center.unit.letter.domain.contact.exception;

public class ContactAlreadySavedException extends ContactException{

    public ContactAlreadySavedException() {
        super(ContactErrorCode.ALREADY_SAVED);
    }
}

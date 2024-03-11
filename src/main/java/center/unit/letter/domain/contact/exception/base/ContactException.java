package center.unit.letter.domain.contact.exception.base;

import center.unit.letter.shared.error.BaseException;

public abstract class ContactException extends BaseException {
    protected ContactException(ContactErrorCode errorCode) {
        super(errorCode);
    }
}

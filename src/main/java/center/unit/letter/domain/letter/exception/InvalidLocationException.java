package center.unit.letter.domain.letter.exception;

import center.unit.letter.domain.letter.exception.base.LetterErrorCode;
import center.unit.letter.domain.letter.exception.base.LetterException;

public class InvalidLocationException extends LetterException {
    public InvalidLocationException() {
        super(LetterErrorCode.INVALID_LOCATION);
    }
}

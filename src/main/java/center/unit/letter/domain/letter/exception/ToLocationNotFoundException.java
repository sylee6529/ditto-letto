package center.unit.letter.domain.letter.exception;

import center.unit.letter.domain.letter.exception.base.LetterErrorCode;
import center.unit.letter.domain.letter.exception.base.LetterException;

public class ToLocationNotFoundException extends LetterException {
    public ToLocationNotFoundException() {
        super(LetterErrorCode.TO_LOCATION_NOT_FOUND);
    }
}

package center.unit.letter.domain.letter.exception;

import center.unit.letter.domain.letter.exception.base.LetterErrorCode;
import center.unit.letter.domain.letter.exception.base.LetterException;

public class FromLocationNotFoundException extends LetterException {
    public FromLocationNotFoundException() {
        super(LetterErrorCode.FROM_LOCATION_NOT_FOUND);
    }
}

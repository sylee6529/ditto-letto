package center.unit.letter.domain.letter.exception;

import center.unit.letter.domain.letter.exception.base.LetterErrorCode;
import center.unit.letter.domain.letter.exception.base.LetterException;

public class LetterNotFoundException extends LetterException {
    public LetterNotFoundException() {
        super(LetterErrorCode.LETTER_NOT_FOUND);
    }
}

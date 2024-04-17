package center.unit.letter.domain.letter.exception;

import center.unit.letter.domain.letter.exception.base.LetterErrorCode;
import center.unit.letter.domain.letter.exception.base.LetterException;

public class LetterAccessDeniedException extends LetterException {
    public LetterAccessDeniedException() {
        super(LetterErrorCode.LETTER_ACCESS_DENIED);
    }
}

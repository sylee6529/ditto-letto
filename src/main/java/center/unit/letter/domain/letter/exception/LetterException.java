package center.unit.letter.domain.letter.exception;

import center.unit.letter.shared.error.BaseException;

public abstract class LetterException extends BaseException {
    protected LetterException(LetterErrorCode errorCode) {
        super(errorCode);
    }
}

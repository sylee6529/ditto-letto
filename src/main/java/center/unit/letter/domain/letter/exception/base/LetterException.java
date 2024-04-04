package center.unit.letter.domain.letter.exception.base;

import center.unit.letter.shared.error.BaseException;

public class LetterException extends BaseException {
    public LetterException(LetterErrorCode errorCode) {
        super(errorCode);
    }
}

package center.unit.letter.domain.user.exception.base;

import center.unit.letter.domain.letter.exception.LetterErrorCode;
import center.unit.letter.shared.error.BaseException;

public class UserException extends BaseException {
    public UserException(UserErrorCode errorCode) {
        super(errorCode);
    }
}

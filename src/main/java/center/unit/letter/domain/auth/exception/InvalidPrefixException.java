package center.unit.letter.domain.auth.exception;

import center.unit.letter.domain.auth.exception.base.AuthErrorCode;
import center.unit.letter.domain.auth.exception.base.AuthException;
import center.unit.letter.shared.error.BaseException;

public class InvalidPrefixException extends AuthException {
    public InvalidPrefixException() {
        super(AuthErrorCode.INVALID_PREFIX);
    }
}

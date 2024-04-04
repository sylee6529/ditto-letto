package center.unit.letter.domain.auth.exception;

import center.unit.letter.domain.auth.exception.base.AuthErrorCode;
import center.unit.letter.domain.auth.exception.base.AuthException;

public class InvalidTokenException extends AuthException {
    public InvalidTokenException() {
        super(AuthErrorCode.INVALID_TOKEN);
    }
}

package center.unit.letter.domain.auth.exception;

import center.unit.letter.domain.auth.exception.base.AuthErrorCode;
import center.unit.letter.domain.auth.exception.base.AuthException;

public class ExpiredTokenException extends AuthException {
    public ExpiredTokenException() {
        super(AuthErrorCode.EXPIRED_TOKEN);
    }
}

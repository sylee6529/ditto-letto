package center.unit.letter.domain.auth.exception.base;

import center.unit.letter.shared.error.BaseException;

public class AuthException extends BaseException {
    public AuthException(AuthErrorCode errorCode) {
        super(errorCode);
    }
}

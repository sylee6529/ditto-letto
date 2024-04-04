package center.unit.letter.domain.auth.exception;

import center.unit.letter.domain.auth.exception.base.AuthErrorCode;
import center.unit.letter.domain.auth.exception.base.AuthException;

public class TokenNotFoundException extends AuthException {
    public TokenNotFoundException() {
        super(AuthErrorCode.TOKEN_NOT_FOUND);
    }
}

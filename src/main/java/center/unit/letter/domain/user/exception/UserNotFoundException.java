package center.unit.letter.domain.user.exception;

import center.unit.letter.domain.user.exception.base.UserErrorCode;
import center.unit.letter.domain.user.exception.base.UserException;

public class UserNotFoundException extends UserException {
        public UserNotFoundException() {
            super(UserErrorCode.USER_NOT_FOUND);
        }
}

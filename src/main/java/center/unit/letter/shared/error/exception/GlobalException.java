package center.unit.letter.shared.error.exception;

import center.unit.letter.shared.error.BaseException;

public abstract class GlobalException extends BaseException {
    protected GlobalException(GlobalErrorCode errorCode) {
        super(errorCode);
    }
}

package center.unit.letter.domain.letter.exception;

import center.unit.letter.shared.error.BaseException;

public abstract class LetterException extends BaseException {
    protected LetterException(LetterErrorCode errorCode) {
        super(errorCode);
    }

    public static class ContentNotFoundException extends LetterException {
        public ContentNotFoundException() {
            super(LetterErrorCode.CONTENT_NOT_FOUND);
        }
    }

    public static class ContentIsOverLimit extends LetterException {
        public ContentIsOverLimit() {
            super(LetterErrorCode.CONTENT_IS_OVER_LIMIT);
        }
    }

    public static class PhoneNumberNotFoundException extends LetterException {
        public PhoneNumberNotFoundException() {
            super(LetterErrorCode.PHONE_NUMBER_NOT_FOUND);
        }
    }
}

package center.unit.letter.domain.medium.exception;

public class MediumException extends RuntimeException {
    public MediumException(MediumErrorCode errorCode) {
        super(errorCode.getMessage());
    }
}

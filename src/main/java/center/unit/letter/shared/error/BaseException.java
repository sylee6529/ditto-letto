package center.unit.letter.shared.error;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class BaseException extends RuntimeException {

    private final String code;
    private final HttpStatus httpStatus;

    public BaseException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.httpStatus = errorCode.getStatus();
        this.code = errorCode.getCode();
    }

}

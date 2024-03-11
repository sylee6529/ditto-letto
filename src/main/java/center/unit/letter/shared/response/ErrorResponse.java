package center.unit.letter.shared.response;

import center.unit.letter.shared.error.ErrorCode;
import lombok.Getter;

import java.util.Map;

@Getter
public class ErrorResponse extends CommonResponse {

    private Map<String, String> error;

    public ErrorResponse(ErrorCode errorCode) {
        super(errorCode.getCode(), errorCode.getMessage());
    }

    public ErrorResponse(ErrorCode errorCode, String message) {
        super(errorCode.getCode(), message);
    }

    public ErrorResponse(ErrorCode errorCode, Map<String, String> data) {
        super(errorCode.getCode(), errorCode.getMessage());
        this.error = data;
    }
}


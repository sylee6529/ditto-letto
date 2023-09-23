package center.unit.letter.shared.response;

import center.unit.letter.shared.error.BaseException;
import center.unit.letter.shared.error.ErrorCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
public class ErrorResponse extends CommonResponse {

    public ErrorResponse(ErrorCode errorCode) {
        super(errorCode.getCode(), errorCode.getMessage());
    }

    public ErrorResponse(String code, String message) {
        super(code, message);
    }

}


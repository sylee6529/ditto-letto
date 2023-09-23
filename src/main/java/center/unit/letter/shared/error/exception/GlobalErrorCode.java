package center.unit.letter.shared.error.exception;

import center.unit.letter.shared.error.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum GlobalErrorCode implements ErrorCode {
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "", "잘못된 요청입니다."),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "", "서버에서 오류가 발생했습니다."),
    CONFLICT(HttpStatus.CONFLICT, "", "");

    private final HttpStatus status;
    private final String code;
    private final String message;
}

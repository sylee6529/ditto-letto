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
    FORBIDDEN(HttpStatus.FORBIDDEN, "", "권한이 없습니다."),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED,"", "인증 정보가 존재하지 않습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}

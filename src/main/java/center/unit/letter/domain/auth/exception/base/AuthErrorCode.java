package center.unit.letter.domain.auth.exception.base;

import center.unit.letter.shared.error.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum AuthErrorCode implements ErrorCode {
    INVALID_TOKEN(HttpStatus.UNAUTHORIZED, "", "유효하지 않은 토큰입니다."),
    EXPIRED_TOKEN(HttpStatus.UNAUTHORIZED, "", "로그인 시간이 만료되었습니다."),
    INVALID_PREFIX(HttpStatus.UNAUTHORIZED, "", "토큰의 접두사가 적절하지 않습니다."),
    TOKEN_NOT_FOUND(HttpStatus.UNAUTHORIZED, "", "토큰 정보가 필요합니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}

package center.unit.letter.domain.user.exception.base;

import center.unit.letter.shared.error.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum UserErrorCode implements ErrorCode {

    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "", "유저를 찾을 수 없습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}

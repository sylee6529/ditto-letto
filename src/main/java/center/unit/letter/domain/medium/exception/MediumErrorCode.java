package center.unit.letter.domain.medium.exception;

import center.unit.letter.shared.error.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum MediumErrorCode implements ErrorCode {
    INVALID_MEDIUM_NAME(HttpStatus.BAD_REQUEST, "", "올바르지 않은 탈 것 이름입니다."),
    MEDIUM_NOT_FOUND(HttpStatus.NOT_FOUND, "", "적절한 탈 것을 찾을 수 없습니다."),
    EVENT_MEDIUM_NOT_FOUND(HttpStatus.NOT_FOUND, "", "적절한 이벤트 탈 것을 찾을 수 없습니다.");


    private final HttpStatus status;
    private final String code;
    private final String message;
}

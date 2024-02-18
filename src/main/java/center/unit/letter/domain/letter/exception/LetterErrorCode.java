package center.unit.letter.domain.letter.exception;

import center.unit.letter.shared.error.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum LetterErrorCode implements ErrorCode {
    FROM_LOCATION_NOT_FOUND(HttpStatus.CONFLICT, "", "출발 위치 정보를 찾을 수 없습니다."),
    TO_LOCATION_NOT_FOUND(HttpStatus.CONFLICT, "", "도착 위치 정보를 찾을 수 없습니다."),
    INVALID_LOCATION_VALUE(HttpStatus.BAD_REQUEST, "", "유효하지 않은 경도 혹은 위도 값입니다."),
    INVALID_LOCATION(HttpStatus.BAD_REQUEST, "", "유효하지 않은 위치 정보입니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}

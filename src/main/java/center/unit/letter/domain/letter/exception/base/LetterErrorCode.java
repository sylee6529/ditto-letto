package center.unit.letter.domain.letter.exception.base;

import center.unit.letter.shared.error.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum LetterErrorCode implements ErrorCode {
    FROM_LOCATION_NOT_FOUND(HttpStatus.NOT_FOUND, "", "출발 위치 정보를 찾을 수 없습니다."),
    TO_LOCATION_NOT_FOUND(HttpStatus.NOT_FOUND, "", "도착 위치 정보를 찾을 수 없습니다."),
    INVALID_LOCATION(HttpStatus.BAD_REQUEST, "", "유효하지 않은 위치 값입니다."),
    LETTER_NOT_FOUND(HttpStatus.NOT_FOUND, "", "편지를 찾을 수 없습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}

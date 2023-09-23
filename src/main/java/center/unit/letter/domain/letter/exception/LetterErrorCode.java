package center.unit.letter.domain.letter.exception;

import center.unit.letter.shared.error.ErrorCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum LetterErrorCode implements ErrorCode {

    CONTENT_NOT_FOUND(HttpStatus.NOT_FOUND, "L001", "편지 내용이 없습니다."),
    CONTENT_IS_OVER_LIMIT(HttpStatus.NOT_FOUND, "L002", "편지 내용이 144자를 초과했습니다."),
    PHONE_NUMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "L003", "휴대폰 번호가 없습니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;

    LetterErrorCode(HttpStatus status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }
}

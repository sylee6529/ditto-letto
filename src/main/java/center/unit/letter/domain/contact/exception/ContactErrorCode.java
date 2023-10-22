package center.unit.letter.domain.contact.exception;

import center.unit.letter.shared.error.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ContactErrorCode implements ErrorCode {
    ALREADY_SAVED(HttpStatus.CONFLICT, "", "이미 저장한 번호입니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}

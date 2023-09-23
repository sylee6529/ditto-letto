package center.unit.letter.domain.letter.exception;

import center.unit.letter.shared.error.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum LetterErrorCode implements ErrorCode {
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}

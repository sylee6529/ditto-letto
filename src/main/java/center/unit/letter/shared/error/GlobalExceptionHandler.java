package center.unit.letter.shared.error;

import center.unit.letter.shared.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BaseException.class)
    protected ResponseEntity<ErrorResponse> handleException(final BaseException e) {

        String code = e.getCode();
        String message = e.getMessage();
        HttpStatus status = e.getHttpStatus();

        log.error("code = {}, message = {}", code, message);
        final ErrorResponse response = new ErrorResponse(code, message);

        return ResponseEntity.status(status).body(response);
    }

}

package boradexample.myboard.myboard.exception;

import groovy.util.logging.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(EmailDuplicateException.class)
    public ResponseEntity<ErrorResponse> handleEmailDuplicateException(EmailDuplicateException ex){
        ErrorResponse response = new ErrorResponse(ex.getErrorCode());
        return new ResponseEntity<>(response, HttpStatus.valueOf(ex.getErrorCode().getStatus()));
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception ex){
        ErrorResponse response = new ErrorResponse(StatusCode.INTER_SERVER_ERROR);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

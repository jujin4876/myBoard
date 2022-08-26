package boradexample.myboard.myboard.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(value = Exception.class)
    public ResponseEntity<String> handlerArgumentException(Exception e) {
        String result =  e.getMessage();
        ResponseEntity<String> responseEntity = new ResponseEntity<String>(result, HttpStatus.INTERNAL_SERVER_ERROR); // 500 반환
        e.printStackTrace();
        return responseEntity;

    }
}

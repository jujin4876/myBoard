package boradexample.myboard.myboard.exception;

import lombok.Getter;

@Getter
public class EmailDuplicateException extends RuntimeException{

    private StatusCode errorCode;

    public EmailDuplicateException(String message, StatusCode errorCode){
        super(message);
        this.errorCode = errorCode;
    }
}

package boradexample.myboard.myboard.exception;

import lombok.Data;

@Data
public class ErrorResponse {

    private int status;
    private String message;
    private String code;

    public ErrorResponse(StatusCode errorCode){
        this.status = errorCode.getStatus();
        this.message = errorCode.getMessage();
        this.code = errorCode.getErrorCode();
    }

}

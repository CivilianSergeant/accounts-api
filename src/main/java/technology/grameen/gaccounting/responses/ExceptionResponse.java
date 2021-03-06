package technology.grameen.gaccounting.responses;

import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.List;

public class ExceptionResponse implements IResponse {

    private HttpStatus status;
    private String message;
    private List<String> errors;

    public ExceptionResponse(HttpStatus status, String message, List<String> errors) {
        this.status = status;
        this.message = message;
        this.errors = errors;
    }

    public ExceptionResponse(HttpStatus status, String message, String... error) {
        this.status = status;
        this.message = message;
        this.errors = Arrays.asList(error);
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }
}

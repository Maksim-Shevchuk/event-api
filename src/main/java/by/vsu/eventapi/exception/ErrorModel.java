package by.vsu.eventapi.exception;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ErrorModel {

    private String message;
    private String statusCodeName;
    private int statusCode;
    @JsonIgnore
    private HttpStatus httpStatus;

    public ErrorModel(String message, HttpStatus statusCode) {
        this.message = message;
        this.httpStatus = statusCode;
        this.statusCodeName = statusCode.name();
        this.statusCode = statusCode.value();
    }

    public static ErrorModel generate(RuntimeException exception) {
        return new ErrorModel(
                exception.getMessage(),
                exception.getClass().getAnnotation(ResponseStatus.class).value()
        );
    }
}

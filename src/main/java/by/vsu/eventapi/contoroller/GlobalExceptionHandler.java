package by.vsu.eventapi.contoroller;

import by.vsu.eventapi.exception.ErrorModel;
import by.vsu.eventapi.exception.EventNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EventNotFoundException.class)
    public ResponseEntity<ErrorModel> handleNotFoundException(EventNotFoundException e) {
        ErrorModel errorModel = ErrorModel.generate(e);
        return new ResponseEntity<>(errorModel, errorModel.getHttpStatus());
    }
}
package by.vsu.eventapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class EventNotFoundException extends RuntimeException {

    private static final String DEFAULT_NOT_FOUND_EXCEPTION_MESSAGE = "There is no event with id %s";

    public EventNotFoundException(String message) {
        super(message);

    }

    public static void call(String pattern, Object... args) throws EventNotFoundException {
        throw new EventNotFoundException(String.format(pattern, args));
    }

    public static void call(Object id) throws EventNotFoundException {
        call(DEFAULT_NOT_FOUND_EXCEPTION_MESSAGE, id);
    }

}

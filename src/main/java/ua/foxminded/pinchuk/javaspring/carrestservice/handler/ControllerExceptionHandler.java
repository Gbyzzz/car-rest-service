package ua.foxminded.pinchuk.javaspring.carrestservice.handler;

import org.springdoc.api.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ua.foxminded.pinchuk.javaspring.carrestservice.service.exception.ItemNotFoundException;

@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value
            = {ItemNotFoundException.class})
    public ResponseEntity<ErrorMessage> itemNotFoundException(Exception ex, WebRequest request) {
        ErrorMessage message = new ErrorMessage(ex.getMessage());

        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value
            = {RuntimeException.class})
    public ResponseEntity<ErrorMessage> internalRuntimeError(Exception ex, WebRequest request) {
        ErrorMessage message = new ErrorMessage(ex.getMessage());

        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }
}

package com.example.employeecontrol.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;


@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(ResourceNdException.class)
    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    public ResponseEntity<?> resourceNotFoundException(ResourceNdException ex, WebRequest request) {
        ErrorMessage message = new ErrorMessage(
                403,
                new Date(),
                ex.getResourcename(),
                request.getDescription(false));

        return ResponseEntity.ok(message);
    }


}

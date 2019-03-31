package com.sample.opentrace.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Optional;

//@ControllerAdvice
public class ExceptionController {


   /* private ResponseEntity < String > error(final Exception exception, final HttpStatus httpStatus, final String logRef) {
        final String message = Optional.of(exception.getMessage()).orElse(exception.getClass().getSimpleName());
        return new ResponseEntity< >("Error Occurred", httpStatus);
    }
    @ExceptionHandler(IllegalArgumentException.class) public ResponseEntity < String > assertionException(final IllegalArgumentException e) {
        return error(e, HttpStatus.NOT_FOUND, e.getLocalizedMessage());
    }*/
}


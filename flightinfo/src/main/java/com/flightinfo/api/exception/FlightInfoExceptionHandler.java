package com.flightinfo.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class FlightInfoExceptionHandler {
    @ExceptionHandler(value = {FlightLocationNotFound.class,FlightLocationBadRequest.class})
    public ResponseEntity<Object> handleFlightLocationNotFound
            (FlightLocationNotFound flightLocationNotFound) {
        FlightInfoException flightInfoException = new FlightInfoException(
                flightLocationNotFound.getMessage(),
                flightLocationNotFound.getCause(),
                HttpStatus.NOT_FOUND
        );
        return new ResponseEntity<>(flightInfoException, HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<Object> handleProperRequest
            (FlightLocationBadRequest flightLocationBadRequest) {
        FlightInfoException flightInfoException = new FlightInfoException(
                flightLocationBadRequest.getMessage(),
                flightLocationBadRequest.getCause(),
                HttpStatus.BAD_REQUEST
        );
        return new ResponseEntity<>(flightInfoException, HttpStatus.BAD_REQUEST);
    }

}

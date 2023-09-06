package com.flightinfo.api.exception;

public class FlightLocationNotFound extends RuntimeException{


    public FlightLocationNotFound(String message) {
        super(message);
    }

    public FlightLocationNotFound(String message, Throwable cause) {
        super(message, cause);
    }
}

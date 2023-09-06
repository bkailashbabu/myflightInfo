package com.flightinfo.api.exception;

public class FlightLocationBadRequest extends RuntimeException{


    public FlightLocationBadRequest(String message) {
        super(message);
    }

    public FlightLocationBadRequest(String message, Throwable cause) {
        super(message, cause);
    }
}

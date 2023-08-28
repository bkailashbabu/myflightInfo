package com.flightinfo.api.exception;

public class DestNotFoundException extends RuntimeException {

    public DestNotFoundException(String message) {
        super(message);
    }
}

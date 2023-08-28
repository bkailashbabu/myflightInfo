package com.flightinfo.api.util;

public class FlightNotFoundException extends Exception {

    public String getMessage() {
        return "Fligh not found";
    }
}

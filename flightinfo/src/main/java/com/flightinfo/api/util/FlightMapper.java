package com.flightinfo.api.util;

import com.flightinfo.api.dto.FlightInfoDTO;
import com.flightinfo.api.entity.FlightInfo;

public class FlightMapper {

    public static FlightInfoDTO mapToDto(FlightInfo ent) {

        FlightInfoDTO fd = new FlightInfoDTO(
                ent.getFid(),
                ent.getFlightNumber(),
                ent.getOrigin(),
                ent.getDestination(),
                ent.getDepartureTime(),
                ent.getArrivalTime(),
                ent.getPrice(),
                ent.getDuration()
        );
        return fd;
    }
}

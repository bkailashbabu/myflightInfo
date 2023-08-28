package com.flightinfo.api.dto;

import com.flightinfo.api.entity.FlightInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class FlightInfoDTO {

    private Long fid;
    private String flightNumber;
    private String origin;
    private String destination;
    private String departureTime;
    private String arrivalTime;
    private Long price;
    private String duration;


}

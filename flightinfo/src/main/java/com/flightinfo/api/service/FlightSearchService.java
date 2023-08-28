package com.flightinfo.api.service;

import com.flightinfo.api.dto.FlightInfoDTO;
import com.flightinfo.api.entity.FlightInfo;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface FlightSearchService {

    public ResponseEntity<List<FlightInfo>> getDetailsFromOriginToDestination(String origin, String destination);

    public ResponseEntity<List<FlightInfoDTO>> getDetailsFromOriginToDestinationBySort(String origin, String destination, String value);


}

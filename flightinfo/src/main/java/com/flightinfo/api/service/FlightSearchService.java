package com.flightinfo.api.service;

import com.flightinfo.api.dto.FlightInfoDTO;
import com.flightinfo.api.entity.FlightInfo;
import java.util.List;

public interface FlightSearchService {

    public List<FlightInfoDTO> getDetailsFromOriginToDestination(String origin, String destination);

    public List<FlightInfoDTO> getDetailsFromOriginToDestinationBySort(String origin, String destination, String value);


}

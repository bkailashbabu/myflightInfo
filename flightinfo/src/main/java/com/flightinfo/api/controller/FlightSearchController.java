package com.flightinfo.api.controller;

import com.flightinfo.api.dto.FlightInfoDTO;
import com.flightinfo.api.entity.FlightInfo;
import com.flightinfo.api.service.FlightSearchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FlightSearchController {
    Logger logger = LoggerFactory.getLogger(FlightSearchController.class);
    @Autowired
    FlightSearchService flightSearchService;


    //flight details - origin and destination
    @GetMapping("/v1/flights/{origin}/{destination}")
    public List<FlightInfoDTO> getDetailsFromOriginToDestination(@PathVariable("origin") String origin, @PathVariable("destination") String destination) {
        List<FlightInfoDTO> list1 = flightSearchService.getDetailsFromOriginToDestination(origin, destination);
        logger.info("In controller + source & destination list ::" + list1.size());
        return list1;
    }

    //flight details - origin and destination based on sort
    @GetMapping("/v1/flights/{origin}/{destination}/sortby")
    public List<FlightInfoDTO> getDetailsFromOriginToDestinationBySort(@PathVariable("origin") String origin, @PathVariable("destination") String destination, @RequestParam String value) {
        List<FlightInfoDTO> result = flightSearchService.getDetailsFromOriginToDestinationBySort(origin, destination, value);
        logger.info("In controller + source & destination list based on sort::" + result.size());
        return result;
    }


}

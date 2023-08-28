package com.flightinfo.api.service;

import com.flightinfo.api.dto.FlightInfoDTO;
import com.flightinfo.api.entity.FlightInfo;
import com.flightinfo.api.exception.DestNotFoundException;
import com.flightinfo.api.util.FlightDurationUtil;
import com.flightinfo.api.util.FlightMapper;
import com.flightinfo.api.repository.FlightSearchRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class FlightSearchServiceImpl implements FlightSearchService {

    Logger logger = LoggerFactory.getLogger(FlightSearchServiceImpl.class);
    @Autowired
    private FlightSearchRepo flightSearchRepo;


    @Override
    public ResponseEntity<List<FlightInfo>> getDetailsFromOriginToDestination(String origin, String destination) {
        List<FlightInfo> list = flightSearchRepo.findAll();
        // List<FlightInfo> list = flightSearchRepo.findById(1L);
        logger.info("===================testing" + list.size());


      /*  List<FlightInfoDTO> dtolist = list.stream().map(FlightMapper::mapToDto)
                .collect(Collectors.toList());*/
        logger.info("service impl==" + list);
        List<FlightInfo> result = list.stream().filter(p -> p.getOrigin().equals(origin)).filter(p -> p.getDestination().equals(destination)).collect(Collectors.toList());
        logger.info("===================testing" + result.size());

        if (result.size() <= 0) {
            throw new DestNotFoundException(" Requested Location Flights not are not available: " + origin + "to" + destination);

            //return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        List<FlightInfo> result1 = result.stream().filter(n -> n.getFlightNumber().length() > 0)
                .map(k -> {
                    k.setDuration(FlightDurationUtil.getHours(k.getArrivalTime(), k.getDepartureTime()));
                    return k;
                }).collect(Collectors.toList());
        return ResponseEntity.ok(result1);
    }

    @Override
    public ResponseEntity<List<FlightInfoDTO>> getDetailsFromOriginToDestinationBySort(String origin, String destination, String value) {

        List<FlightInfo> list = flightSearchRepo.findAll();
        List<FlightInfoDTO> dtolist = list.stream().map(FlightMapper::mapToDto)
                .collect(Collectors.toList());


        List<FlightInfoDTO> originDestList = dtolist.stream().filter(p -> p.getOrigin().equals(origin)).filter(p -> p.getDestination().equals(destination)).collect(Collectors.toList());

        List<FlightInfoDTO> originDestWithDurationList = originDestList.stream().filter(n -> n.getFlightNumber().length() > 0)
                .map(k -> {
                    k.setDuration(FlightDurationUtil.getHours(k.getArrivalTime(), k.getDepartureTime()));
                    return k;
                }).collect(Collectors.toList());
        if (value.equals("price")) {
            logger.info("value passed to sort" + value);
            List priceSortList = originDestWithDurationList.stream().sorted((p1, p2) -> p1.getPrice().compareTo(p2.getPrice())).collect(Collectors.toList());
            return ResponseEntity.ok(priceSortList);
        } else if (value.equals("flightnumber")) {
            logger.info("value passed to sort" + value);
            List flightNumberSortList = originDestWithDurationList.stream().sorted((p1, p2) -> p1.getFlightNumber().compareTo(p2.getFlightNumber())).collect(Collectors.toList());
            return ResponseEntity.ok(flightNumberSortList);
        } else if (value.equals("duration")) {
            logger.info("value passed to sort" + value);
            List durationSortList = originDestWithDurationList.stream().sorted((p1, p2) -> p1.getDuration().compareTo(p2.getDuration())).collect(Collectors.toList());
            logger.info("service impl==" + durationSortList);
            return ResponseEntity.ok(durationSortList);
        } else
            return ResponseEntity.ok(originDestWithDurationList);
    }

}

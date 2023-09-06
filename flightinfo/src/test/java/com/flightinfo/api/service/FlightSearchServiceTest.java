package com.flightinfo.api.service;

import com.flightinfo.api.dto.FlightInfoDTO;
import com.flightinfo.api.entity.FlightInfo;
import com.flightinfo.api.repository.FlightSearchRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class FlightSearchServiceTest {

    @Mock
    FlightSearchRepo flightSearchRepo;

    @InjectMocks
    FlightSearchServiceImpl flightSearchServiceImpl;

    @Test
    void getDetailsFromOriginToDestination() throws Exception {

        List<FlightInfo> flightInfoList = Arrays.asList(
                new FlightInfo(1L, "A101", "AMS", "DEL", "11:00", "17:00", 850L, "6:00"),
                new FlightInfo(2L, "B101", "AMS", "DEL", "12:00", "19:30", 750L, "7:30"),
                new FlightInfo(3L, "C101", "AMS", "BLR", "13:00", "18:30", 800L, "5:30"));

        Mockito.when(flightSearchRepo.findAll()).thenReturn(flightInfoList);
        List<FlightInfoDTO> List1 = flightSearchServiceImpl.getDetailsFromOriginToDestination("AMS", "DEL");
        Assertions.assertNotNull(List1);
        Assertions.assertEquals(2, List1.size());
    }


    @Test
    void getDetailsFromOriginToDestinationBySort() throws Exception {


        List<FlightInfo> flightInfoList = Arrays.asList(
                new FlightInfo(1L, "A101", "AMS", "DEL", "11:00", "17:00", 850L, "6:00"),
                new FlightInfo(2L, "B101", "AMS", "DEL", "12:00", "19:30", 750L, "7:30"),
                new FlightInfo(3L, "C101", "AMS", "BLR", "13:00", "18:30", 800L, "5:30"));

        Mockito.when(flightSearchRepo.findAll()).thenReturn(flightInfoList);
        List<FlightInfoDTO> List1 = flightSearchServiceImpl.getDetailsFromOriginToDestination("AMS", "DEL");
        Assertions.assertNotNull(List1);
        Assertions.assertEquals(2, List1.size());
    }

}
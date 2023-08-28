package com.flightinfo.api;

import com.flightinfo.api.dto.FlightInfoDTO;
import com.flightinfo.api.entity.FlightInfo;
import com.flightinfo.api.repository.FlightSearchRepo;
import com.flightinfo.api.service.FlightSearchService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootMockitoApplicationTests {
    @Autowired
    private FlightSearchService flightSearchService;

    @MockBean
    private FlightSearchRepo flightSearchRepo;

    @Test
    public void getDetailsFromOriginToDestinationTest() {
        when(flightSearchRepo.findAll())
                .thenReturn((List<FlightInfo>) Stream
                        .of(new FlightInfo(1L, "A101", "AMS", "DEL", "11:00", "17:00", 850L, "6:00"
                        )).collect(Collectors.toList()));
        ResponseEntity<List<FlightInfo>> flights = flightSearchService.getDetailsFromOriginToDestination("AMS", "DEL");
        assertEquals(true, flights.getBody().size() <= 1);


    }


}

package com.flightinfo.api;

import com.flightinfo.api.entity.FlightInfo;
import com.flightinfo.api.repository.FlightSearchRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class FlightSearchRepoUnitTest {

    @Autowired
    private FlightSearchRepo flightSearchRepo;

    @Test
    void findAll_should_return_Flights_List() {
        List<FlightInfo> flights = this.flightSearchRepo.findAll();
        assertEquals(14, flights.size());

    }
}

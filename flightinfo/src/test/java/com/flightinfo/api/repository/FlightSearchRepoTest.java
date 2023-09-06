package com.flightinfo.api.repository;

import com.flightinfo.api.entity.FlightInfo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)
@Transactional
class FlightSearchRepoTest {

    @Mock
    FlightSearchRepo flightSearchRepo;

    @Test
    void getDetails() throws Exception {

        List<FlightInfo> flightInfoList = Arrays.asList(
                new FlightInfo(1L, "A101", "AMS", "DEL", "11:00", "17:00", 850L, "6:00"),
                new FlightInfo(2L, "B101", "AMS", "DEL", "12:00", "19:30", 750L, "7:30"),
                new FlightInfo(3L, "C101", "AMS", "BLR", "13:00", "18:30", 800L, "5:30"));

        Mockito.when(flightSearchRepo.findAll()).thenReturn(flightInfoList);
        List<FlightInfo> List1 = flightSearchRepo.findAll();
        Assertions.assertNotNull(List1);
        Assertions.assertEquals(3, List1.size());
    }


}
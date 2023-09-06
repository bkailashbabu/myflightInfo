package com.flightinfo.api.controller;

import com.flightinfo.api.dto.FlightInfoDTO;
import com.flightinfo.api.service.FlightSearchService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;


import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebAppConfiguration()
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@WebMvcTest(FlightSearchController.class)
@AutoConfigureMockMvc
class FlightSearchControllerTest {

    @MockBean
    FlightSearchService flightSearchService;

    @Autowired
    MockMvc mockMvc;

    @InjectMocks
    private FlightSearchController flightSearchController;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(flightSearchController).build();
    }

    @Test
    void getDetailsFromOriginToDestination() throws Exception {
        List<FlightInfoDTO> flightInfoList = Arrays.asList(
                new FlightInfoDTO(1L, "A101", "AMS", "DEL", "11:00", "17:00", 850L, "6:00"),
                new FlightInfoDTO(2L, "B101", "AMS", "BOM", "12:00", "19:30", 750L, "7:30"),
                new FlightInfoDTO(3L, "C101", "AMS", "BLR", "13:00", "18:30", 800L, "5:30"));
        when(flightSearchService.getDetailsFromOriginToDestination("AMS", "DEL")).thenReturn(flightInfoList);
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/flights/{origin}/{destination}", "AMS", "DEL"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].origin").value("AMS"));
    }


    @Test
    void getDetailsFromOriginToDestinationByPrice() throws Exception {

        List<FlightInfoDTO> flightInfoList = Arrays.asList(
                new FlightInfoDTO(13L, "F201", "BOM", "DEL", "21:15", "22:30", 80L, "5:30"),
                new FlightInfoDTO(6L, "F101", "BOM", "DEL", "20:30", "21:30", 80L, "6:00"),
                new FlightInfoDTO(7L, "G101", "BOM", "DEL", "18:00", "19:30", 100L, "7:30")
        );
        when(flightSearchService.getDetailsFromOriginToDestinationBySort("BOM", "DEL", "price")).thenReturn(flightInfoList);
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/flights/{origin}/{destination}/sortby?value=price", "BOM", "DEL")
                        .contentType(MediaType.APPLICATION_JSON))

                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(3)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].price").value("80"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].price").value("100"));
        when(flightSearchService.getDetailsFromOriginToDestinationBySort("BOM", "DEL", "duration")).thenReturn(flightInfoList);
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/flights/{origin}/{destination}/sortby?value=duration", "BOM", "DEL")
                        .contentType(MediaType.APPLICATION_JSON))

                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(3)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].duration").value("5:30"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].duration").value("6:00"));
    }
}
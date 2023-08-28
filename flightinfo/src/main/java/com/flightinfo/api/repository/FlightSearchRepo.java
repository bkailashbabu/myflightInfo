package com.flightinfo.api.repository;

import com.flightinfo.api.entity.FlightInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightSearchRepo extends JpaRepository<FlightInfo, Long> {

}

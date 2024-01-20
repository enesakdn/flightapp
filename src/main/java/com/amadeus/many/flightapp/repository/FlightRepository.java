package com.amadeus.many.flightapp.repository;

import com.amadeus.many.flightapp.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface FlightRepository extends JpaRepository<Flight, Integer> {
    List<Flight> findByDepartureAirport_CityAndDepartureDateTime(String departureCity, LocalDateTime departureDateTime);

    List<Flight> findByArrivalAirport_CityAndDepartureDateTimeAndReturnDateTime(String arrivalCity, LocalDateTime departureDateTime, LocalDateTime returnDateTime);
}

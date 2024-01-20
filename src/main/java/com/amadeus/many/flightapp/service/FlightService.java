package com.amadeus.many.flightapp.service;

import com.amadeus.many.flightapp.entity.Flight;

import java.time.LocalDateTime;
import java.util.List;

    public interface FlightService {

        List<Flight> getAllFlights();

        Flight getFlightById(int id);

        Flight saveFlight(Flight flight);

        Flight deleteFlight(int id);
        public Flight updateFlight(int id, Flight updatedFlight);

        List<Flight> searchFlights(String departureCity, String arrivalCity, LocalDateTime departureDateTime, LocalDateTime returnDateTime);
    }


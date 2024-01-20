package com.amadeus.many.flightapp.service;

import com.amadeus.many.flightapp.entity.Flight;
import com.amadeus.many.flightapp.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class FlightServiceImpl implements FlightService {


    private final FlightRepository flightRepository;
    @Autowired
    public FlightServiceImpl(FlightRepository flightRepository){
        this.flightRepository = flightRepository;
    }

    @Override
    public List<Flight> getAllFlights() {
        return flightRepository.findAll();
    }

    @Override
    public Flight getFlightById(int id) {
        Optional<Flight> flight = flightRepository.findById(id);
        if(flight.isPresent()){
            return flight.get();
        }
        throw new RuntimeException("Id is not exist" + id);
    }

    @Override
    public Flight saveFlight(Flight flight) {
        return flightRepository.save(flight);
    }

    @Override
    public Flight deleteFlight(int id) {
        Flight flight = getFlightById(id);
        flightRepository.delete(flight);
        return flight;
    }
    @Override
    public Flight updateFlight(int id, Flight updatedFlight) {
        Optional<Flight> existingFlightOptional = flightRepository.findById(id);

        return existingFlightOptional.map(existingFlight -> {
            existingFlight.setDepartureAirport(updatedFlight.getDepartureAirport());
            existingFlight.setArrivalAirport(updatedFlight.getArrivalAirport());
            existingFlight.setDepartureDateTime(updatedFlight.getDepartureDateTime());
            existingFlight.setReturnDateTime(updatedFlight.getReturnDateTime());
            existingFlight.setPrice(updatedFlight.getPrice());


            return flightRepository.save(existingFlight);
        }).orElseThrow(() -> new RuntimeException("Id is not exist: " + id));
    }

    @Override
    public List<Flight> searchFlights(String departureCity, String arrivalCity, LocalDateTime departureDateTime, LocalDateTime returnDateTime) {
        if (returnDateTime == null) {
            return flightRepository.findByDepartureAirport_CityAndDepartureDateTime(departureCity, departureDateTime);
        } else {
            return flightRepository.findByArrivalAirport_CityAndDepartureDateTimeAndReturnDateTime(arrivalCity, departureDateTime, returnDateTime);
        }
    }


}
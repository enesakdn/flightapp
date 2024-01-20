package com.amadeus.many.flightapp.service;

import com.amadeus.many.flightapp.entity.Airport;
import com.amadeus.many.flightapp.entity.Flight;
import com.amadeus.many.flightapp.repository.AirportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AirportServiceImpl implements AirportService {
    @Autowired
    private AirportRepository airportRepository;
    @Override
    public List<Airport> getAllAirports() {
        return airportRepository.findAll();
    }
    @Override
    public Airport getAirportById(int id) {
        Optional<Airport> airport = airportRepository.findById(id);
        if(airport.isPresent()){
            return airport.get();
        }
        throw new RuntimeException("Id is not exist" + id);
    }
    @Override
    public Airport saveAirport(Airport airport) {
        return airportRepository.save(airport);
    }
    @Override
    public Airport deleteAirport(int id) {
        Airport flight = getAirportById(id);
        airportRepository.delete(flight);
        return flight;
    }
    @Override
    public Airport updateAirport(int id, Airport updatedAirport) {
        Optional<Airport> existingAirportOptional = airportRepository.findById(id);

        return existingAirportOptional.map(existingAirport -> {
            existingAirport.setCity(updatedAirport.getCity());
            return airportRepository.save(existingAirport);
        }).orElseThrow(() -> new RuntimeException("Id is not exist: " + id));
    }
}
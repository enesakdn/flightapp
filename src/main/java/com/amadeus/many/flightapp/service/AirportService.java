package com.amadeus.many.flightapp.service;

import com.amadeus.many.flightapp.entity.Airport;

import java.util.List;
import java.util.Optional;

public interface AirportService {

    List<Airport> getAllAirports();

    Airport getAirportById(int id);

    Airport saveAirport(Airport airport);

    Airport deleteAirport(int id);

    Airport updateAirport(int id, Airport updatedAirport);
}
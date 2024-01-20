package com.amadeus.many.flightapp.controller;

import com.amadeus.many.flightapp.entity.Airport;
import com.amadeus.many.flightapp.service.AirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/airports")
public class AirportController {

    @Autowired
    private AirportService airportService;

    @GetMapping
    public List<Airport> getAllAirports() {
        return airportService.getAllAirports();
    }

    @GetMapping("/{id}")
    public Airport getAirportById(@PathVariable int id) {
        return airportService.getAirportById(id);
    }

    @PostMapping
    public Airport saveAirport(@RequestBody Airport airport) {
        return airportService.saveAirport(airport);
    }

    @PutMapping("/{id}")
    public Airport updateAirport(@PathVariable int id, @RequestBody Airport updatedAirport) {
        return airportService.updateAirport(id, updatedAirport);
    }

    @DeleteMapping("/{id}")
    public Airport deleteAirport(@PathVariable int id) {
        return airportService.deleteAirport(id);
    }
}

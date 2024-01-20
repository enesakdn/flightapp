package com.amadeus.many.flightapp.repository;

import com.amadeus.many.flightapp.entity.Airport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AirportRepository extends JpaRepository<Airport, Integer> {
}

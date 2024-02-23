package com.Airlines.AirlinesManagementSystem.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Airlines.AirlinesManagementSystem.Models.FlightManagement;
import com.Airlines.AirlinesManagementSystem.Repo.FlightRepo;

@Service
public class FlightService {
	@Autowired
	FlightRepo flightRepo;

	public FlightManagement addFlights(FlightManagement flight) {
		// TODO Auto-generated method stub
			return flightRepo.save(flight);
	}

	public FlightManagement getFlight(int id) {
		// TODO Auto-generated method stub
		FlightManagement flight = flightRepo.findById(id).get();
		FlightManagement flights = new FlightManagement();
		flights.setArrival_city(flight.getArrival_city());
		flights.setArrival_time(flight.getArrival_time());
		flights.setAvailable_seats(flights.getAvailable_seats());
		flights.setDeparture_city(flight.getDeparture_city());
		flights.setDeparture_time(flight.getDeparture_time());
		flights.setFlight_no(flight.getFlight_no());
		flights.setFlight_status(flight.getFlight_status());
		return flights;
	}

	public FlightManagement updateFlight(int id, String status) {
		// TODO Auto-generated method stub
		FlightManagement flights = flightRepo.findById(id).get();
		flights.setFlight_status(status);
		flightRepo.save(flights);
		return flights;
	}

	public FlightManagement deleteFlight(int id) {
		// TODO Auto-generated method stub
		FlightManagement flights = flightRepo.findById(id).get();
		flightRepo.delete(flights);
		return flights;
	}
	
	
}

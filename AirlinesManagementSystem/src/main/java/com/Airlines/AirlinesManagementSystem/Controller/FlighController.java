package com.Airlines.AirlinesManagementSystem.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Airlines.AirlinesManagementSystem.Models.FlightManagement;
import com.Airlines.AirlinesManagementSystem.Service.FlightService;
import com.Airlines.AirlinesManagementSystem.Service.KafkaProducerService;

@RestController
@RequestMapping("/flights")
public class FlighController {
	
	@Autowired
	FlightService flightService;
	@Autowired
	KafkaProducerService kafkaProducerService;
	@PostMapping("/addFlight")
	public ResponseEntity<FlightManagement> addFlights(@RequestBody FlightManagement flight) {
		FlightManagement flights =  flightService.addFlights(flight);
		kafkaProducerService.AddStatus(flights.getFlight_no(),flights,flights.getFlight_status());
		return new ResponseEntity<>(flights,HttpStatus.OK);
	}
	@GetMapping("/getFlight")
	public ResponseEntity<FlightManagement> getFlight(@RequestParam int id) {
		FlightManagement flights = flightService.getFlight(id);
		return new ResponseEntity<>(flights,HttpStatus.OK);
	}
	
	@PutMapping("/updateFlight")
	public ResponseEntity<FlightManagement> updateFlight(@RequestParam int id, @RequestParam String status){
		FlightManagement flights = flightService.updateFlight(id,status);
		kafkaProducerService.AddStatus(id,flights,status);
		 return new ResponseEntity<>(flights,HttpStatus.OK);
	}
	@DeleteMapping("/deleteFlight")
	public ResponseEntity<FlightManagement> deleteFlight(@RequestParam int id){
		return new ResponseEntity<>(flightService.deleteFlight(id),HttpStatus.OK);
	}
}

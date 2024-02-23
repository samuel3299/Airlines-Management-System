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

import com.Airlines.AirlinesManagementSystem.Log.Logs;
import com.Airlines.AirlinesManagementSystem.Models.PassangerBooking;
import com.Airlines.AirlinesManagementSystem.Service.BookingService;
import com.Airlines.AirlinesManagementSystem.Service.KafkaConsumerService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

@RestController
@RequestMapping("/Booking")
public class BookingController {

	@Autowired
	BookingService bookingService;
	@Autowired
	KafkaConsumerService kafkaConsumerService;
	
	@PostMapping("/addPassanger")
	public ResponseEntity<PassangerBooking> addPassenger(@RequestBody PassangerBooking passanger) throws JsonMappingException, JsonProcessingException {
		if(kafkaConsumerService.getBooking(passanger.getFlightNo()) == true) {
			PassangerBooking passanger1 = bookingService.addPassanger(passanger);
			Logs.logInfo("Receiving message to post Mapping: " + "Success");
		return new ResponseEntity<>(passanger1,HttpStatus.OK);
	}
		else {
			PassangerBooking passanger1 = bookingService.addPassanger1(passanger);
			Logs.logInfo("Receiving message to post Mapping: " + HttpStatus.NOT_ACCEPTABLE);
			return new ResponseEntity<>(passanger1,HttpStatus.OK);
		}
		 
	}
	
	@GetMapping("/getPassanger")
	public ResponseEntity<PassangerBooking> getPassanger(@RequestParam int id) {
		return new ResponseEntity<>(bookingService.getPassanger(id),HttpStatus.OK);
	}
	@PutMapping("/updatePassanger")
	public ResponseEntity<String> updatePassanger(@RequestParam int id, @RequestParam int FlightNo) throws JsonMappingException, JsonProcessingException {
		if(kafkaConsumerService.getBooking(FlightNo) != true) {
			bookingService.updatePassanger1(id);
			 return new ResponseEntity<>("Flight Cancelled",HttpStatus.OK);
		}
		else {
			bookingService.updatePassanger(id,FlightNo);
			return new ResponseEntity<>("Booking Confirmed",HttpStatus.OK);
		}
		}
	@DeleteMapping("/deletePassanger")
	public ResponseEntity<PassangerBooking> deletePassanger(@RequestParam int id){
		 return new ResponseEntity<>( bookingService.deletePassanger(id),HttpStatus.OK);
		
	}
	}

package com.Airlines.AirlinesManagementSystem.Service;





import java.util.*;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.Airlines.AirlinesManagementSystem.Log.Logs;
import com.Airlines.AirlinesManagementSystem.Models.FlightManagement;
import com.fasterxml.jackson.databind.ObjectMapper;




@Service
public class KafkaProducerService {
	
	private final KafkaTemplate<String, String>kafkaTemplate;
	private final String Topic = "FlightStimulator";
	private final ObjectMapper obj = new ObjectMapper();
	
	public  KafkaProducerService(KafkaTemplate<String, String>kafkaTemplate) {
		this.kafkaTemplate=kafkaTemplate;
	}


	public void AddStatus(int id, FlightManagement flights, java.lang.String status) {
		// TODO Auto-generated method stub
		 try {
		        Map<String, Object> flightDetails =  new HashMap<>();
		        flightDetails.put("id", id);
	        flightDetails.put("flight", flights);
	        flightDetails.put("status", status);
		        String message = obj.writeValueAsString(flightDetails);
		        kafkaTemplate.send(Topic, message);
		    } catch (Exception e) {
		        Logs.logInfo("Error Received: " + e);
		    }
	}

	
}

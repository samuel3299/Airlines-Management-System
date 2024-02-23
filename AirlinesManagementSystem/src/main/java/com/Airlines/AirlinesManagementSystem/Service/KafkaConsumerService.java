package com.Airlines.AirlinesManagementSystem.Service;


import java.util.*;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.Airlines.AirlinesManagementSystem.Log.Logs;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;




@Service
public class KafkaConsumerService {


	private final ObjectMapper objectMapper = new ObjectMapper();
	private static List<String> jsonList = new ArrayList<>();

    @KafkaListener(topics = "FlightStimulator", groupId = "my_consumer_group")
    public void consumeMessage(String message) {
    	try {
            // Deserialize the JSON message into a Map
            Map<String, Object> flightStatusUpdate = objectMapper.readValue(message, Map.class);
    		jsonList.add(message);
    		Logs.logInfo("Receiving message to Kafka topic: " + message);
        } catch (Exception e) {
            
        	Logs.logInfo("Error Received: " + e);
        }
       
    }

	public boolean getBooking(int flightNo) throws JsonMappingException, JsonProcessingException {
		// Extract the flight details and status
		List<String>jsonList1 = getMessages(1,100);
		int i =0;
		boolean res = true;
		if(jsonList1.size() == 0)Logs.logInfo("Received message: " + "empty");
		while(i<jsonList1.size()) {
			
			Map<String, Object> flightStatusUpdate = objectMapper.readValue(jsonList1.get(i), Map.class);
			  int id = (int) flightStatusUpdate.get("id");
        String status = (String) flightStatusUpdate.get("status");
        String k = "cancelled";
        if(id == flightNo) {
        	if(status.equals(k)) {
        		res= false;
        	}
        	else res = true;
        	
        }
        i++;
		}
		return res;
	}
	public List<String> getMessages(int page, int pageSize) {
        int startIndex = (page - 1) * pageSize;
        int endIndex = Math.min(startIndex + pageSize, jsonList.size());
        return jsonList.subList(startIndex, endIndex);
    }
}

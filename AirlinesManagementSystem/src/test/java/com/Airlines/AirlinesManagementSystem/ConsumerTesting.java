package com.Airlines.AirlinesManagementSystem;


import com.Airlines.AirlinesManagementSystem.Log.Logs;
import com.Airlines.AirlinesManagementSystem.Service.KafkaConsumerService;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import org.mockito.MockitoAnnotations;
public class ConsumerTesting {
	

	    @InjectMocks
	    private KafkaConsumerService kafkaConsumerService;

	    @BeforeEach
	    public void setUp() {
	        MockitoAnnotations.openMocks(this);
	    }

	    @Test
	    public void testConsumeMessage() {
	        String message = "{\"id\":25,\"status\":\"Ontime\"}";
	        kafkaConsumerService.consumeMessage(message);

	        // Verify that the message was added to the list
	        assert kafkaConsumerService.getMessages(1,  100).contains(message);
	        Logs.logInfo("Received consumeMessage: " + message);
	    }

	    @Test
	    public void testGetBooking() throws Exception {
	        String message = "{\"id\":25,\"status\":\"cancelled\"}";
	        kafkaConsumerService.consumeMessage(message);

	        boolean result = kafkaConsumerService.getBooking(25);

	        Logs.logInfo("Received test Get Booking: " + result);
	        // Expect false since the status is "cancelled"
	        assert !result;
	    }

	    @Test
	    public void testGetBookingWithNoMatch() throws Exception {
	        String message = "{\"id\":26,\"status\":\"delay\"}";
	        kafkaConsumerService.consumeMessage(message);

	        boolean result = kafkaConsumerService.getBooking(1);
	        Logs.logInfo("Received tex tGet Booking with No Match : " + result);

	        // Expect true since there's no match for flightNo  1
	        assert result;
	    }


}

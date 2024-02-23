package com.Airlines.AirlinesManagementSystem;



import com.Airlines.AirlinesManagementSystem.Service.KafkaProducerService;
import com.Airlines.AirlinesManagementSystem.Log.Logs;
import com.Airlines.AirlinesManagementSystem.Models.FlightManagement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;


import java.util.concurrent.CompletableFuture;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ProducerTesting {

	 @Mock
	    private KafkaTemplate<String, String> kafkaTemplate;

	    @InjectMocks
	    private KafkaProducerService kafkaProducerService;

	    @BeforeEach
	    public void setUp() {
	        MockitoAnnotations.openMocks(this);
	    }

	    @Test
	    public void testAddStatus() {
	        int id =  25;
	        FlightManagement flights = new FlightManagement();
	        String status = "cancelled";

	        // Mock the send method to return a ListenableFuture
	        CompletableFuture<SendResult<String, String>> future = CompletableFuture.completedFuture(null);
	        when(kafkaTemplate.send(any(String.class), any(String.class))).thenReturn(future);

	        kafkaProducerService.AddStatus(id, flights, status);

	        // Verify that the send method was called with the correct topic and message
	        verify(kafkaTemplate, times(1)).send(eq("FlightStimulator"), any(String.class));
	        Logs.logInfo("Received Add status : " + future);
	    }
}

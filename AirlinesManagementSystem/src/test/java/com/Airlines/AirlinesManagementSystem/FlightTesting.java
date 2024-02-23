package com.Airlines.AirlinesManagementSystem;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.Airlines.AirlinesManagementSystem.Log.Logs;
import com.Airlines.AirlinesManagementSystem.Models.FlightManagement;
import com.Airlines.AirlinesManagementSystem.Repo.FlightRepo;
import com.Airlines.AirlinesManagementSystem.Service.FlightService;

import static org.mockito.Mockito.*;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FlightTesting {
	@Mock
    private FlightRepo flightRepo;

    @InjectMocks
    private FlightService flightService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    
    @Test
    public void testAddFlights() {
        FlightManagement flight = new FlightManagement();
        flight.setFlight_no(25);
        flight.setDeparture_city("Mumbai");
        flight.setArrival_city("New Delhi");
        flight.setDeparture_city("2024-02-27T07:20:00");
        flight.setArrival_time("2024-02-23T09:40:00");
        flight.setAvailable_seats(220);
        flight.setFlight_status("Ontime");
        
        FlightManagement flight1 = new FlightManagement();
        flight1.setFlight_no(26);
        flight1.setDeparture_city("New Delhi");
        flight1.setArrival_city("Kolkata");
        flight1.setDeparture_city("2024-02-27T21:00:00");
        flight1.setArrival_time("2024-02-23T23:20:00");
        flight1.setAvailable_seats(200);
        flight1.setFlight_status("Ontime");
        
        when(flightRepo.save(any(FlightManagement.class))).thenReturn(flight);
        when(flightRepo.save(any(FlightManagement.class))).thenReturn(flight1);
        Logs.logInfo("Received add Flight: " + flight);
        Logs.logInfo("Received add Flight: " + flight1);
    }

    @Test
    public void testGetFlight() {
        int id =  25;
        FlightManagement flight = new FlightManagement();
        when(flightRepo.findById(id)).thenReturn(Optional.of(flight));
        Logs.logInfo("Received Get Flight: " + flight);
        
    }

    @Test
    public void testUpdateFlight() {
        int id =  25;
        String status = "cancelled";
        FlightManagement flight = new FlightManagement();
        flight.setFlight_status(status);
        when(flightRepo.findById(id)).thenReturn(Optional.of(flight));

        FlightManagement result = flightService.updateFlight(id, status);

        assertEquals(status, result.getFlight_status());
        Logs.logInfo("Received update Flight: " + flight);
        Logs.logInfo("Received update Flight: " + result);
    }

    @Test
    public void testDeleteFlight() {
        int id =  26;
        FlightManagement flight = new FlightManagement();
        when(flightRepo.findById(id)).thenReturn(Optional.of(flight));

        FlightManagement result = flightService.deleteFlight(id);

        assertEquals(flight, result);
        verify(flightRepo, times(1)).delete(flight);
        Logs.logInfo("Received delete Flight: " + flight);
    }
}

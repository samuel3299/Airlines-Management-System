package com.Airlines.AirlinesManagementSystem;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.Airlines.AirlinesManagementSystem.Log.Logs;
import com.Airlines.AirlinesManagementSystem.Models.PassangerBooking;
import com.Airlines.AirlinesManagementSystem.Repo.BookingRepo;
import com.Airlines.AirlinesManagementSystem.Service.BookingService;

import static org.mockito.Mockito.*;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BookingTesting {
	 @Mock
	    private BookingRepo bookingRepo;
	 @InjectMocks
	BookingService bookingService;
	 
	 @BeforeEach
	    public void setUp() {
	        MockitoAnnotations.openMocks(this);
	    }
	 
	 
	@Test
	void testBookingService() {
		PassangerBooking p = new PassangerBooking();
		p.setPassangerName("kiran");
		p.setPassangerAddress("Mumbai");
		p.setContactNo(Long.parseLong("7790214530"));
		p.setFlightNo(25);
		
		when(bookingRepo.save(any(PassangerBooking.class))).thenReturn(p);
		PassangerBooking result =  bookingService.addPassanger(p);
		
		 assertEquals(p, result);
		 Logs.logInfo("Received Booking Service res : " + result);
		 Logs.logInfo("Received Booking Service : " + p);
	}
	
    @Test
    public void testDeletePassanger() {
        int id =  1;
        PassangerBooking passanger = new PassangerBooking();
        passanger.setBookingId(id);

        when(bookingRepo.findById(id)).thenReturn(Optional.of(passanger));

        PassangerBooking result = bookingService.deletePassanger(id);

        assertEquals(id, result.getBookingId());
        verify(bookingRepo, times(1)).delete(passanger);
        Logs.logInfo("Received Delete Passanger res : " + result);
        Logs.logInfo("Received Delete Passanger : " + passanger);
    }
    
    @Test
    public void testUpdatePassanger() {
    	int id = 25,flightNo=26;
    	 PassangerBooking passanger = new PassangerBooking();
    	 when(bookingRepo.findById(id)).thenReturn(Optional.of(passanger));
    	 passanger = bookingRepo.findById(id).get();
		passanger.setFlightNo(flightNo);
		when(bookingRepo.save(any(PassangerBooking.class))).thenReturn(passanger);
		Logs.logInfo("Received update Passanger : " + passanger);
    }
	

}

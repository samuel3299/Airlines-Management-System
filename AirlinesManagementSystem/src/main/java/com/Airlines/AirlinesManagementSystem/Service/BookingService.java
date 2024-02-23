package com.Airlines.AirlinesManagementSystem.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Airlines.AirlinesManagementSystem.Models.PassangerBooking;
import com.Airlines.AirlinesManagementSystem.Repo.BookingRepo;

@Service
public class BookingService {

	@Autowired
	BookingRepo bookingRepo;

	public PassangerBooking addPassanger(PassangerBooking passanger) {
		// TODO Auto-generated method stub
		bookingRepo.save(passanger);
		return passanger;
	}

	public PassangerBooking getPassanger(int id) {
		// TODO Auto-generated method stub
		PassangerBooking pb = bookingRepo.findById(id).get();
		PassangerBooking pb1 = new PassangerBooking();
		pb1.setBookedStatus("Booked");
		pb1.setBookingId(id);
		pb1.setContactNo(pb.getContactNo());
		pb1.setFlightNo(pb.getFlightNo());
		pb1.setPassangerName(pb.getPassangerName());
		pb1.setPassangerAddress(pb.getPassangerAddress());
		return pb1;
	}
	

	public PassangerBooking deletePassanger(int id) {
		// TODO Auto-generated method stub
		PassangerBooking passanger = bookingRepo.findById(id).get();
		bookingRepo.delete(passanger);
		return passanger;
	}

	public void updatePassanger(int id, int flightNo) {
		// TODO Auto-generated method stub
		PassangerBooking passanger = bookingRepo.findById(id).get();
		passanger.setFlightNo(flightNo);
		bookingRepo.save(passanger);
		
	}

	public PassangerBooking addPassanger1(PassangerBooking passanger) {
		// TODO Auto-generated method stub
		passanger.setBookedStatus("cancelled");
		bookingRepo.save(passanger);
		return passanger;
	}

	public void updatePassanger1(int id) {
		// TODO Auto-generated method stub
		PassangerBooking passanger = bookingRepo.findById(id).get();
		passanger.setBookedStatus("cancelled");
		bookingRepo.save(passanger);
	}
}

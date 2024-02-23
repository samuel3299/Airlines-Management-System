package com.Airlines.AirlinesManagementSystem.Models;

import org.springframework.data.cassandra.core.mapping.Column;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "bookingdetails")
public class PassangerBooking {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int bookingId;
	
	String passangerName;
	String passangerAddress;
	long contactNo;
	int flightNo;
	@Column(value = "BookedStatus")
	String BookedStatus;
	
	public PassangerBooking() {}

	public int getBookingId() {
		return bookingId;
	}

	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}

	public String getPassangerName() {
		return passangerName;
	}

	public void setPassangerName(String passangerName) {
		this.passangerName = passangerName;
	}

	public String getPassangerAddress() {
		return passangerAddress;
	}

	public void setPassangerAddress(String passangerAddress) {
		this.passangerAddress = passangerAddress;
	}

	public long getContactNo() {
		return contactNo;
	}

	public void setContactNo(long contactNo) {
		this.contactNo = contactNo;
	}

	public int getFlightNo() {
		return flightNo;
	}

	public void setFlightNo(int flightNo) {
		this.flightNo = flightNo;
	}

	public String getBookedStatus() {
		return BookedStatus;
	}

	public void setBookedStatus(String bookedStatus) {
		BookedStatus = bookedStatus;
	}
	
	
	
}

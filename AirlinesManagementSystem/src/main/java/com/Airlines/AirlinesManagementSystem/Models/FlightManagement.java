package com.Airlines.AirlinesManagementSystem.Models;


import org.springframework.data.annotation.Id;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;



@Table("flights")
public class FlightManagement{
		@Id
	    @PrimaryKey
	    private int flight_no;

		@Column("departure_city")
	    private String departure_city;

		@Column("arrival_city")
	    private String arrival_city;

		@Column("departure_time")
	    private String departure_time;

		@Column("arrival_time")
	    private String arrival_time;


	    private int available_seats;
	    @Column("flight_status")
	    private String flight_status;
	
	public FlightManagement() {}

	public int getFlight_no() {
		return flight_no;
	}

	public void setFlight_no(int flight_no) {
		this.flight_no = flight_no;
	}

	public String getDeparture_city() {
		return departure_city;
	}

	public void setDeparture_city(String departure_city) {
		this.departure_city = departure_city;
	}

	public String getArrival_city() {
		return arrival_city;
	}

	public void setArrival_city(String arrival_city) {
		this.arrival_city = arrival_city;
	}

	public String getDeparture_time() {
		return departure_time;
	}

	public void setDeparture_time(String departure_time) {
		this.departure_time = departure_time;
	}

	public String getArrival_time() {
		return arrival_time;
	}

	public void setArrival_time(String arrival_time) {
		this.arrival_time = arrival_time;
	}

	public int getAvailable_seats() {
		return available_seats;
	}

	public void setAvailable_seats(int available_seats) {
		this.available_seats = available_seats;
	}

	public String getFlight_status() {
		return flight_status;
	}

	public void setFlight_status(String flight_status) {
		this.flight_status = flight_status;
	}

	
}

package com.Airlines.AirlinesManagementSystem.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Airlines.AirlinesManagementSystem.Models.PassangerBooking;

@Repository
public interface BookingRepo extends JpaRepository<PassangerBooking, Integer> {

}

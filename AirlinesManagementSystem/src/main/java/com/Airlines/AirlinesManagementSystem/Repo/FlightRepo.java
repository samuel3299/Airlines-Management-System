package com.Airlines.AirlinesManagementSystem.Repo;

import org.springframework.data.cassandra.repository.CassandraRepository;


import org.springframework.stereotype.Repository;

import com.Airlines.AirlinesManagementSystem.Models.FlightManagement;



@Repository
public interface FlightRepo extends CassandraRepository<FlightManagement, Integer> {

}

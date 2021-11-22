package com.example.employeecontrol.repository;

import com.example.employeecontrol.model.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegionRepository extends JpaRepository<Region,Integer> {
}

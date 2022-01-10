package com.example.employeecontrol.repository;


import com.example.employeecontrol.model.District;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DistrictRepository extends JpaRepository<District,Integer> {
    List<District> findAllByRegionId(Integer region_id);

}

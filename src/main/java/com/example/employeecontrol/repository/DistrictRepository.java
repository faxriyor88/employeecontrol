package com.example.employeecontrol.repository;


import com.example.employeecontrol.model.District;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DistrictRepository extends JpaRepository<District,Integer> {
    List<District> findAllById(Integer id);

}

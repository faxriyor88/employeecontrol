package com.example.employeecontrol.repository;

import com.example.employeecontrol.model.EmployeeAdditional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EmployeeAdditionalRepository extends JpaRepository<EmployeeAdditional,Long> {

    EmployeeAdditional findByEmployeeId(UUID employee_id);
    void deleteByEmployeeId(Long employee_id);

}

package com.example.employeecontrol.repository;

import com.example.employeecontrol.model.MehnatFaoliyati;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MehnatFaoliyatiRepository extends JpaRepository<MehnatFaoliyati,UUID> {

    List<MehnatFaoliyati> findAllByEmployeeId(UUID employee_id);
}

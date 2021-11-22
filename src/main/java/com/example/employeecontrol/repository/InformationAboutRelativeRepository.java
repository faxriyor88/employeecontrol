package com.example.employeecontrol.repository;


import com.example.employeecontrol.model.InformationAboutRelative;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface InformationAboutRelativeRepository extends JpaRepository<InformationAboutRelative, UUID> {
    void deleteAllByEmployeeId(UUID employee_id);
    List<InformationAboutRelative> findAllByEmployeeId(UUID employee_id);

}

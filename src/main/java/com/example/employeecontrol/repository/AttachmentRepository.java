package com.example.employeecontrol.repository;

import com.example.employeecontrol.model.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AttachmentRepository extends JpaRepository<Attachment, UUID> {


    Optional<Attachment> findByEmployeeId(UUID employee_id);

    Attachment getByEmployeeId(UUID employee_id);
}

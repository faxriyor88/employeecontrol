package com.example.employeecontrol.repository;

import com.example.employeecontrol.model.DocumentEffector;
import com.example.employeecontrol.model.Manager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.UUID;
@Repository
public interface DocumentEffectorRepository extends JpaRepository<DocumentEffector, UUID> {
    Page<DocumentEffector> findAllByEffectorNameAndExecutionDeadlineBefore(Manager effectorName, LocalDate executionDeadline, Pageable pageable);
    Page<DocumentEffector> findAllByEffectorNameAndExecutionDeadlineAfter(Manager effectorName, LocalDate executionDeadline, Pageable pageable);
    Page<DocumentEffector> findAllByEffectorNameAndExecutionDeadline(Manager effectorName, LocalDate executionDeadline, Pageable pageable);

}

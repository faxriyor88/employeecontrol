package com.example.employeecontrol.repository;

import com.example.employeecontrol.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, UUID> {
    Page<Employee> findAllByCompanyId(Integer company_id, Pageable pageable);
    List<Employee> findAllByCompanyId(Integer company_id);
    Optional<Employee> findByIdAndCompanyId(UUID id, Integer company_id);
    boolean existsByFullname(String fullname);
    boolean existsByFullnameAndCompanyId(String fullname, Integer company_id);
    boolean existsByFullnameNot(String fullname);
    boolean existsByFullnameNotAndCompanyId(String fullname, Integer company_id);
    boolean existsByCompanyIdAndId(Integer company_id, UUID id);





}

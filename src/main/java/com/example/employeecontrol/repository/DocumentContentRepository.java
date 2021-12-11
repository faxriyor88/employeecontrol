package com.example.employeecontrol.repository;

import com.example.employeecontrol.model.DocumentContent;
import com.example.employeecontrol.model.DocumentEffector;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface DocumentContentRepository extends JpaRepository<DocumentContent, UUID> {
   // List<DocumentContent> findAllByDocumentEffectorContaining(List<DocumentEffector> documentEffectorList);
}

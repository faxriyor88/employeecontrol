package com.example.employeecontrol.repository;

import com.example.employeecontrol.model.DocumentContent;
import com.example.employeecontrol.model.DocumentEffector;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface DocumentContentRepository extends JpaRepository<DocumentContent, UUID> {
    //Muddati o'tgan
    @Query(value = "select * from document_content inner join document_effector on document_content.document_effector_uuid = document_effector.uuid where document_effector.execution_deadline<?1 and document_effector.effector_name_id=?2", nativeQuery = true)
    List<DocumentContent> findAllByDocumentEffectorBefore(LocalDate date, Integer managerId);

    //Muddati kelgan
    @Query(value = "select * from document_content inner join document_effector on document_content.document_effector_uuid = document_effector.uuid where document_effector.execution_deadline=?1 and document_effector.effector_name_id=?2", nativeQuery = true)
    List<DocumentContent> findAllByDocumentEffector(LocalDate date, Integer managerId);

    //Muddati bor
    @Query(value = "select * from document_content inner join document_effector on document_content.document_effector_uuid = document_effector.uuid where document_effector.execution_deadline>?1 and document_effector.effector_name_id=?2", nativeQuery = true)
    List<DocumentContent> findAllByDocumentEffectorAfter(LocalDate date, Integer managerId);
}

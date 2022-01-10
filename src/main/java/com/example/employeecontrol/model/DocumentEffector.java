package com.example.employeecontrol.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class DocumentEffector {
    @Id
    @GeneratedValue
    private UUID uuid;
    private String taskSection;
    private String documentType;
    private String documentNumber;
    private LocalDate documentDate;//date
    private LocalDate registrDay;//date
    private String registrNumber;
    private String documentNameOrContent;
    private String organizationName;
    private String directorResolution;
    @ManyToOne
    private Manager effectorName;
    private String effectorSection;
    private LocalDate executionDeadline;//date
   // private boolean control;


    public DocumentEffector(String taskSection, String documentType, String documentNumber, LocalDate documentDate, LocalDate registrDay, String registrNumber, String documentNameOrContent, String organizationName, String directorResolution, Manager effectorName, String effectorSection, LocalDate executionDeadline) {
        this.taskSection = taskSection;
        this.documentType = documentType;
        this.documentNumber = documentNumber;
        this.documentDate = documentDate;
        this.registrDay = registrDay;
        this.registrNumber = registrNumber;
        this.documentNameOrContent = documentNameOrContent;
        this.organizationName = organizationName;
        this.directorResolution = directorResolution;
        this.effectorName = effectorName;
        this.effectorSection = effectorSection;
        this.executionDeadline = executionDeadline;
    }
}

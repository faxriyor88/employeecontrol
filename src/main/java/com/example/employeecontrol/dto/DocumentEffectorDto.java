package com.example.employeecontrol.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DocumentEffectorDto {
    private String taskSection;
    private String documentType;
    private String documentNumber;
    private LocalDate documentDate;//date
    private LocalDate registrDay;//date
    private String registrNumber;
    private String documentNameOrContent;
    private String organizationName;
    private String directorResolution;
    private Integer effectorNameId;
    private String effectorSection;
    private LocalDate executionDeadline;//date
 }

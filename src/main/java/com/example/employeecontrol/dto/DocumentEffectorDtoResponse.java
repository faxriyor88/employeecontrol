package com.example.employeecontrol.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DocumentEffectorDtoResponse {
    private UUID id;
    private String taskSection;
    private String documentType;
    private String documentNumber;
    private LocalDate documentDate;//date
    private LocalDate registrDay;//date
    private String registrNumber;
    private String documentNameOrContent;
    private String organizationName;
    private String directorResolution;
    private String effectorName;
    private String effectorSection;
    private LocalDate executionDeadline;//date
    private Integer deadLineDay;
    private String originalFileName;
 }

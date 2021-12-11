package com.example.employeecontrol.dto;



import lombok.*;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeAdditonalDTO {
    private List<InformationAboutRelativeDTO> aboutRelative;
    private String nationality;
    private String malumoti;
    private String malumotiboyichamutaxasisligi;
    private String ilmiydarajasi;
    private String ilmiyunvoni;
    private String chettillari;
    private String davlatmukofotibilantaqdirlanganligiqanaqa;
    private String saylovorganiazosi;
    private String partiyaviyligi;
    private String tamomlaganjoyi;
    private String harbiyunvoni;
    private String mehnatfaoliyati;
    private String imageUrl;
}

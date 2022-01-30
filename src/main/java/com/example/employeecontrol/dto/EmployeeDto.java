package com.example.employeecontrol.dto;


import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto {
    String fullname;
    Integer regionId;
    Integer districtId;
    Integer companyId;
    private String lavozimivaQachondan;

    private List<InformationAboutRelativeDTO> aboutRelative;
    private String birthday;
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


}







//package com.example.employeecontrol.dto;
//
//
//import com.example.employeecontrol.model.MehnatFaoliyati;
//import lombok.*;
//
//import java.util.List;
//
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//public class EmployeeDto {
//     String fullname;
//    Integer regionId;
//    Integer districtId;
//    Integer companyId;
//    private String lavozimivaQachondan;
//
//    private List<InformationAboutRelativeDTO> aboutRelative;
//    private String birthday;
//    private String nationality;
//    private String malumoti;
//    private String malumotiboyichamutaxasisligi;
//    private String ilmiydarajasi;
//    private String ilmiyunvoni;
//    private String chettillari;
//    private String davlatmukofotibilantaqdirlanganligiqanaqa;
//    private String saylovorganiazosi;
//    private String partiyaviyligi;
//    private String tamomlaganjoyi;
//    private String harbiyunvoni;
//    private List<MehnatFaoliyatiDto> mehnatfaoliyati;
//
//
//}

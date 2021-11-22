package com.example.employeecontrol.model;

import com.example.employeecontrol.model.absentity.AbstractEntity;
import lombok.*;

import javax.persistence.*;
import java.util.UUID;


@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Employee /*extends AbstractEntity*/ {
    @Id
    @GeneratedValue
    private UUID id;
    private String  fullname;
    @ManyToOne
    private District district;
    @ManyToOne
    private Company company;
    private String birthday;
    private String lavozimivaQachondan;
    ///////////
//    private String nationality;
//    private String malumoti;
//    private String malumotiboyichamutaxasisligi;
//    private String ilmiydarajasi;
//    private String chettillari;
//    private String davlatmukofotibilantaqdirlanganligiqanaqa;
//    private String saylovorganiazosi;
//    private String partiyaviyligi;
//    private String tamomlaganjoyi;
//    private String harbiyunvoni;
//    private String mehnatfaoliyati;


    public Employee(String fullname, District district, Company company, String birthday, String lavozimivaQachondan) {
        this.fullname = fullname;
        this.district = district;
        this.company = company;
        this.birthday = birthday;
        this.lavozimivaQachondan = lavozimivaQachondan;
    }
}

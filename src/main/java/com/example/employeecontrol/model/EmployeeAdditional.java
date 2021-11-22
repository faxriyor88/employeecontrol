package com.example.employeecontrol.model;

import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeAdditional {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nationality;
    private String malumoti;
    private String malumotiboyichamutaxasisligi;
    private String ilmiydarajasi;
    private String chettillari;
    private String davlatmukofotibilantaqdirlanganligiqanaqa;
    private String saylovorganiazosi;
    private String partiyaviyligi;
    private String tamomlaganjoyi;
    private String harbiyunvoni;
    private String mehnatfaoliyati;
    private String ilmiyunvon;
    @OnDelete(action = OnDeleteAction.CASCADE)
    @OneToOne
    private Employee employee;

    public EmployeeAdditional(String nationality, String malumoti, String malumotiboyichamutaxasisligi, String ilmiydarajasi,String ilmiyunvon, String chettillari, String davlatmukofotibilantaqdirlanganligiqanaqa, String saylovorganiazosi, String partiyaviyligi, String tamomlaganjoyi, String harbiyunvoni, String mehnatfaoliyati, Employee employee) {
        this.nationality = nationality;
        this.malumoti = malumoti;
        this.malumotiboyichamutaxasisligi = malumotiboyichamutaxasisligi;
        this.ilmiydarajasi = ilmiydarajasi;
        this.ilmiyunvon = ilmiyunvon;
        this.chettillari = chettillari;
        this.davlatmukofotibilantaqdirlanganligiqanaqa = davlatmukofotibilantaqdirlanganligiqanaqa;
        this.saylovorganiazosi = saylovorganiazosi;
        this.partiyaviyligi = partiyaviyligi;
        this.tamomlaganjoyi = tamomlaganjoyi;
        this.harbiyunvoni = harbiyunvoni;
        this.mehnatfaoliyati = mehnatfaoliyati;
        this.employee = employee;
    }
}

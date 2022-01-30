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

    private String nationality;                                     //1
    private String malumoti;//2
    private String malumotiboyichamutaxasisligi;//3
    private String ilmiydarajasi;//4
    private String chettillari;//5
    private String davlatmukofotibilantaqdirlanganligiqanaqa;//6
    private String saylovorganiazosi;//7
    private String partiyaviyligi;//8
    private String tamomlaganjoyi;//9
    private String harbiyunvoni;//10
    private String mehnatfaoliyati;//11
    private String ilmiyunvon;//12
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















//package com.example.employeecontrol.model;
//
//import lombok.*;
//import org.hibernate.annotations.OnDelete;
//import org.hibernate.annotations.OnDeleteAction;
//
//import javax.persistence.*;
//import java.util.List;
//
//@Data
//@Entity
//@AllArgsConstructor
//@NoArgsConstructor
//public class EmployeeAdditional {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    private String nationality;                                     //1
//    private String malumoti;//2
//    private String malumotiboyichamutaxasisligi;//3
//    private String ilmiydarajasi;//4
//    private String chettillari;//5
//    private String davlatmukofotibilantaqdirlanganligiqanaqa;//6
//    private String saylovorganiazosi;//7
//    private String partiyaviyligi;//8
//    private String tamomlaganjoyi;//9
//    private String harbiyunvoni;//10
////    @OneToMany
////    private List<MehnatFaoliyati> mehnatfaoliyati;//11
//    private String ilmiyunvon;//12
//    @OnDelete(action = OnDeleteAction.CASCADE)
//    @OneToOne
//    private Employee employee;
//
//    public EmployeeAdditional(String nationality, String malumoti, String malumotiboyichamutaxasisligi, String ilmiydarajasi,String ilmiyunvon, String chettillari, String davlatmukofotibilantaqdirlanganligiqanaqa, String saylovorganiazosi, String partiyaviyligi, String tamomlaganjoyi, String harbiyunvoni,/* List<MehnatFaoliyati> mehnatfaoliyati,*/ Employee employee) {
//        this.nationality = nationality;
//        this.malumoti = malumoti;
//        this.malumotiboyichamutaxasisligi = malumotiboyichamutaxasisligi;
//        this.ilmiydarajasi = ilmiydarajasi;
//        this.ilmiyunvon = ilmiyunvon;
//        this.chettillari = chettillari;
//        this.davlatmukofotibilantaqdirlanganligiqanaqa = davlatmukofotibilantaqdirlanganligiqanaqa;
//        this.saylovorganiazosi = saylovorganiazosi;
//        this.partiyaviyligi = partiyaviyligi;
//        this.tamomlaganjoyi = tamomlaganjoyi;
//        this.harbiyunvoni = harbiyunvoni;
//       // this.mehnatfaoliyati = mehnatfaoliyati;
//        this.employee = employee;
//    }
//}

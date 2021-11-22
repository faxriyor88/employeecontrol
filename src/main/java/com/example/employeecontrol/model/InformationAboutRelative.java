package com.example.employeecontrol.model;

import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.UUID;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class InformationAboutRelative {
    @Id
    @GeneratedValue
    private UUID id;
    private String name;
    private String relativefullname;
    private String birthdayandbirthofplace;
    private String ishjoyivalavozimi;
    private String turarjoyi;
    @OnDelete(action = OnDeleteAction.CASCADE)
    @ManyToOne
    private Employee employee;

    public InformationAboutRelative(String name, String relativefullname, String birthdayandbirthofplace, String ishjoyivalavozimi, String turarjoyi, Employee employee) {
        this.name = name;
        this.relativefullname = relativefullname;
        this.birthdayandbirthofplace = birthdayandbirthofplace;
        this.ishjoyivalavozimi = ishjoyivalavozimi;
        this.turarjoyi = turarjoyi;
        this.employee = employee;
    }
}

package com.example.employeecontrol.model;

import lombok.*;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String companyname;
    @ManyToOne
    private Region region;

    public Company(String companyname, Region region) {
        this.companyname = companyname;
        this.region = region;
    }


}

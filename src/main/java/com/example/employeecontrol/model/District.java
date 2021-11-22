package com.example.employeecontrol.model;

import lombok.*;

import javax.persistence.*;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class District {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String districtname;
    @ManyToOne
    private Region region;

    public District(String districtname, Region region) {
        this.districtname = districtname;
        this.region = region;
    }
}

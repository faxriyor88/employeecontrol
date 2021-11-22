package com.example.employeecontrol.model;

import com.example.employeecontrol.model.absentity.AbstractEntity;
import com.example.employeecontrol.model.enums.Permission;
import lombok.*;

import javax.persistence.*;
import java.util.List;
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Role extends AbstractEntity {
    @Column(nullable = false)
    private String name;
    @ElementCollection
    @Enumerated(EnumType.STRING)
    private List<Permission> permission;

}

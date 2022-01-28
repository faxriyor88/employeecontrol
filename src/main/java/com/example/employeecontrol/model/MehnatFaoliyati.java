package com.example.employeecontrol.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.UUID;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class MehnatFaoliyati {
    @Id
    @GeneratedValue
    private UUID id;
    private String text;
    @OnDelete(action = OnDeleteAction.CASCADE)
    @ManyToOne
    private Employee employee;

    public MehnatFaoliyati(String text, Employee employee) {
        this.text = text;
        this.employee = employee;
    }
}

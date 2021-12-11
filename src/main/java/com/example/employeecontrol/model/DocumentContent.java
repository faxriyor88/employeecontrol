package com.example.employeecontrol.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class DocumentContent {
    @Id
    @GeneratedValue
    private UUID uuid;
    private byte[] bytes;
    private String originalFileName;
    private String name;
    @OnDelete(action = OnDeleteAction.CASCADE)
    @OneToOne
    private DocumentEffector documentEffector;

    public DocumentContent(byte[] bytes, String originalFileName, String name, DocumentEffector documentEffector) {
        this.bytes = bytes;
        this.originalFileName = originalFileName;
        this.name = name;
        this.documentEffector = documentEffector;
    }
}

package com.example.employeecontrol.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
public class Attachment {
    @Id
    @GeneratedValue
    private UUID id;
    private String fileOriginalname;
    private String content_type;
    private String saveimagename;
    private String imageUrl;
    @OnDelete(action = OnDeleteAction.CASCADE)
    @OneToOne
    private Employee employee;

    public Attachment(String fileOriginalname, String content_type, String saveimagename, String imageUrl, Employee employee) {
        this.fileOriginalname = fileOriginalname;
        this.content_type = content_type;
        this.saveimagename = saveimagename;
        this.imageUrl = imageUrl;
        this.employee = employee;
    }
}

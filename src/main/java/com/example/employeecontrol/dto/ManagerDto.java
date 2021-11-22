package com.example.employeecontrol.dto;


import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ManagerDto {
    private String name;
    private String surname;
    private String username;
    private String password;
    private Integer roleId;
    private Integer companyId;
    private boolean enabled;
}

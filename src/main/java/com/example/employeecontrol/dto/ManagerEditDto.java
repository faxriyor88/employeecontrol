package com.example.employeecontrol.dto;

import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ManagerEditDto {
    private String name;
    private String surname;
    private String username;
    private String password;
}

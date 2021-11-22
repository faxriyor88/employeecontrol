package com.example.employeecontrol.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Getter
@Service
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse {
    private String  message;
    private boolean success;
}

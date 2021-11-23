package com.example.employeecontrol.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UploadImageResponse {
    private ApiResponse response;
    private String imageUrl;
    private String savefileimage;
}

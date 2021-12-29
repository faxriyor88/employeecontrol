package com.example.employeecontrol.controller;

import com.example.employeecontrol.aop.CheckPermission;
import com.example.employeecontrol.jwt.JwtFilter;
import com.example.employeecontrol.model.enums.Permission;
import com.example.employeecontrol.response.ApiResponse;
import com.example.employeecontrol.service.AttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/api/download")
public class DownloadEmployeeAboutInformationController {
    @Autowired
    AttachmentService attachmentService;
    @Autowired
    JwtFilter jwtFilter;

    //======== Xodim ma'lumotlarini docx qilib yuklab olish ======
    @CheckPermission(permission = "DOWNLOAD", permission1 = "DOWNLOAD_REGION")
    @GetMapping("/employeeaboutinformation/{id}")
    public void downloadEmployee(@PathVariable UUID id, HttpServletResponse response) throws IOException {
        ApiResponse apiResponse = attachmentService.downloadEmployee(id, response);
        if (apiResponse.isSuccess()) {
            System.out.println(apiResponse.getMessage());
            response.setStatus(201);
        } else {
            System.out.println(apiResponse.getMessage());
            response.setStatus(404);
        }
    }
//    @CheckPermission(permission = "DOWNLOAD", permission1 = "DOWNLOAD_REGION")
//    @GetMapping("/employeeaboutinformation/{id}")
//    public ResponseEntity<?> downloadEmployee(@PathVariable UUID id, HttpServletResponse response) throws IOException {
//        ApiResponse apiResponse = attachmentService.downloadEmployee(id, response);
//        if (apiResponse.isSuccess()) {
//            return ResponseEntity.status(201).body(apiResponse.getMessage());
//        } else {
//            return ResponseEntity.status(404).body(apiResponse.getMessage());
//        }
//    }
}

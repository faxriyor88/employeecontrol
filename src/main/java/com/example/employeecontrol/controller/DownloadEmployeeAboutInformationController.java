package com.example.employeecontrol.controller;

import com.example.employeecontrol.aop.CheckPermission;
import com.example.employeecontrol.model.enums.Permission;
import com.example.employeecontrol.response.ApiResponse;
import com.example.employeecontrol.service.AttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@RestController
@RequestMapping("/api/download")
public class DownloadEmployeeAboutInformationController {
    @Autowired
    AttachmentService attachmentService;

    @CheckPermission(permission = "DOWNLOAD",permission1 = "DOWNLOAD_REGION")
    @GetMapping("/employeeaboutinformation/{id}")
    public void downloadEmployee(@PathVariable UUID id, HttpServletResponse response){
        boolean b = attachmentService.downloadEmployee(id, response);
       if (b){
           response.setStatus(201);
       }else {
           response.setStatus(404);
       }
    }
}

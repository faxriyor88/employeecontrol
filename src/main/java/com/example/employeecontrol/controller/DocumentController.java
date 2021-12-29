package com.example.employeecontrol.controller;


import com.example.employeecontrol.aop.CheckPermission;
import com.example.employeecontrol.dto.DocumentEffectorDto;
import com.example.employeecontrol.dto.DocumentEffectorDtoResponse;
import com.example.employeecontrol.jwt.JwtFilter;
import com.example.employeecontrol.response.ApiResponse;
import com.example.employeecontrol.service.DocumentEffectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@CrossOrigin(origins = "https://empproba.herokuapp.com", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class DocumentController {
    @Autowired
    DocumentEffectorService documentEffectorService;
    @Autowired
    JwtFilter jwtFilter;

    //==== Document qo'shish ====
    @CheckPermission(permission = "ADD",permission1 = "ADD_REGION")
    @PostMapping("/addocument")
    public ResponseEntity<?> addDocument(@RequestPart MultipartFile file, @RequestPart DocumentEffectorDto documentEffectorDto) throws IOException {
        ApiResponse apiResponse = documentEffectorService.addDocument(file, documentEffectorDto);
        return ResponseEntity.status(apiResponse.isSuccess()?201:404).body(apiResponse);
    }
    //==== Document ko'rish =====
    @CheckPermission(permission = "VIEW",permission1 ="VIEW_REGION")
    @GetMapping("/viewdocument/{pagenumber}")
    public ResponseEntity<?> viewDocument(@PathVariable Integer pagenumber,@RequestParam String deadlinetype) throws  IOException {
        Page<DocumentEffectorDtoResponse> page = documentEffectorService.viewDocument(pagenumber, deadlinetype);
    return ResponseEntity.ok(page);
    }
}

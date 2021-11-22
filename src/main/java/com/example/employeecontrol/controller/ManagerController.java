package com.example.employeecontrol.controller;

import com.example.employeecontrol.aop.CheckPermission;
import com.example.employeecontrol.dto.ManagerDto;
import com.example.employeecontrol.dto.ManagerEditDto;
import com.example.employeecontrol.model.Manager;
import com.example.employeecontrol.repository.ManagerRepository;
import com.example.employeecontrol.response.ApiResponse;
import com.example.employeecontrol.service.EmployeeService;
import com.example.employeecontrol.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/manager")
public class ManagerController {

    @Autowired
    ManagerService managerService;
    @Autowired
    EmployeeService employeeService;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    ManagerRepository managerRepository;

    //Manager qo'shish
    @CheckPermission(permission = "ADD", permission1 = "1")
    @PostMapping("/addmanager")
    public ResponseEntity<?> addManager(@RequestBody ManagerDto managerDto) {
        ApiResponse add = managerService.add(managerDto);
        return ResponseEntity.status(add.isSuccess() ? 201 : 208).body(add);
    }

    // Managerni tahrirlash
    @CheckPermission(permission = "EDIT", permission1 = "EDIT_REGION")
    @PostMapping("/editmanager/{id}")
    public ResponseEntity<?> editManager(@RequestBody ManagerEditDto managerEditDto, @PathVariable Integer id) {
        Optional<Manager> optionalManager = managerRepository.findById(id);
        if(managerRepository.existsById(id)){
        Manager manager= optionalManager.get();
        if (manager.getUsername().equals(managerEditDto.getUsername())){
            if (manager.getPassword().equals(passwordEncoder.encode(managerEditDto.getPassword()))){
                manager.setSurname(managerEditDto.getSurname());
                manager.setPassword(passwordEncoder.encode(managerEditDto.getPassword()));
                manager.setUsername(managerEditDto.getUsername());
                manager.setName(managerEditDto.getName());
                managerRepository.save(manager);
                return ResponseEntity.status(201).body("Yangilandi");
            }
        }else {
            if(!managerRepository.existsByUsername(managerEditDto.getUsername())){
                if (!managerRepository.existsByPassword(passwordEncoder.encode(managerEditDto.getPassword()))){
                    manager.setSurname(managerEditDto.getSurname());
                    manager.setPassword(passwordEncoder.encode(managerEditDto.getPassword()));
                    manager.setUsername(managerEditDto.getUsername());
                    manager.setName(managerEditDto.getName());
                    managerRepository.save(manager);
                    return ResponseEntity.status(201).body("Yangilandi");
                }
                return ResponseEntity.status(404).body("Bunday password tanlayolmaysiz");
            }
            return ResponseEntity.status(404).body("Bunday username tanlayolmaysiz");
        }
    }
        return ResponseEntity.status(404).body("Bunday manager topilmadi");
    }
}

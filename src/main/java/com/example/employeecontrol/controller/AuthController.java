package com.example.employeecontrol.controller;

import com.example.employeecontrol.dto.LoginDTO;
import com.example.employeecontrol.jwt.JwtProwider;
import com.example.employeecontrol.response.ApiResponse;
import io.jsonwebtoken.JwtBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "https://empproba.herokuapp.com", maxAge = 3600)
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtProwider  jwtProwider;

    // Boshqaruvchini sistemaga kirishi
    @PostMapping("/login")
    public ResponseEntity<?> loginToSystem(@RequestBody LoginDTO loginDTO){
       try {
           authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getUsername(),loginDTO.getPassword()));
           String token = jwtProwider.generateToken(loginDTO.getUsername());
          return ResponseEntity.ok(token);
       }catch (Exception e){
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Bunday boshqaruvchi topilmadi");
       }

    }
}

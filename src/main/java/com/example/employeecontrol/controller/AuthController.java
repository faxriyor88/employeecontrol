package com.example.employeecontrol.controller;

import com.example.employeecontrol.dto.LoginDTO;
import com.example.employeecontrol.jwt.JwtProwider;
import com.google.gson.Gson;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.InputStreamReader;


@RestController
@RequestMapping("/api")

public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtProwider  jwtProwider;

    // Boshqaruvchini sistemaga kirishi
    @PostMapping("/login")
    public ResponseEntity<?> loginToSystem(HttpServletRequest request/*@RequestBody LoginDTO loginDTO*/){
       try {

           Gson gson=new Gson();
           Part login = request.getPart("login");
           LoginDTO loginDTO = gson.fromJson(new InputStreamReader(login.getInputStream()), LoginDTO.class);

           authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getUsername(),loginDTO.getPassword()));
           String token = jwtProwider.generateToken(loginDTO.getUsername());
           System.out.println(token);
          return ResponseEntity.ok(token);
       }catch (Exception e){
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Bunday boshqaruvchi topilmadi");
       }

    }
}

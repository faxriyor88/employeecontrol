package com.example.employeecontrol.service;

import com.example.employeecontrol.model.Manager;
import com.example.employeecontrol.repository.ManagerRepository;
import com.example.employeecontrol.repository.RoleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService implements UserDetailsService {

    @Autowired
    ManagerRepository managerRepository;
    @Autowired
    RoleRepository roleRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Manager> user = managerRepository.findByUsername(username);
        return user.orElseThrow(() -> new UsernameNotFoundException("Bunday username topilmadi "));
    }



}

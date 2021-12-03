package com.example.employeecontrol.service;

import com.example.employeecontrol.dto.ManagerDto;
import com.example.employeecontrol.model.Company;
import com.example.employeecontrol.model.Manager;
import com.example.employeecontrol.model.Role;
import com.example.employeecontrol.repository.CompanyRepository;
import com.example.employeecontrol.repository.ManagerRepository;
import com.example.employeecontrol.repository.RoleRepository;
import com.example.employeecontrol.response.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ManagerService {
    @Autowired
    ManagerRepository managerRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    CompanyRepository companyRepository;

    public ApiResponse add(ManagerDto managerDto) {
        Optional<Role> optionalRole = roleRepository.findById(managerDto.getRoleId());
        if (optionalRole.isPresent()) {
            Role role = optionalRole.get();
            if (optionalRole.get().getName().equals("SUPERADMIN")) {
                Manager manager = new Manager(managerDto.getName(), managerDto.getSurname(), managerDto.getUsername(), passwordEncoder.encode(managerDto.getPassword()), role, null, managerDto.isEnabled());
                managerRepository.save(manager);
                return new ApiResponse("Saqlandi", true);
            } else {
                Optional<Company> optionalCompany = companyRepository.findById(managerDto.getCompanyId());
                if (optionalCompany.isPresent()) {
                    Company company = optionalCompany.get();

                    Manager manager = new Manager(managerDto.getName(), managerDto.getSurname(), managerDto.getUsername(), passwordEncoder.encode(managerDto.getPassword()), role, company, managerDto.isEnabled());
                    managerRepository.save(manager);
                    return new ApiResponse("Saqlandi", true);
                } else {
                    return new ApiResponse("Saqlanmadi", false);
                }
            }
        }

        return new ApiResponse("Saqlanmadii", false);

    }
}

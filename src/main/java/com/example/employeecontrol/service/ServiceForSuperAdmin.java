package com.example.employeecontrol.service;

import com.example.employeecontrol.dto.*;
import com.example.employeecontrol.model.*;
import com.example.employeecontrol.model.enums.Permission;
import com.example.employeecontrol.repository.*;
import com.example.employeecontrol.response.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ServiceForSuperAdmin implements ServiceForAdminMethods {
    @Autowired
    RegionRepository regionRepository;
    @Autowired
    EmployeeService employeeService;
    @Autowired
    DistrictRepository districtRepository;
    @Autowired
    CompanyRepository companyRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    ManagerRepository managerRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    //================Insert===========
    @Override
    public ApiResponse insertRegion(List<Region> regions) {
        if (employeeService.getManagerInSystem().getRole().getName().equals("SUPERADMIN")) {
            try {
                regionRepository.saveAll(regions);
                return new ApiResponse("Saqlandi reg", true);
            } catch (Exception e) {
                return new ApiResponse("Saqlanmadi reg", false);
            }
        }
        return new ApiResponse("Sizda bunday huquq yo'q", false);
    }

    @Override
    public ApiResponse insertDistrict(List<DistrictDTO> districtDTOS) {
        if (employeeService.getManagerInSystem().getRole().getName().equals("SUPERADMIN")) {
            try {
                List<District> districts=new ArrayList<>();
                for (DistrictDTO d:districtDTOS) {
                  districts.add(new District(d.getDistrictname(), regionRepository.getById(d.getRegionId())));
                }
                districtRepository.saveAll(districts);
                return new ApiResponse("Saqlandi dis", true);
            } catch (Exception e) {
                return new ApiResponse("Saqlanmadi dis", false);
            }
        }
        return new ApiResponse("Sizda bunday huquq yo'q", false);
    }

    @Override
    public ApiResponse insertCompany(List<CompanyDTO> companyDTOS) {
        if (employeeService.getManagerInSystem().getRole().getName().equals("SUPERADMIN")) {
            try {
                List<Company> companies=new ArrayList<>();
                for (CompanyDTO c:companyDTOS) {
                    companies.add(new Company(c.getCompanyname(), regionRepository.getById(c.getRegionId())));
                }
                companyRepository.saveAll(companies);
                return new ApiResponse("Saqlandi com", true);
            } catch (Exception e) {
                return new ApiResponse("Saqlanmadi com", false);
            }
        }
        return new ApiResponse("Sizda bunday huquq yo'q", false);
    }

    @Override
    public ApiResponse insertRole(List<RoleDTO> roleDTOS) {
        try {
                List<Role> roles=new ArrayList<>();
                for (RoleDTO r :roleDTOS) {
                    Role role=new Role();
                   if (r.getRolename().equals("SUPERADMIN")||r.getRolename().equals("DIRECTOR")){
                       List<Permission> p=new ArrayList<>();
                       p.add(Permission.ADD);
                       p.add(Permission.EDIT);
                       p.add(Permission.VIEW);
                       p.add(Permission.DELETE);
                       p.add(Permission.DOWNLOAD);
                       role.setPermission(p);
                       role.setName(r.getRolename());
                   }
                    if (r.getRolename().equals("REGION")){
                        List<Permission> p=new ArrayList<>();
                        p.add(Permission.ADD_REGION);
                        p.add(Permission.EDIT_REGION);
                        p.add(Permission.VIEW_REGION);
                        p.add(Permission.DELETE_REGION);
                        p.add(Permission.DOWNLOAD_REGION);
                        role.setPermission(p);
                        role.setName(r.getRolename());
                    }
                    if (r.getRolename().equals("DISTRICT")){
                        List<Permission> p=new ArrayList<>();
                        p.add(Permission.ADD_DISTRICT);
                        p.add(Permission.EDIT_DISTRICT);
                        p.add(Permission.VIEW_DISTRICT);
                        p.add(Permission.DELETE_DISTRICT);
                        p.add(Permission.DOWNlOAD_DISTRICT);
                        role.setPermission(p);
                        role.setName(r.getRolename());
                    }

                   roles.add(role);
                }
                roleRepository.saveAll(roles);
                return new ApiResponse("Saqlandi role", true);
            } catch (Exception e) {
                return new ApiResponse("Saqlanmadi reg", false);
            }
    }


    //=======================Get===========================

    @Override
    public List<Region> getAllRegions() {
        return regionRepository.findAll();
    }

    @Override
    public List<District> getAllDistricts() {
        return districtRepository.findAll();
    }

    @Override
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public List<Manager> getAllManagers() {
        return managerRepository.findAll();
    }

    //===========================Update================================

    @Override
    public ApiResponse updateRegion(Integer id, Region region) {
        Optional<Region> optionalRegion = regionRepository.findById(id);
        if (optionalRegion.isPresent()) {
            Region region1 = optionalRegion.get();
            region1.setName(region.getName());
            regionRepository.save(region1);
            return new ApiResponse("Yangilandi", true);
        }

        return new ApiResponse("Bunday region topilmadi", false);
    }

    @Override
    public ApiResponse updateDistrict(Integer id, DistrictDTO districtDTO) {
        Optional<District> optionalDistrict = districtRepository.findById(id);
        if (optionalDistrict.isPresent()) {
            Optional<Region> optionalRegion = regionRepository.findById(districtDTO.getRegionId());
            if (optionalRegion.isPresent()) {
                District district = optionalDistrict.get();
                district.setDistrictname(districtDTO.getDistrictname());
                district.setRegion(optionalRegion.get());
                districtRepository.save(district);
                return new ApiResponse("Yangilandi", true);
            }

            return new ApiResponse("Bunday region topilmadi", false);
        }
        return new ApiResponse("Bunaday district topilmadi", false);
    }

    @Override
    public ApiResponse updateCompany(Integer id, CompanyDTO companyDTO) {
        Optional<Company> optionalCompany = companyRepository.findById(id);
        if (optionalCompany.isPresent()) {
            Optional<Region> optionalRegion = regionRepository.findById(companyDTO.getRegionId());
            if (optionalRegion.isPresent()) {
                Company company = optionalCompany.get();
                company.setCompanyname(company.getCompanyname());
                company.setRegion(optionalRegion.get());
                companyRepository.save(company);
                return new ApiResponse("Yangilandi",true);
            }
            return new ApiResponse("Bunday District topilmadi",false);
        }
        return new ApiResponse("Bunday Company topilmadi ",false);
    }

    @Override
    public ApiResponse updateRole(Integer id, RoleDTO roleDTO) {
        Optional<Role> optionalRole = roleRepository.findById(id);
        if (optionalRole.isPresent()){
            Role role= optionalRole.get();
            role.setName(role.getName());
            return new ApiResponse("Yangilandi",true);
        }
        return new ApiResponse("Bunday role topilmadi",false);
    }

    @Override
    public ApiResponse updateManager(Integer id, ManagerDto managerDto) {
        Optional<Manager> optionalManager = managerRepository.findById(id);
        if (optionalManager.isPresent()){
            Optional<Company> optionalCompany = companyRepository.findById(managerDto.getCompanyId());
            if (optionalCompany.isPresent()) {
                Optional<Role> optionalRole = roleRepository.findById(managerDto.getRoleId());
                if (optionalRole.isPresent()) {
                    Manager manager = optionalManager.get();

                    manager.setCompany(optionalCompany.get());
                    manager.setName(managerDto.getName());
                    manager.setEnabled(managerDto.isEnabled());
                    manager.setPassword(passwordEncoder.encode(managerDto.getPassword()));
                    manager.setRole(optionalRole.get());
                    manager.setUsername(managerDto.getUsername());
                    manager.setSurname(managerDto.getSurname());
                    managerRepository.save(manager);
                    return new ApiResponse("Yangilandi",true);
                }else {
                    return new ApiResponse("Bunday Role topilmadi",false);
                }
            }else {
                return new ApiResponse("Bunday company topilmadi",false);
            }
        }else {
            return new ApiResponse("Bunday manager topilmadi",false);
        }
    }

    //============= DELETE ===========
    @Override
    public ApiResponse deleteRegion(Integer id) {
        return null;
    }

    @Override
    public ApiResponse deleteDistrict(Integer id) {
        return null;
    }

    @Override
    public ApiResponse deleteCompany(Integer id) {
        return null;
    }

    @Override
    public ApiResponse deleteRole(Integer id) {
        return null;
    }

    @Override
    public ApiResponse deleteManager(Integer id) {
        return null;
    }
}
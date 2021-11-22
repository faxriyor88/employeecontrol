package com.example.employeecontrol.service;

import com.example.employeecontrol.dto.CompanyDTO;
import com.example.employeecontrol.dto.DistrictDTO;
import com.example.employeecontrol.dto.ManagerDto;
import com.example.employeecontrol.dto.RoleDTO;
import com.example.employeecontrol.model.*;
import com.example.employeecontrol.response.ApiResponse;

import java.util.List;

public interface ServiceForAdminMethods {

   ApiResponse insertRegion(List<Region> regions);
   ApiResponse insertDistrict(List<DistrictDTO> districtDTOS);
   ApiResponse insertCompany(List<CompanyDTO> companyDTOS);
   ApiResponse insertRole(List<RoleDTO> roleDTOs);


   List<Region> getAllRegions();
   List<District> getAllDistricts();
   List<Company> getAllCompanies();
   List<Role> getAllRoles();
   List<Manager> getAllManagers();

   ApiResponse updateRegion(Integer id,Region region);
   ApiResponse updateDistrict(Integer id, DistrictDTO districtDTO);
   ApiResponse updateCompany(Integer id, CompanyDTO companyDTO);
   ApiResponse updateRole(Integer id, RoleDTO roleDTO);
   ApiResponse updateManager(Integer id, ManagerDto managerDto);

   ApiResponse deleteRegion(Integer id);
   ApiResponse deleteDistrict(Integer id);
   ApiResponse deleteCompany(Integer id);
   ApiResponse deleteRole(Integer id);
   ApiResponse deleteManager(Integer id);

}

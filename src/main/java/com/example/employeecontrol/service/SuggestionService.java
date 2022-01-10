package com.example.employeecontrol.service;

import com.example.employeecontrol.dto.CompanyDTO;
import com.example.employeecontrol.model.Company;
import com.example.employeecontrol.model.District;
import com.example.employeecontrol.model.Manager;
import com.example.employeecontrol.model.Region;
import com.example.employeecontrol.repository.CompanyRepository;
import com.example.employeecontrol.repository.DistrictRepository;
import com.example.employeecontrol.repository.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SuggestionService {

    @Autowired
    RegionRepository regionRepository;
    @Autowired
    DistrictRepository districtRepository;
    @Autowired
    EmployeeService employeeService;
    @Autowired
    CompanyRepository companyRepository;

    // Suggestion uchun region olish, xodim tug'ilgan joyi uchun
    public List<Region> getSuggesRegion() {
        return regionRepository.findAll();
    }

    // Suggestion uchun district olish, xodim tug'ilgan joyi uchun
    public List<District> getSuggesDistrict(Integer regionId) {
        return districtRepository.findAllByRegionId(regionId);
    }

    //Director va Region role uchun, Suggestion uchun region olish,xodim ish joyini tanlash uchun
    public List<CompanyDTO> getSuggesRegionForCompany() {
        Manager manager = employeeService.getManagerInSystem();
        List<CompanyDTO> companyDTOS = new ArrayList<>();
        if (manager.getRole().getName().equals("DIRECTOR")) {
            List<Company> all = companyRepository.findAll();
            for (Company c : all) {
                companyDTOS.add(new CompanyDTO(c.getCompanyname(), c.getRegion().getId()));
            }
            return companyDTOS;
        }
        if (manager.getRole().getName().equals("REGION")) {
            Optional<Company> company = companyRepository.findById(manager.getCompany().getId());
            if (company.isPresent()) {
                CompanyDTO companydto = new CompanyDTO(company.get().getCompanyname(), company.get().getRegion().getId());
                companyDTOS.add(companydto);
                return companyDTOS;
            }
            return new ArrayList<>();
        }
        return new ArrayList<>();
    }

}

package com.example.employeecontrol.dataloader;

import com.example.employeecontrol.model.*;
import com.example.employeecontrol.model.enums.Permission;
import com.example.employeecontrol.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    RegionRepository regionRepository;
    @Autowired
    DistrictRepository districtRepository;
    @Autowired
    ManagerRepository managerRepository;
    @Autowired
    CompanyRepository companyRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Value("${spring.datasource.initialization-mode}")
    private String initialMode;

    @Override
    public void run(String... args) throws Exception {
        if (initialMode.equals("always")) {
            //Role qo'shish
            List<Permission> list1 = new ArrayList<>();
            list1.add(Permission.ADD);
            list1.add(Permission.EDIT);
            list1.add(Permission.VIEW);
            list1.add(Permission.DELETE);
            list1.add(Permission.DOWNLOAD);
            List<Permission> list2 = new ArrayList<>();
            list2.add(Permission.ADD_REGION);
            list2.add(Permission.EDIT_REGION);
            list2.add(Permission.DELETE_REGION);
            list2.add(Permission.VIEW_REGION);
            list2.add(Permission.DOWNLOAD_REGION);
            Role role1 = new Role("SUPERADMIN", list1);
            Role role2 = new Role("DIRECTOR", list1);
            Role role3 = new Role("REGION", list2);
            List<Role> roles = new ArrayList<>();
            roles.add(role1);
            roles.add(role2);
            roles.add(role3);
            roleRepository.saveAll(roles);
            // Region qo'shish
            List<Region> regions = new ArrayList<>();
            Region region1 = new Region("Toshkent");
            Region region2 = new Region("Jizzax");
            Region region3 = new Region("Samarqand");
            Region region4 = new Region("Наманган");
            Region region5 = new Region("Tarmoq");
            regions.add(region1);
            regions.add(region2);
            regions.add(region3);
            regions.add(region4);
            regions.add(region5);
            regionRepository.saveAll(regions);
            //District qo'shish
            List<District> districts = new ArrayList<>();
            District district1 = new District("Yunusobod", region1);
            District district2 = new District("Sergeli", region1);
            District district3 = new District("Zomin", region2);
            District district4 = new District("Forish", region2);
            District district5 = new District("KattaQo'rg'on", region3);
            District district6 = new District("Jomboy", region3);
            District district7 = new District("Chust", region4);
            District district8 = new District("Olmos", region4);
            districts.add(district1);
            districts.add(district2);
            districts.add(district3);
            districts.add(district4);
            districts.add(district5);
            districts.add(district6);
            districts.add(district7);
            districts.add(district8);
            districtRepository.saveAll(districts);
            //Company qo'shish
            List<Company> companies = new ArrayList<>();
            Company company = new Company("Ўзбекистон Республикаси касаба уюшмалари Федерацияси Тошкент шаҳар кенгаши", region1);
            Company company1 = new Company("Ўзбекистон Республикаси касаба уюшмалари Федерацияси Наманган вилоят кенгаши", region4);
            Company company2 = new Company("Ўзбекистон авиаходимлар касаба уюшмаси Республика Кенгаши ", region5);
            companies.add(company);
            companies.add(company1);
            companies.add(company2);
            companyRepository.saveAll(companies);
            //Boshqaruvchi qo'shish
            List<Manager> managers = new ArrayList<>();
            Manager manager = new Manager("Super", "Superov", "tt", passwordEncoder.encode("tt"), role1, null, true);
            Manager manager1 = new Manager("Director", "Directorov", "ttd", passwordEncoder.encode("ttd"), role2, company, true);
            Manager manager2 = new Manager("Region1", "Regionov1", "ttr1", passwordEncoder.encode("ttr1"), role3, company1, true);
            Manager manager3 = new Manager("Region2", "Regionov2", "ttr2", passwordEncoder.encode("ttr2"), role3, company2, true);
            managers.add(manager);
            managers.add(manager1);
            managers.add(manager2);
            managers.add(manager3);
            managerRepository.saveAll(managers);

        }
    }
}
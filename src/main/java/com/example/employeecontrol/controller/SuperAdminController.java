package com.example.employeecontrol.controller;

import com.example.employeecontrol.aop.CheckPermission;
import com.example.employeecontrol.dto.CompanyDTO;
import com.example.employeecontrol.dto.DistrictDTO;
import com.example.employeecontrol.dto.ManagerDto;
import com.example.employeecontrol.dto.RoleDTO;
import com.example.employeecontrol.model.Region;
import com.example.employeecontrol.response.ApiResponse;
import com.example.employeecontrol.service.EmployeeService;
import com.example.employeecontrol.service.ServiceForSuperAdmin;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.List;

@RestController
@RequestMapping("/manag")
public class SuperAdminController {
    @Autowired
    ServiceForSuperAdmin serviceForSuperAdmin;
    @Autowired
    EmployeeService employeeService;

    @CheckPermission(permission = "ADD", permission1 = "1")
    @PostMapping("/addregion")
    public ResponseEntity<?> addreg(@RequestBody List<Region> regions) {
        if (employeeService.getManagerInSystem().getRole().getName().equals("SUPERADMIN")) {
            ApiResponse apiResponse = serviceForSuperAdmin.insertRegion(regions);
            return ResponseEntity.status(apiResponse.isSuccess() ? 201 : 404).body(apiResponse);
        }
        return ResponseEntity.status(404).body(new ApiResponse("Sizda bunday huquq yo'q", false));
    }

    @CheckPermission(permission = "ADD", permission1 = "1")
    @PostMapping("/adddistrict")
    public ResponseEntity<?> adddist(@RequestBody List<DistrictDTO> districtDTOS) {
        if (employeeService.getManagerInSystem().getRole().getName().equals("SUPERADMIN")) {
            ApiResponse apiResponse = serviceForSuperAdmin.insertDistrict(districtDTOS);
            return ResponseEntity.status(apiResponse.isSuccess() ? 201 : 404).body(apiResponse);
        }
        return ResponseEntity.status(404).body(new ApiResponse("Sizda bunday huquq yo'q", false));

    }

    @CheckPermission(permission = "ADD", permission1 = "1")
    @PostMapping("/addcompany")
    public ResponseEntity<?> addcom(@RequestBody List<CompanyDTO> companyDTOS) {
        if (employeeService.getManagerInSystem().getRole().getName().equals("SUPERADMIN")) {
        ApiResponse apiResponse = serviceForSuperAdmin.insertCompany(companyDTOS);
        return ResponseEntity.status(apiResponse.isSuccess() ? 201 : 404).body(apiResponse);
        }
        return ResponseEntity.status(404).body(new ApiResponse("Sizda bunday huquq yo'q", false));
    }

    @CheckPermission(permission = "ADD", permission1 = "1")
    @PostMapping("/addrole")
    public ResponseEntity<?> addrole(@RequestBody List<RoleDTO> roleDTOS) {
        if (employeeService.getManagerInSystem().getRole().getName().equals("SUPERADMIN")) {
        ApiResponse apiResponse = serviceForSuperAdmin.insertRole(roleDTOS);
        return ResponseEntity.status(apiResponse.isSuccess() ? 201 : 404).body(apiResponse);
        }
        return ResponseEntity.status(404).body(new ApiResponse("Sizda bunday huquq yo'q", false));
    }

    //============================================================
    @CheckPermission(permission = "VIEW", permission1 = "1")
    @GetMapping("/getregion")
    public ResponseEntity<?> getreg() {
        if (employeeService.getManagerInSystem().getRole().getName().equals("SUPERADMIN")) {
        return ResponseEntity.ok(serviceForSuperAdmin.getAllRegions());
        }
        return ResponseEntity.status(404).body(new ApiResponse("Sizda bunday huquq yo'q", false));

    }

    @CheckPermission(permission = "VIEW", permission1 = "1")
    @GetMapping("/getdistrict")
    public ResponseEntity<?> getdist() {
        if (employeeService.getManagerInSystem().getRole().getName().equals("SUPERADMIN")) {
        return ResponseEntity.ok(serviceForSuperAdmin.getAllDistricts());
        }
        return ResponseEntity.status(404).body(new ApiResponse("Sizda bunday huquq yo'q", false));
    }

    @CheckPermission(permission = "VIEW", permission1 = "1")
    @GetMapping("/getcompany")
    public ResponseEntity<?> getcom() {
        if (employeeService.getManagerInSystem().getRole().getName().equals("SUPERADMIN")) {
        return ResponseEntity.ok(serviceForSuperAdmin.getAllCompanies());
        }
        return ResponseEntity.status(404).body(new ApiResponse("Sizda bunday huquq yo'q", false));
    }

    @CheckPermission(permission = "VIEW", permission1 = "1")
    @GetMapping("/getrole")
    public ResponseEntity<?> getrole() {
        if (employeeService.getManagerInSystem().getRole().getName().equals("SUPERADMIN")) {
        return ResponseEntity.ok(serviceForSuperAdmin.getAllRoles());
        }
        return ResponseEntity.status(404).body(new ApiResponse("Sizda bunday huquq yo'q", false));
    }

    @CheckPermission(permission = "VIEW", permission1 = "1")
    @GetMapping("/getmanager")
    public ResponseEntity<?> getmanag() {
        if (employeeService.getManagerInSystem().getRole().getName().equals("SUPERADMIN")) {
        return ResponseEntity.ok(serviceForSuperAdmin.getAllManagers());
        }
        return ResponseEntity.status(404).body(new ApiResponse("Sizda bunday huquq yo'q", false));
    }
    //=======================================

    @CheckPermission(permission = "EDIT", permission1 = "1")
    @PutMapping("/editregion/{id}")
    public ResponseEntity<?> editreg(@PathVariable Integer id, @RequestBody Region region) {
        if (employeeService.getManagerInSystem().getRole().getName().equals("SUPERADMIN")) {
        ApiResponse apiResponse = serviceForSuperAdmin.updateRegion(id, region);
        return ResponseEntity.status(apiResponse.isSuccess() ? 201 : 404).body(apiResponse);
        }
        return ResponseEntity.status(404).body(new ApiResponse("Sizda bunday huquq yo'q", false));
    }

    @CheckPermission(permission = "EDIT", permission1 = "1")
    @PutMapping("/editdistrict/{id}")
    public ResponseEntity<?> editdis(@PathVariable Integer id, @RequestBody DistrictDTO districtDTO) {
        if (employeeService.getManagerInSystem().getRole().getName().equals("SUPERADMIN")) {
        ApiResponse apiResponse = serviceForSuperAdmin.updateDistrict(id, districtDTO);
        return ResponseEntity.status(apiResponse.isSuccess() ? 201 : 404).body(apiResponse);
        }
        return ResponseEntity.status(404).body(new ApiResponse("Sizda bunday huquq yo'q", false));
    }

    @CheckPermission(permission = "EDIT", permission1 = "1")
    @PutMapping("/editcompany/{id}")
    public ResponseEntity<?> editcom(@PathVariable Integer id, @RequestBody CompanyDTO companyDTO) {
        if (employeeService.getManagerInSystem().getRole().getName().equals("SUPERADMIN")) {
        ApiResponse apiResponse = serviceForSuperAdmin.updateCompany(id, companyDTO);
        return ResponseEntity.status(apiResponse.isSuccess() ? 201 : 404).body(apiResponse);
        }
        return ResponseEntity.status(404).body(new ApiResponse("Sizda bunday huquq yo'q", false));
    }

    @CheckPermission(permission = "EDIT", permission1 = "1")
    @PutMapping("/editrole/{id}")
    public ResponseEntity<?> editrole(@PathVariable Integer id, @RequestBody RoleDTO roleDTO) {
        if (employeeService.getManagerInSystem().getRole().getName().equals("SUPERADMIN")) {
        ApiResponse apiResponse = serviceForSuperAdmin.updateRole(id, roleDTO);
        return ResponseEntity.status(apiResponse.isSuccess() ? 201 : 404).body(apiResponse);
        }
        return ResponseEntity.status(404).body(new ApiResponse("Sizda bunday huquq yo'q", false));
    }

    @CheckPermission(permission = "EDIT", permission1 = "1")
    @PutMapping("/editmanager/{id}")
    public ResponseEntity<?> editmanag(@PathVariable Integer id, @RequestBody ManagerDto managerDto) {
        if (employeeService.getManagerInSystem().getRole().getName().equals("SUPERADMIN")) {
        ApiResponse apiResponse = serviceForSuperAdmin.updateManager(id, managerDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 201 : 404).body(apiResponse);
        }
        return ResponseEntity.status(404).body(new ApiResponse("Sizda bunday huquq yo'q", false));
    }

    @GetMapping("/countfilenumber")
    public String jk(){
        File file=new File("informationaboutemployee");
        File file1=new File("imagelocation");
        return "infro="+file.list().length+",  image="+file1.list().length;
    }
}

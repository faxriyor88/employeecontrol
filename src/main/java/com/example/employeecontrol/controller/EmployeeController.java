package com.example.employeecontrol.controller;

import com.example.employeecontrol.aop.CheckPermission;
import com.example.employeecontrol.dto.EmployeeAdditonalDTO;
import com.example.employeecontrol.dto.EmployeeDto;
import com.example.employeecontrol.jwt.JwtFilter;
import com.example.employeecontrol.model.Employee;
import com.example.employeecontrol.repository.EmployeeAdditionalRepository;
import com.example.employeecontrol.response.ApiResponse;
import com.example.employeecontrol.service.EmployeeService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import java.util.UUID;

@CrossOrigin(origins = "https://empproba.herokuapp.com", maxAge = 3600)
@RestController
@RequestMapping("/api/employee")
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;
    @Autowired
    EmployeeAdditionalRepository additionalRepository;
    @Autowired
    JwtFilter jwtFilter;

    // DIRECTOR VA REGION XODIM QO'SHISHI

    @CheckPermission(permission ="ADD",permission1 = "ADD_REGION")
    @PostMapping("/addemployee")
    public ResponseEntity<?> addEmployee(@RequestPart EmployeeDto employeeDto, @RequestPart MultipartFile image)throws IOException {
        ApiResponse apiResponse = employeeService.addEmployee(employeeDto,image);
        System.out.println(apiResponse.getMessage());
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.CREATED : HttpStatus.NOT_FOUND).body(apiResponse);
    }

    // DIRECTOR VA REGION XODIMLARNI KO'RISHI
    @CheckPermission(permission = "VIEW",permission1 ="VIEW_REGION" )
    @GetMapping("/getallemployee/{pagenumber}")
    public ResponseEntity<?> getAllEmployee(@PathVariable Integer pagenumber) {
        Page<Employee> allEmplDire = employeeService.getAllEmployee(pagenumber);
        return ResponseEntity.ok(allEmplDire);
    }

    // DIRECTOR VA REGION XODIMLARNI TAHRIRLASHI
    @CheckPermission(permission = "EDIT",permission1 = "EDIT_REGION")
    @PutMapping("/editemployee/{id}")
    public ResponseEntity<?> editEmployee(@PathVariable UUID id, @RequestPart EmployeeDto employeeDto,@RequestPart MultipartFile image) throws IOException {
        ApiResponse apiResponse = employeeService.editEmployee(id, employeeDto,image);
        return ResponseEntity.status(apiResponse.isSuccess()?201:404).body(apiResponse);
    }

    // DIRECTOR VA REGION XODIMLARNI O'CHIRISHI
    @CheckPermission(permission = "DELETE",permission1 = "DELETE_REGION")
    @DeleteMapping("/deleteemployee/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable UUID id){
        ApiResponse apiResponse = employeeService.deleteEmployee(id);
        return ResponseEntity.status(apiResponse.isSuccess()?200:404).body(apiResponse);
    }

    // DIRECTOR VA REGION XODIM HAQIDA QO'SHIMCHA MA'LUMOT OLISHI
    @CheckPermission(permission = "VIEW",permission1 = "VIEW_REGION")
    @GetMapping("/getemployeeadditional/{id}")
    public ResponseEntity<?> getEmployeeAdditional(@PathVariable UUID id){
        EmployeeAdditonalDTO employeeAdditional = employeeService.getEmployeeAdditional(id);
       // List<EmployeeAdditonalDTO> employeeAdditonalDTOS=new ArrayList<>();
       // employeeAdditonalDTOS.add(employeeAdditional);
        return  ResponseEntity.ok(employeeAdditional);
    }


    // Proba uchun
    @DeleteMapping("/deletenega/{id}")
    public String nega(@PathVariable Long id){
        try {
            additionalRepository.deleteById(id);

            return "O'chirildi";
        }catch (Exception e){
            return "O'chirilmadi";
        }
    }



//    MultipartFile image=null;
//    EmployeeDto employeeDto = new EmployeeDto();
//    Iterator<String> fileNames = request.getFileNames();
//        while (fileNames.hasNext()) {
//        MultipartFile loginDTO = request.getFile(fileNames.next());
//        if (loginDTO.getContentType().startsWith("imag")) {
//            image=loginDTO;
//        }
//        if (loginDTO.getContentType().startsWith("appli")){
//            System.out.println(loginDTO.getContentType()+"sdsd");
//            Gson gson = new Gson();
//            BufferedReader reader = new BufferedReader(new InputStreamReader(loginDTO.getInputStream()));
//            ArrayList<String> list = gson.fromJson(reader, ArrayList.class);
//
//            Iterator iterator = list.iterator();
//            while (iterator.hasNext()) {
//                String json = gson.toJson(iterator.next());
//                EmployeeDto employeeDtoreq = gson.fromJson(json, EmployeeDto.class);
//                employeeDto=employeeDtoreq;
//                System.out.println(employeeDto.getChettillari());
//            }
//        }
//    }
//        System.out.println(employeeDto);
//        if (image!=null){
//        System.out.println("nullmas");
//    }

}







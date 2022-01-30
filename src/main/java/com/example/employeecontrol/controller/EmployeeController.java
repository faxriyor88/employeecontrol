package com.example.employeecontrol.controller;

import com.example.employeecontrol.aop.CheckPermission;
import com.example.employeecontrol.dto.EmployeeAdditonalDTO;
import com.example.employeecontrol.dto.EmployeeDto;
import com.example.employeecontrol.dto.LoginDTO;
import com.example.employeecontrol.jwt.JwtFilter;
import com.example.employeecontrol.model.Employee;
import com.example.employeecontrol.repository.EmployeeAdditionalRepository;
import com.example.employeecontrol.response.ApiResponse;
import com.example.employeecontrol.service.EmployeeService;
import org.apache.http.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import java.util.UUID;


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
    @CheckPermission(permission = "ADD", permission1 = "ADD_REGION")
    @PostMapping("/addemployee")
    public ResponseEntity<?> addEmployee(@RequestBody EmployeeDto employeeDto/*, @RequestPart MultipartFile image*/) throws IOException {
        //  ApiResponse apiResponse = employeeService.addEmployee(employeeDto, image);

        // return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.CREATED : HttpStatus.NOT_FOUND).body(apiResponse);
        if (employeeDto != null) {
            return ResponseEntity.status(201).body(employeeDto.getFullname() + " ishlayapti");
        } else {
            return ResponseEntity.status(404).body("Ishlamayapti");
        }
    }

    // DIRECTOR VA REGION XODIMLARNI KO'RISHI
    @CheckPermission(permission = "VIEW", permission1 = "VIEW_REGION")
    @GetMapping("/getallemployee/{pagenumber}")
    public ResponseEntity<?> getAllEmployee(@PathVariable Integer pagenumber) {
        Page<Employee> allEmplDire = employeeService.getAllEmployee(pagenumber);
        return ResponseEntity.ok(allEmplDire);
    }


    //DIRECTOR VA REGION XODIMLARNI QIDIRISHI
    // 1.Ism bo'yicha qidirish
    @CheckPermission(permission = "VIEW", permission1 = "VIEW_REGION")
    @GetMapping("/findbyfullname")
    public ResponseEntity<?> findByFullName(@RequestParam String fullname) {
        return ResponseEntity.ok(employeeService.findByName(fullname));
    }

    //2.Company bo'yicha qidirish
    @CheckPermission(permission = "VIEW", permission1 = "VIEW_REGION")
    @GetMapping("/findbycompany")
    public ResponseEntity<?> findByCompany(@RequestParam String companyName) {
        return ResponseEntity.ok(employeeService.findByCompany(companyName));
    }

    //3.Lavozim bo'yicha qidirish
    @CheckPermission(permission = "VIEW", permission1 = "VIEW_REGION")
    @GetMapping("/findbyposition")
    public ResponseEntity<?> findByPosition(@RequestParam String positionName) {
        return ResponseEntity.ok(employeeService.findByPosition(positionName));
    }


    // DIRECTOR VA REGION XODIMLARNI TAHRIRLASHI
    @CheckPermission(permission = "EDIT", permission1 = "EDIT_REGION")
    @PutMapping("/editemployee/{id}")
    public ResponseEntity<?> editEmployee(@PathVariable UUID id, @RequestPart EmployeeDto employeeDto, @RequestPart MultipartFile image) throws IOException {
        ApiResponse apiResponse = employeeService.editEmployee(id, employeeDto, image);
        return ResponseEntity.status(apiResponse.isSuccess() ? 201 : 404).body(apiResponse);
    }

    // DIRECTOR VA REGION XODIMLARNI O'CHIRISHI
    @CheckPermission(permission = "DELETE", permission1 = "DELETE_REGION")
    @DeleteMapping("/deleteemployee/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable UUID id) {
        ApiResponse apiResponse = employeeService.deleteEmployee(id);
        System.out.println("Ishladi");
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 404).body(apiResponse);
    }

    // DIRECTOR VA REGION XODIM HAQIDA QO'SHIMCHA MA'LUMOT OLISHI
    @CheckPermission(permission = "VIEW", permission1 = "VIEW_REGION")
    @GetMapping("/getemployeeadditional/{id}")
    public ResponseEntity<?> getEmployeeAdditional(@PathVariable UUID id) {
        EmployeeAdditonalDTO employeeAdditional = employeeService.getEmployeeAdditional(id);
        // List<EmployeeAdditonalDTO> employeeAdditonalDTOS=new ArrayList<>();
        // employeeAdditonalDTOS.add(employeeAdditional);
        return ResponseEntity.ok(employeeAdditional);
    }


    // Proba uchun
    @CheckPermission(permission = "VIEW", permission1 = "VIEW_REGION")
    @GetMapping("/get")
    public String get(HttpServletResponse response) {
        response.setHeader("Get", "ishlayapti");
        System.out.println("GET");
        return "GETISHLADI";
    }
    // Proba uchun

    @CheckPermission(permission = "VIEW", permission1 = "VIEW_REGION")
    @DeleteMapping("/delete")
    public String delete(HttpServletResponse response) {
        System.out.println("DELETE");
        response.setHeader("Delete", "ishlayapti");
        return "DELETEISHLADI";
    }

    @CheckPermission(permission = "VIEW", permission1 = "VIEW_REGION")
    @PostMapping("/post")
    public String post(@RequestBody LoginDTO loginDTO, HttpServletResponse response) {
        System.out.println(loginDTO.getPassword());
        response.setHeader(loginDTO.getUsername(), loginDTO.getPassword());
        return "POSTISHLADI";
    }

    @CheckPermission(permission = "VIEW", permission1 = "VIEW_REGION")
    @PutMapping("/update")
    public String update(HttpServletResponse response) {
        System.out.println("UPDATE");
        response.setHeader("Update", "ishlayapti");
        return "PUTISHLADI";
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


//package com.example.employeecontrol.controller;
//
//
//import com.example.employeecontrol.aop.CheckPermission;
//import com.example.employeecontrol.dto.EmployeeAdditonalDTO;
//import com.example.employeecontrol.dto.EmployeeDto;
//import com.example.employeecontrol.dto.LoginDTO;
//import com.example.employeecontrol.jwt.JwtFilter;
//import com.example.employeecontrol.model.Employee;
//import com.example.employeecontrol.repository.EmployeeAdditionalRepository;
//import com.example.employeecontrol.response.ApiResponse;
//import com.example.employeecontrol.service.EmployeeService;
//import com.google.gson.Gson;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import org.springframework.data.domain.Page;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//import org.springframework.web.multipart.MultipartHttpServletRequest;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.Part;
//import java.io.IOException;
//
//import java.io.InputStreamReader;
//import java.util.UUID;
//
//
//@RestController
//@RequestMapping("/api/employee")
//public class EmployeeController {
//    @Autowired
//    EmployeeService employeeService;
//    @Autowired
//    EmployeeAdditionalRepository additionalRepository;
//    @Autowired
//    JwtFilter jwtFilter;
//
//    // DIRECTOR VA REGION XODIM QO'SHISHI
//    @CheckPermission(permission = "ADD", permission1 = "ADD_REGION")
//    @PostMapping("/addemployee")
//    public ResponseEntity<?> addEmployee(MultipartHttpServletRequest request/*@RequestPart EmployeeDto employeeDto, @RequestPart MultipartFile image*/) throws IOException, ServletException {
//        Gson gson=new Gson();
//        Part employeeDto1=request.getPart("employeeDto");
//        MultipartFile image = request.getFile("image");
//        EmployeeDto employeeDto = gson.fromJson(new InputStreamReader(employeeDto1.getInputStream()), EmployeeDto.class);
//        ApiResponse apiResponse= employeeService.addEmployee(employeeDto, image);
//        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.CREATED : HttpStatus.NOT_FOUND).body(apiResponse);
//    }
//
//    // DIRECTOR VA REGION XODIMLARNI KO'RISHI
//    @CheckPermission(permission = "VIEW", permission1 = "VIEW_REGION")
//    @GetMapping("/getallemployee/{pagenumber}")
//    public ResponseEntity<?> getAllEmployee(@PathVariable Integer pagenumber) {
//        Page<Employee> allEmplDire = employeeService.getAllEmployee(pagenumber);
//        return ResponseEntity.ok(allEmplDire);
//    }
//
//
//    //DIRECTOR VA REGION XODIMLARNI QIDIRISHI
//    // 1.Ism bo'yicha qidirish
//    @CheckPermission(permission = "VIEW",permission1 = "VIEW_REGION")
//    @GetMapping("/findbyfullname")
//    public ResponseEntity<?> findByFullName(@RequestParam String fullname){
//      return ResponseEntity.ok(employeeService.findByName(fullname));
//    }
//    //2.Company bo'yicha qidirish
//    @CheckPermission(permission = "VIEW",permission1 = "VIEW_REGION")
//    @GetMapping("/findbycompany")
//    public ResponseEntity<?> findByCompany(@RequestParam String companyName){
//        return ResponseEntity.ok(employeeService.findByCompany(companyName));
//    }
//    //3.Lavozim bo'yicha qidirish
//    @CheckPermission(permission = "VIEW", permission1 = "VIEW_REGION")
//    @GetMapping("/findbyposition")
//    public ResponseEntity<?> findByPosition(@RequestParam String positionName){
//        return ResponseEntity.ok(employeeService.findByPosition(positionName));
//    }
//
//
//    // DIRECTOR VA REGION XODIMLARNI TAHRIRLASHI
//    @CheckPermission(permission = "EDIT", permission1 = "EDIT_REGION")
//    @PutMapping("/editemployee/{id}")
//    public ResponseEntity<?> editEmployee(@PathVariable UUID id,MultipartHttpServletRequest request/* @RequestPart EmployeeDto employeeDto, @RequestPart MultipartFile image*/) throws IOException, ServletException {
//        Gson gson=new Gson();
//        Part part=request.getPart("employeeDto");
//        MultipartFile image= request.getFile("image");
//        EmployeeDto employeeDto = gson.fromJson(new InputStreamReader(part.getInputStream()), EmployeeDto.class);
//        ApiResponse apiResponse/*=new ApiResponse("hghg",true); */ =employeeService.editEmployee(id, employeeDto, image);
//        return ResponseEntity.status(apiResponse.isSuccess() ? 201 : 404).body(apiResponse);
//    }
//
//    // DIRECTOR VA REGION XODIMLARNI O'CHIRISHI
//    @CheckPermission(permission = "DELETE", permission1 = "DELETE_REGION")
//    @DeleteMapping("/deleteemployee/{id}")
//    public ResponseEntity<?> deleteEmployee(@PathVariable UUID id) {
//        ApiResponse apiResponse = employeeService.deleteEmployee(id);
//        System.out.println("Ishladi");
//        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 404).body(apiResponse);
//    }
//
//    // DIRECTOR VA REGION XODIM HAQIDA QO'SHIMCHA MA'LUMOT OLISHI
//    @CheckPermission(permission = "VIEW", permission1 = "VIEW_REGION")
//    @GetMapping("/getemployeeadditional/{id}")
//    public ResponseEntity<?> getEmployeeAdditional(@PathVariable UUID id) {
//        EmployeeAdditonalDTO employeeAdditional = employeeService.getEmployeeAdditional(id);
//        // List<EmployeeAdditonalDTO> employeeAdditonalDTOS=new ArrayList<>();
//        // employeeAdditonalDTOS.add(employeeAdditional);
//        return ResponseEntity.ok(employeeAdditional);
//    }
//
//
//    // Proba uchun
//    @CheckPermission(permission = "VIEW", permission1 = "VIEW_REGION")
//    @GetMapping("/get")
//    public String get(HttpServletResponse response) {
//        response.setHeader("Get","ishlayapti");
//        System.out.println("GET");
//        return "GETISHLADI";
//    }
//    // Proba uchun
//
//    @CheckPermission(permission = "VIEW", permission1 = "VIEW_REGION")
//    @DeleteMapping("/delete")
//    public String delete(HttpServletResponse response) {
//        System.out.println("DELETE");
//        response.setHeader("Delete","ishlayapti");
//        return "DELETEISHLADI";
//    }
//
//    @CheckPermission(permission = "VIEW", permission1 = "VIEW_REGION")
//    @PostMapping("/post")
//    public String post(@RequestBody LoginDTO loginDTO, HttpServletResponse response) {
//        System.out.println(loginDTO.getPassword());
//        response.setHeader(loginDTO.getUsername(), loginDTO.getPassword());
//        return "POSTISHLADI";
//    }
//
//    @CheckPermission(permission = "VIEW", permission1 = "VIEW_REGION")
//    @PutMapping("/update")
//    public String update(HttpServletResponse response) {
//        System.out.println("UPDATE");
//        response.setHeader("Update","ishlayapti");
//        return "PUTISHLADI";
//    }
//
//
////    MultipartFile image=null;
////    EmployeeDto employeeDto = new EmployeeDto();
////    Iterator<String> fileNames = request.getFileNames();
////        while (fileNames.hasNext()) {
////        MultipartFile loginDTO = request.getFile(fileNames.next());
////        if (loginDTO.getContentType().startsWith("imag")) {
////            image=loginDTO;
////        }
////        if (loginDTO.getContentType().startsWith("appli")){
////            System.out.println(loginDTO.getContentType()+"sdsd");
////            Gson gson = new Gson();
////            BufferedReader reader = new BufferedReader(new InputStreamReader(loginDTO.getInputStream()));
////            ArrayList<String> list = gson.fromJson(reader, ArrayList.class);
////
////            Iterator iterator = list.iterator();
////            while (iterator.hasNext()) {
////                String json = gson.toJson(iterator.next());
////                EmployeeDto employeeDtoreq = gson.fromJson(json, EmployeeDto.class);
////                employeeDto=employeeDtoreq;
////                System.out.println(employeeDto.getChettillari());
////            }
////        }
////    }
////        System.out.println(employeeDto);
////        if (image!=null){
////        System.out.println("nullmas");
////    }
//
//}
//
//
//
//
//
//

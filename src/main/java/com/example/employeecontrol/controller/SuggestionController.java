package com.example.employeecontrol.controller;

import com.example.employeecontrol.aop.CheckPermission;
import com.example.employeecontrol.service.SuggestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/suggestion")
public class SuggestionController {
    @Autowired
    SuggestionService suggestionService;

    // Suggestion uchun region olish, xodim tug'ilgan joyi uchun
    @CheckPermission(permission = "VIEW",permission1 = "VIEW_REGION")
    @GetMapping("/region")
    public ResponseEntity<?> getSugReg(){
        return ResponseEntity.ok(suggestionService.getSuggesRegion());
    }

    // Suggestion uchun district olish, xodim tug'ilgan joyi uchun
    @CheckPermission(permission = "VIEW",permission1 = "VIEW_REGION")
    @GetMapping("/district/{regionid}")
    public ResponseEntity<?> getSugDis(@PathVariable Integer regionid){
        return ResponseEntity.ok(suggestionService.getSuggesDistrict(regionid));
    }

    // get all district for suggestion, xodim tug'ilgan joyi uchun
    @CheckPermission(permission = "VIEW",permission1 = "VIEW_REGION")
    @GetMapping("/getalldistrict")
    public ResponseEntity<?> getSugAllDis(){
        return ResponseEntity.ok(suggestionService.getAllSugDistrict());
    }

    //Director va Region role uchun, Suggestion uchun company olish,xodim ish joyini tanlash uchun
    @CheckPermission(permission = "VIEW",permission1 = "VIEW_REGION")
    @GetMapping("/company")
    public ResponseEntity<?> getSugRegForCom(){
        return ResponseEntity.ok(suggestionService.getSuggesRegionForCompany());
    }

}

package com.example.server.controllers;


import com.example.server.entity.EngineeringStaff;
import com.example.server.service.EngineeringStaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/engineer")
public class EngineeringStaffController {

    @Autowired
    EngineeringStaffService engineeringStaffService;

    @PostMapping
    public ResponseEntity<?> createEngineer(@RequestBody EngineeringStaff entity){
        try {
            engineeringStaffService.create(entity);
            return ResponseEntity.ok("Engineer created");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error");
        }
    }
}

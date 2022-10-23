package com.example.server.controllers;


import com.example.server.dto.EngineerDto;
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
    public ResponseEntity<?> createEngineer(@RequestBody EngineerDto entity){
        try {
            engineeringStaffService.create(entity);
            return ResponseEntity.ok("Engineer created");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error");
        }
    }
    @GetMapping
    public  ResponseEntity<?> getById(@RequestParam Integer id){
        try {
            return ResponseEntity.ok(engineeringStaffService.getById(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error");
        }
    }
    @GetMapping("/all")
    public  ResponseEntity<?> showAll(){
        try {
            return ResponseEntity.ok(engineeringStaffService.getAll());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error");
        }
    }

    @DeleteMapping
    public ResponseEntity delete(@RequestParam Integer id){
        try {
            engineeringStaffService.delete(id);
            return ResponseEntity.ok("delete");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error");
        }
    }
}

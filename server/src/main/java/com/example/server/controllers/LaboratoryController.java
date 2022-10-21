package com.example.server.controllers;

import com.example.server.service.LaboratoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/laboratory")
public class LaboratoryController {
    @Autowired
    private LaboratoryService laboratoryService;

    @PostMapping
    public ResponseEntity<?> create(){
        try {
            laboratoryService.create();
            return ResponseEntity.ok("Laboratory created");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error");
        }
    }
    @GetMapping
    public ResponseEntity<?> getById(@RequestParam Integer id){
        try {
            return ResponseEntity.ok(laboratoryService.getById(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error");
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> getById(){
        try {
            return ResponseEntity.ok(laboratoryService.getAll());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error");
        }
    }

    @DeleteMapping
    public ResponseEntity delete(@RequestParam Integer id){
        try {
            laboratoryService.delete(id);
            return ResponseEntity.ok("delete");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error");
        }
    }
}

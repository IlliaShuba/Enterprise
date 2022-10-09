package com.example.server.controllers;

import com.example.server.service.LaboratoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}

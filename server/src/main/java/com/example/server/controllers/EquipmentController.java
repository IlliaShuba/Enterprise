package com.example.server.controllers;

import com.example.server.dto.EquipmentDto;
import com.example.server.entity.Equipment;
import com.example.server.service.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/equipment")
public class EquipmentController {
    @Autowired
    private EquipmentService equipmentService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody EquipmentDto entity, @RequestParam Integer labId){
        try {
            equipmentService.create(entity, labId);
            return ResponseEntity.ok("Equipment created");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error");
        }
    }
}

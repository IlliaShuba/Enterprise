package com.example.server.controllers;

import com.example.server.dto.EquipmentDto;
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

    @GetMapping
    public ResponseEntity<?> getById(@RequestParam Integer id){
        try {
            return ResponseEntity.ok(equipmentService.getById(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error");
        }
    }
    @GetMapping("/all")
    public ResponseEntity<?> getAll(){
        try {
            return ResponseEntity.ok(equipmentService.getAll());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error");
        }
    }

    @GetMapping("ware")
    public ResponseEntity<?> getByWareId(@RequestParam Integer id){
        try {
            return ResponseEntity.ok(equipmentService.getByWareId(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error");
        }
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@RequestParam Integer id){
        try {
            equipmentService.delete(id);
            return ResponseEntity.ok("delete");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error");
        }
    }
}

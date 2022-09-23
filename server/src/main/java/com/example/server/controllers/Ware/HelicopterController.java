package com.example.server.controllers.Ware;

import com.example.server.dto.HelicopterDto;
import com.example.server.service.Ware.HelicopterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/helicopter")
public class HelicopterController {
    @Autowired
    private HelicopterService helicopterService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody HelicopterDto entity){
        try {
            helicopterService.create(entity);
            return ResponseEntity.ok("Helicopter created");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error");
        }
    }

    @GetMapping
    public ResponseEntity getById(@RequestParam Integer id){
        try {
            return ResponseEntity.ok(helicopterService.getById(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error");
        }
    }
}

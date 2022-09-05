package com.example.server.controllers;

import com.example.server.entity.Brigade;
import com.example.server.service.BrigadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/brigade")
public class BrigadeController {

    @Autowired
    private BrigadeService brigadeService;

    @PostMapping
    public ResponseEntity createBrigade(@RequestBody Brigade entity, @RequestParam Integer areaId){
        try {
            brigadeService.createBrigade(entity, areaId);
            return ResponseEntity.ok("Brigade created");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error");
        }
    }
}

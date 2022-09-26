package com.example.server.controllers;

import com.example.server.entity.Tester;
import com.example.server.service.TesterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/tester")
public class TesterController {
    @Autowired
    private TesterService testerService;

    @PostMapping
    public ResponseEntity createWorker(@RequestBody Tester entity, @RequestParam Integer labId){
        try {
            testerService.create(entity, labId);
            return ResponseEntity.ok("Tester created");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error");
        }
    }

    @GetMapping("/all")
    public  ResponseEntity<?> showAll(){
        try {
            return ResponseEntity.ok(testerService.getAll());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error");
        }
    }
}

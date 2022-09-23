package com.example.server.controllers;

import com.example.server.entity.Worker;
import com.example.server.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/tester")
public class TesterController {
    @Autowired
    private WorkerService workerService;

    @PostMapping
    public ResponseEntity createWorker(@RequestBody Worker entity, @RequestParam Integer labId){
        try {
            workerService.create(entity, labId);
            return ResponseEntity.ok("Tester created");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error");
        }
    }
}

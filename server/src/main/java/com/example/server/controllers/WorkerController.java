package com.example.server.controllers;

import com.example.server.entity.Worker;
import com.example.server.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/worker")
public class WorkerController {

    @Autowired
    private WorkerService workerService;

    @PostMapping
    public ResponseEntity createWorker(@RequestBody Worker entity, @RequestParam Integer brigadeId){
        try {
            workerService.createWorker(entity, brigadeId);
            return ResponseEntity.ok("Brigade created");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error");
        }
    }
}

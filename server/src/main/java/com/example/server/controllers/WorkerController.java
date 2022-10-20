package com.example.server.controllers;

import com.example.server.dto.WorkerDto;
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
    public ResponseEntity createWorker(@RequestBody WorkerDto entity, @RequestParam Integer brigadeId){
        try {
            workerService.create(entity, brigadeId);
            return ResponseEntity.ok("Worker created");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error");
        }
    }

    @GetMapping("/all")
    public  ResponseEntity<?> showAll(){
        try {
            return ResponseEntity.ok(workerService.getAll());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error");
        }
    }

    @DeleteMapping
    public ResponseEntity delete(@RequestParam Integer id){
        try {
            workerService.delete(id);
            return ResponseEntity.ok("delete");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error");
        }
    }
}

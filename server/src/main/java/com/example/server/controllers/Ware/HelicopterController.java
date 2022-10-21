package com.example.server.controllers.Ware;

import com.example.server.dto.HelicopterDto;
import com.example.server.dto.Interval;
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
    @GetMapping("/all")
    public ResponseEntity<?> getAll(){
        try {
            return ResponseEntity.ok(helicopterService.getAll());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error");
        }
    }

    @PostMapping("/interval")
    public ResponseEntity<?> getByInterval(@RequestBody Interval interval){
        try {
            return ResponseEntity.ok(helicopterService.getByInterval(interval.getFirstDate(), interval.getSecondDate()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error");
        }
    }

    @PutMapping("/finish-create")
    public ResponseEntity<?> finishCreate(@RequestParam Integer id){
        try {
            return ResponseEntity.ok(helicopterService.finishCreate(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error");
        }
    }
    @PutMapping("/finish-test")
    public ResponseEntity<?> finishTest(@RequestParam Integer id){
        try {
            return ResponseEntity.ok(helicopterService.finishTest(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error");
        }
    }
    @DeleteMapping
    public ResponseEntity<?> delete(@RequestParam Integer id){
        try {
            helicopterService.delete(id);
            return ResponseEntity.ok("delete");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error");
        }
    }
}

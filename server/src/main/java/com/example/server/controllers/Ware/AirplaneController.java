package com.example.server.controllers.Ware;

import com.example.server.dto.AirplaneDto;
import com.example.server.dto.Interval;
import com.example.server.service.Ware.AirplaneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/airplane")
public class AirplaneController {
    @Autowired
    private AirplaneService airplaneService;

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<?> create(@RequestBody AirplaneDto entity){
        try {
            airplaneService.create(entity);
            return ResponseEntity.ok("Airplane created");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error");
        }
    }

    @GetMapping
    public ResponseEntity<?> getById(@RequestParam Integer id){
        try {
            return ResponseEntity.ok(airplaneService.getById(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error");
        }
    }
    @GetMapping("/all")
    public ResponseEntity<?> getAll(){
        try {
            return ResponseEntity.ok(airplaneService.getAll());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error");
        }
    }

    @PostMapping("/interval")
    public ResponseEntity<?> getByInterval(@RequestBody Interval interval){
        try {
            return ResponseEntity.ok(airplaneService.getByInterval(interval.getFirstDate(), interval.getSecondDate()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error");
        }
    }

    @PutMapping("/finish-create")
    public ResponseEntity<?> finishCreate(@RequestParam Integer id){
        try {
            return ResponseEntity.ok(airplaneService.finishCreate(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error");
        }
    }
    @PutMapping("/finish-test")
    public ResponseEntity<?> finishTest(@RequestParam Integer id){
        try {
            return ResponseEntity.ok(airplaneService.finishTest(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error");
        }
    }
    @DeleteMapping
    public ResponseEntity<?> delete(@RequestParam Integer id){
        try {
            airplaneService.delete(id);
            return ResponseEntity.ok("delete");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error");
        }
    }
}

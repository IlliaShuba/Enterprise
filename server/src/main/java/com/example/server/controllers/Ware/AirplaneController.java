package com.example.server.controllers.Ware;

import com.example.server.dto.AirplaneDto;
import com.example.server.service.Ware.AirplaneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/airplane")
public class AirplaneController {
    @Autowired
    private AirplaneService airplaneService;

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
}

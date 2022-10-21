package com.example.server.controllers.Ware;

import com.example.server.dto.Interval;
import com.example.server.dto.MissileDto;
import com.example.server.service.Ware.MissileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/missile")
public class MissileController {
    @Autowired
    private MissileService missileService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody MissileDto entity){
        try {
            missileService.create(entity);
            return ResponseEntity.ok("Missile created");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error");
        }
    }

    @GetMapping
    public ResponseEntity getById(@RequestParam Integer id){
        try {
            return ResponseEntity.ok(missileService.getById(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error");
        }
    }
    @GetMapping("/all")
    public ResponseEntity<?> getAll(){
        try {
            return ResponseEntity.ok(missileService.getAll());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error");
        }
    }

    @PostMapping("/interval")
    public ResponseEntity<?> getByInterval(@RequestBody Interval interval){
        try {
            return ResponseEntity.ok(missileService.getByInterval(interval.getFirstDate(), interval.getSecondDate()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error");
        }
    }

    @PutMapping("/finish-create")
    public ResponseEntity<?> finishCreate(@RequestParam Integer id){
        try {
            return ResponseEntity.ok(missileService.finishCreate(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error");
        }
    }
    @PutMapping("/finish-test")
    public ResponseEntity<?> finishTest(@RequestParam Integer id){
        try {
            return ResponseEntity.ok(missileService.finishTest(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error");
        }
    }
    @DeleteMapping
    public ResponseEntity<?> delete(@RequestParam Integer id){
        try {
            missileService.delete(id);
            return ResponseEntity.ok("delete");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error");
        }
    }
}

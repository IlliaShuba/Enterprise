package com.example.server.controllers.Ware;

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
}

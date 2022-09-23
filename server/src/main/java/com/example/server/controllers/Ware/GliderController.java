package com.example.server.controllers.Ware;

import com.example.server.dto.GliderDto;
import com.example.server.service.Ware.GliderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/glider")
public class GliderController {
    @Autowired
    private GliderService gliderService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody GliderDto entity){
        try {
            gliderService.create(entity);
            return ResponseEntity.ok("Glider created");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error");
        }
    }

    @GetMapping
    public ResponseEntity getById(@RequestParam Integer id){
        try {
            return ResponseEntity.ok(gliderService.getById(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error");
        }
    }
}

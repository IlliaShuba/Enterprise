package com.example.server.controllers.Ware;

import com.example.server.dto.HangGliderDto;
import com.example.server.service.Ware.HangGliderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/hang-glider")
public class HangGliderController {
    @Autowired
    private HangGliderService hangGliderService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody HangGliderDto entity){
        try {
            hangGliderService.create(entity);
            return ResponseEntity.ok("Hang glider created");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error");
        }
    }

    @GetMapping
    public ResponseEntity getById(@RequestParam Integer id){
        try {
            return ResponseEntity.ok(hangGliderService.getById(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error");
        }
    }
}

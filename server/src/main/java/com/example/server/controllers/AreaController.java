package com.example.server.controllers;

import com.example.server.dto.AreaDto;
import com.example.server.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/area")
public class AreaController {
    @Autowired
    private AreaService areaService;

    @PostMapping
    public ResponseEntity<?> createArea(@RequestBody AreaDto entity, @RequestParam Integer shopId, @RequestParam Integer headId){
        try {
            areaService.createArea(entity, shopId, headId);
            return ResponseEntity.ok("Area created");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error");
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllAreaInShop(@RequestParam Integer id){
        try {
            return ResponseEntity.ok(areaService.getByShopId(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error");
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAll(){
        try {
            return ResponseEntity.ok(areaService.getAll());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error");
        }
    }

    @DeleteMapping
    public ResponseEntity deleteArea(@RequestParam Integer id){
        try {
            return ResponseEntity.ok(areaService.deleteArea(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error");
        }
    }
}

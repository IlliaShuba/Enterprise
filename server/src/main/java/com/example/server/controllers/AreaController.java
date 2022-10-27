package com.example.server.controllers;

import com.example.server.dto.AreaDto;
import com.example.server.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

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
    public ResponseEntity<?> getById(@RequestParam Integer id){
        try {
            return ResponseEntity.ok(areaService.getById(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error");
        }
    }

    @GetMapping("/byShop")
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

    @PutMapping("/head")
    public ResponseEntity<?> setHead(@RequestParam Integer areaId, @RequestParam Integer headId){
        try {
            return ResponseEntity.ok(areaService.setHead(areaId,headId));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error");
        }
    }

    @PutMapping("/master")
    public ResponseEntity<?> setMasters(@RequestParam Integer areaId, @RequestParam Integer masterId){
        try {
            return ResponseEntity.ok(areaService.setMasters(areaId, masterId));
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

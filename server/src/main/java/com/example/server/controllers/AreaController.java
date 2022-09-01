package com.example.server.controllers;

import com.example.server.entity.Area;
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
    public ResponseEntity createArea(@RequestBody Area entity, @RequestParam Integer shopId){
        try {
            areaService.createArea(entity, shopId);
            return ResponseEntity.ok("Area created");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @GetMapping
    public ResponseEntity showAllArea(){
        try {
            return ResponseEntity.ok(areaService.getAll());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @DeleteMapping
    public ResponseEntity deleteArea(@RequestParam Integer id){
        try {
            return ResponseEntity.ok(areaService.deleteArea(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }
}

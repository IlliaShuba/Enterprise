package com.example.server.controllers;

import com.example.server.service.BrigadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/brigade")
public class BrigadeController {

    @Autowired
    private BrigadeService brigadeService;

    @PostMapping
    public ResponseEntity<?> createBrigade(@RequestParam Integer areaId){
        try {
            brigadeService.createBrigade(areaId);
            return ResponseEntity.ok("Brigade created");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error");
        }
    }

    @GetMapping
    public  ResponseEntity<?> getById(@RequestParam Integer id){
        try {
            return ResponseEntity.ok(brigadeService.getById(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error");
        }
    }

    @GetMapping("/area")
    public  ResponseEntity<?> getByAreaId(@RequestParam Integer id){
        try {
            return ResponseEntity.ok(brigadeService.getByAreaId(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error");
        }
    }
    @GetMapping("/all")
    public  ResponseEntity<?> showAll(){
        try {
            return ResponseEntity.ok(brigadeService.getAll());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error");
        }
    }

    @PutMapping
    public ResponseEntity<?> setHead(@RequestParam Integer id, @RequestParam Integer headId){
        try {
            return ResponseEntity.ok(brigadeService.setHead(id, headId));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error");
        }
    }


}

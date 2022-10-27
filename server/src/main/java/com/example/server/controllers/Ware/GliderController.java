package com.example.server.controllers.Ware;

import com.example.server.dto.GliderDto;
import com.example.server.dto.Interval;
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
    @GetMapping("/all")
    public ResponseEntity<?> getAll(){
        try {
            return ResponseEntity.ok(gliderService.getAll());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error");
        }
    }

    @GetMapping("/shop")
    public ResponseEntity<?> getByShop(@RequestParam Integer id){
        try {
            return ResponseEntity.ok(gliderService.getByShop(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error");
        }
    }
    @GetMapping("/laboratory")
    public ResponseEntity<?> getByLaboratory(@RequestParam Integer id){
        try {
            return ResponseEntity.ok(gliderService.getByLaboratory(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error");
        }
    }

    @PostMapping("/interval")
    public ResponseEntity<?> getByInterval(@RequestBody Interval interval){
        try {
            return ResponseEntity.ok(gliderService.getByInterval(interval.getFirstDate(), interval.getSecondDate()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error");
        }
    }

    @PutMapping("/finish-create")
    public ResponseEntity<?> finishCreate(@RequestParam Integer id){
        try {
            return ResponseEntity.ok(gliderService.finishCreate(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error");
        }
    }
    @PutMapping("/finish-test")
    public ResponseEntity<?> finishTest(@RequestParam Integer id){
        try {
            return ResponseEntity.ok(gliderService.finishTest(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error");
        }
    }
    @DeleteMapping
    public ResponseEntity<?> delete(@RequestParam Integer id){
        try {
            gliderService.delete(id);
            return ResponseEntity.ok("delete");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error");
        }
    }

}

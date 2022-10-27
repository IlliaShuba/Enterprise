package com.example.server.controllers.Ware;

import com.example.server.dto.HangGliderDto;
import com.example.server.dto.Interval;
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
    @GetMapping("/all")
    public ResponseEntity<?> getAll(){
        try {
            return ResponseEntity.ok(hangGliderService.getAll());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error");
        }
    }

    @GetMapping("/shop")
    public ResponseEntity<?> getByShop(@RequestParam Integer id){
        try {
            return ResponseEntity.ok(hangGliderService.getByShop(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error");
        }
    }
    @GetMapping("/laboratory")
    public ResponseEntity<?> getByLaboratory(@RequestParam Integer id){
        try {
            return ResponseEntity.ok(hangGliderService.getByLaboratory(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error");
        }
    }

    @PostMapping("/interval")
    public ResponseEntity<?> getByInterval(@RequestBody Interval interval){
        try {
            return ResponseEntity.ok(hangGliderService.getByInterval(interval.getFirstDate(), interval.getSecondDate()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error");
        }
    }

    @PutMapping("/finish-create")
    public ResponseEntity<?> finishCreate(@RequestParam Integer id){
        try {
            return ResponseEntity.ok(hangGliderService.finishCreate(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error");
        }
    }
    @PutMapping("/finish-test")
    public ResponseEntity<?> finishTest(@RequestParam Integer id){
        try {
            return ResponseEntity.ok(hangGliderService.finishTest(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error");
        }
    }
    @DeleteMapping
    public ResponseEntity<?> delete(@RequestParam Integer id){
        try {
            hangGliderService.delete(id);
            return ResponseEntity.ok("delete");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error");
        }
    }
}

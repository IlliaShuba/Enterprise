package com.example.server.controllers.Ware;

import com.example.server.dto.WorkDto;
import com.example.server.service.Ware.WorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/work")
public class WorkController {
    @Autowired
    private WorkService workService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody WorkDto entity, @RequestParam Integer areaId, @RequestParam Integer brigadeId){
        try {
            workService.create(entity, areaId, brigadeId);
            return ResponseEntity.ok("Work created");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error");
        }
    }

    @GetMapping("{ware}")
    public ResponseEntity<?> getByType(@PathVariable("ware") String ware){
        try {
            return ResponseEntity.ok(workService.getByType(ware));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error");
        }
    }

    @GetMapping("{ware}/{id}")
    public ResponseEntity<?> getByWareId(@PathVariable("ware") String ware, @PathVariable("id") Integer id){
        try {
            return ResponseEntity.ok(workService.getByWareId(ware, id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error");
        }
    }

    @PutMapping("/finish")
    public ResponseEntity<?> finish(@RequestParam Integer id){
        try {
            workService.finish(id);
            return ResponseEntity.ok("Work finished");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error");
        }
    }
}

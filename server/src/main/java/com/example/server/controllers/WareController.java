package com.example.server.controllers;

import com.example.server.entity.Ware;
import com.example.server.service.WareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/ware")
public class WareController {

    @Autowired
    private WareService wareService;

    @PostMapping
    public ResponseEntity<?> createWare(@RequestBody Ware entity){
        try {
            wareService.createWare(entity);
            return ResponseEntity.ok("Ware created");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @GetMapping
    public void test(){
        System.out.println("123");
    }


}

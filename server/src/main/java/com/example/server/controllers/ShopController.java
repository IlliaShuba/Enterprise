package com.example.server.controllers;

import com.example.server.entity.Area;
import com.example.server.entity.Shop;
import com.example.server.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;


@RestController
@RequestMapping("/shop")
public class ShopController {

    @Autowired
    private ShopService shopService;

    @PostMapping
    public ResponseEntity createShop(@RequestBody Shop shop){
        try {
            shopService.createShop(shop);
            return ResponseEntity.ok("Shop created");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @GetMapping
    public ResponseEntity showAllShop(){
        try {
            return ResponseEntity.ok(shopService.getAll());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @DeleteMapping
    public ResponseEntity deleteShop(@RequestParam Integer id){
        try {
            return ResponseEntity.ok(shopService.deleteShop(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }
}

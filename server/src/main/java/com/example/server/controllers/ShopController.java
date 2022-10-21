package com.example.server.controllers;

import com.example.server.entity.Shop;
import com.example.server.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/shop")
public class ShopController {

    @Autowired
    private ShopService shopService;

    @PostMapping
    public ResponseEntity<?> createShop(@RequestParam Integer headId){
        try {
            Shop shop = new Shop();
            shopService.createShop(shop, headId);
            return ResponseEntity.ok("Shop created");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error");
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> showAllShop(){
        try {
            return ResponseEntity.ok(shopService.getAll());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error");
        }
    }

    @PutMapping("/head")
    public ResponseEntity<?> setHead(@RequestParam Integer shopId, @RequestParam Integer headId){
        try {
            return ResponseEntity.ok(shopService.setHead(shopId,headId));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error");
        }
    }
    @PutMapping("/laboratory")
    public ResponseEntity<?> setLaboratory(@RequestParam Integer shopId, @RequestParam Integer labId){
        try {
            return ResponseEntity.ok(shopService.setLab(shopId,labId));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error");
        }
    }

    @DeleteMapping
    public ResponseEntity deleteShop(@RequestParam Integer id){
        try {
            return ResponseEntity.ok(shopService.deleteShop(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error");
        }
    }
}

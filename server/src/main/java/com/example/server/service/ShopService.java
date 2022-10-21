package com.example.server.service;

import com.example.server.dto.ShopDto;
import com.example.server.entity.EngineeringStaff;
import com.example.server.entity.Laboratory;
import com.example.server.entity.Shop;
import com.example.server.repository.AreaRepository;
import com.example.server.repository.EngineeringStaffRepository;
import com.example.server.repository.LaboratoryRepository;
import com.example.server.repository.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShopService {
    @Autowired
    ShopRepository shopRepository;
    @Autowired
    AreaRepository areaRepository;
    @Autowired
    EngineeringStaffRepository engineeringStaffRepository;
    @Autowired
    LaboratoryRepository laboratoryRepository;


    public List<ShopDto> getAll(){
        List<Shop> shops = shopRepository.findAll();
        return shops.stream().map(this::toDto).collect(Collectors.toList());
    }

    public Shop createShop(Shop shop, Integer headId){
        EngineeringStaff head = engineeringStaffRepository.findById(headId).orElseThrow();
        shop.setHead(head);
        return shopRepository.save(shop);
    }

    public Shop setHead(Integer shopId,Integer headId){
        Shop shop = shopRepository.findById(shopId).orElseThrow();
        EngineeringStaff head = engineeringStaffRepository.findById(headId).orElseThrow();
        shop.setHead(head);
        return  shopRepository.save(shop);
    }
    public Shop setLab(Integer shopId,Integer labId){
        Shop shop = shopRepository.findById(shopId).orElseThrow();
        Laboratory lab = laboratoryRepository.findById(labId).orElseThrow();
        List<Laboratory> laboratories = shop.getLaboratories();
        laboratories.add(lab);
        shop.setLaboratories(laboratories);
        return  shopRepository.save(shop);
    }

    public Integer deleteShop(Integer id){
        shopRepository.deleteById(id);
        return id;
    }


    public ShopDto toDto(Shop entity){
        ShopDto dto = new ShopDto();
        dto.setId(entity.getId());
        dto.setArea(entity.getArea());
        dto.setHead(entity.getHead());
        dto.setLaboratories(entity.getLaboratories());
        return dto;
    }

}

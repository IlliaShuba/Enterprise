package com.example.server.service;

import com.example.server.dto.ShopDto;
import com.example.server.entity.Area;
import com.example.server.entity.Shop;
import com.example.server.repository.AreaRepository;
import com.example.server.repository.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ShopService {

    @Autowired
    ShopRepository shopRepository;

    @Autowired
    AreaRepository areaRepository;

    public Iterable<Shop>  getAll(){
        Iterable<Shop> set = shopRepository.findAll();
        return set;
    }

    public Shop createShop(Shop shop){
        return shopRepository.save(shop);
    }

    public Integer deleteShop(Integer id){
        shopRepository.deleteById(id);
        return id;
    }


    public ShopDto toDto(Shop entity){
        ShopDto dto = new ShopDto();
        dto.setId(entity.getId());
        //dto.setArea(entity.getArea().stream().map(Area::toAreaDto).collect(Collectors.toList()));
        return dto;
    }

}

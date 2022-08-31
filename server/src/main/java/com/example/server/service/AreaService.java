package com.example.server.service;

import com.example.server.dto.AreaDto;
import com.example.server.entity.Area;
import com.example.server.entity.Shop;
import com.example.server.repository.AreaRepository;
import com.example.server.repository.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AreaService {

    @Autowired
    private ShopRepository shopRepository;
    @Autowired
    private AreaRepository areaRepository;

    public Area createArea(Area area, Integer shopId){
        Shop shop = shopRepository.findById(shopId).get();
        area.setShop(shop);
        return areaRepository.save(area);
    }


    public AreaDto toShopDto(Area entity){
        AreaDto dto = new AreaDto();
        dto.setId(entity.getId());
        dto.setType(entity.getType());
        return dto;
    }

}
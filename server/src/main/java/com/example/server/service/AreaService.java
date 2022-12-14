package com.example.server.service;

import com.example.server.dto.AreaDto;
import com.example.server.entity.Area;
import com.example.server.entity.EngineeringStaff;
import com.example.server.entity.Laboratory;
import com.example.server.entity.Shop;
import com.example.server.repository.AreaRepository;
import com.example.server.repository.EngineeringStaffRepository;
import com.example.server.repository.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AreaService {

    @Autowired
    private ShopRepository shopRepository;
    @Autowired
    private AreaRepository areaRepository;
    @Autowired
    EngineeringStaffRepository engineeringStaffRepository;

    public Area createArea(AreaDto dto, Integer shopId, Integer headId){
        Area area = new Area();
        Shop shop = shopRepository.findById(shopId).get();
        EngineeringStaff head = engineeringStaffRepository.findById(headId).get();

        area.setType(dto.getType());
        area.setShop(shop);
        area.setHead(head);
        return areaRepository.save(area);
    }
    public Area setMasters(Integer areaId, Integer masterId){
        Area area = areaRepository.findById(areaId).orElseThrow();
        EngineeringStaff master = engineeringStaffRepository.findById(masterId).orElseThrow();
        List<EngineeringStaff> masters = area.getMasters();
        masters.add(master);
        area.setMasters(masters);
        return  areaRepository.save(area);
    }

    public Area setHead(Integer areaId,Integer headId){
        Area area = areaRepository.findById(areaId).orElseThrow();
        EngineeringStaff head = engineeringStaffRepository.findById(headId).orElseThrow();
        area.setHead(head);
        return  areaRepository.save(area);
    }

    public Area getById(Integer id) {return areaRepository.findById(id).get();}
    public List<Area> getByShopId(Integer id){return areaRepository.queryFindAllByShopId(id);}
    public List<Area> getAll() {return areaRepository.findAll();}

    public Integer deleteArea(Integer id){
        areaRepository.deleteById(id);
        return id;
    }

    public AreaDto toDto(Area entity){
        AreaDto dto = new AreaDto();
        dto.setId(entity.getId());
        dto.setType(entity.getType());
        return dto;
    }
}
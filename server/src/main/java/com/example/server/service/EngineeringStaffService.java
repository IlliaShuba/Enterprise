package com.example.server.service;

import com.example.server.dto.EngineerDto;
import com.example.server.dto.WorkerDto;
import com.example.server.entity.Area;
import com.example.server.entity.EngineeringStaff;
import com.example.server.entity.Shop;
import com.example.server.entity.Worker;
import com.example.server.repository.AreaRepository;
import com.example.server.repository.EngineeringStaffRepository;
import com.example.server.repository.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class EngineeringStaffService {

    @Autowired
    EngineeringStaffRepository engineeringStaffRepository;
    @Autowired
    ShopRepository shopRepository;
    @Autowired
    AreaRepository areaRepository;

    public EngineeringStaff create(EngineerDto dto){
        EngineeringStaff entity = new EngineeringStaff();
        entity.setName(dto.getName());
        entity.setLastname(dto.getLastname());
        entity.setSpeciality(dto.getSpeciality());

        return engineeringStaffRepository.save(entity);
    }

    public List<EngineeringStaff> getAll(){return engineeringStaffRepository.findAll();}
    public EngineerDto getById(Integer id){return toDto(engineeringStaffRepository.findById(id).get());}
    public List<EngineeringStaff> getByShop(Integer id){
        Shop shop = shopRepository.findById(id).get();
        List<EngineeringStaff> response = new ArrayList<>();

        for(Area area : shop.getArea()){
            response.addAll(area.getMasters());
        }

        return response;
    }
    public List<EngineeringStaff> getByArea(Integer id){return areaRepository.findById(id).get().getMasters();}

    public void delete(Integer id){engineeringStaffRepository.deleteById(id);}

    public EngineerDto toDto(EngineeringStaff entity){
        EngineerDto dto = new EngineerDto();
        dto.setId(entity.getId());
        dto.setSpeciality(entity.getSpeciality());
        dto.setName(entity.getName());
        dto.setLastname(entity.getLastname());
        if(entity.getArea() != null){
            dto.setArea(entity.getArea().getId());
        }
        return dto;
    }
}

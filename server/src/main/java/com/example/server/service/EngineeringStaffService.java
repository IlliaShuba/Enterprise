package com.example.server.service;

import com.example.server.entity.EngineeringStaff;
import com.example.server.repository.AreaRepository;
import com.example.server.repository.EngineeringStaffRepository;
import com.example.server.repository.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EngineeringStaffService {

    @Autowired
    EngineeringStaffRepository engineeringStaffRepository;
    @Autowired
    ShopRepository shopRepository;
    @Autowired
    AreaRepository areaRepository;

    public EngineeringStaff create(EngineeringStaff entity){
        return engineeringStaffRepository.save(entity);
    }

    public Iterable<EngineeringStaff> getAll(){return engineeringStaffRepository.findAll();}
    public EngineeringStaff getById(Integer id){return engineeringStaffRepository.findById(id).get();}
    public void delete(Integer id){engineeringStaffRepository.deleteById(id);}
}

package com.example.server.service;

import com.example.server.entity.Area;
import com.example.server.entity.Brigade;
import com.example.server.repository.AreaRepository;
import com.example.server.repository.BrigadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BrigadeService {

    @Autowired
    private BrigadeRepository brigadeRepository;

    @Autowired
    private AreaRepository areaRepository;

    public Brigade createBrigade(Brigade entity, Integer areaId){
        Area area = areaRepository.findById(areaId).get();
        entity.setArea(area);
        return brigadeRepository.save(entity);
    }
}

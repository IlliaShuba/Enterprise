package com.example.server.service;


import com.example.server.entity.Ware;
import com.example.server.repository.WareRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WareService {
    @Autowired
    private WareRepository wareRepository;

    public Ware createWare(Ware entity){
        return wareRepository.save(entity);
    }

}

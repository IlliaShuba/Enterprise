package com.example.server.service;


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

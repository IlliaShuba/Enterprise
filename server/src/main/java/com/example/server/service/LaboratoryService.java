package com.example.server.service;

import com.example.server.entity.Laboratory;
import com.example.server.repository.LaboratoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LaboratoryService {
    @Autowired
    private LaboratoryRepository laboratoryRepository;

    public Laboratory create(){
        Laboratory laboratory = new Laboratory();

        return laboratoryRepository.save(laboratory);
    }

}

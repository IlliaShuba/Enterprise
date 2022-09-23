package com.example.server.service;

import com.example.server.entity.Laboratory;
import com.example.server.entity.Tester;
import com.example.server.repository.LaboratoryRepository;
import com.example.server.repository.TesterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TesterService {
    @Autowired
    private TesterRepository testerRepository;
    @Autowired
    private LaboratoryRepository laboratoryRepository;

    public Tester create(Tester entity, Integer labId){
        Laboratory laboratory = laboratoryRepository.findById(labId).get();
        entity.setLaboratory(laboratory);
        return testerRepository.save(entity);
    }
}

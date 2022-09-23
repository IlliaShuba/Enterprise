package com.example.server.service;

import com.example.server.entity.Brigade;
import com.example.server.entity.Worker;
import com.example.server.repository.BrigadeRepository;
import com.example.server.repository.WorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WorkerService {
    @Autowired
    private WorkerRepository workerRepository;
    @Autowired
    private BrigadeRepository brigadeRepository;

    public Worker create(Worker entity, Integer brigadeId){
        Brigade brigade = brigadeRepository.findById(brigadeId).get();
        entity.setBrigade(brigade);
        return workerRepository.save(entity);
    }
}

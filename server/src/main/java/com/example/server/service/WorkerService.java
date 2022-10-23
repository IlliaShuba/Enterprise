package com.example.server.service;

import com.example.server.dto.WorkerDto;
import com.example.server.entity.Brigade;
import com.example.server.entity.EngineeringStaff;
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

    public Worker create(WorkerDto dto, Integer brigadeId){
        Worker entity = new Worker();
        Brigade brigade = brigadeRepository.findById(brigadeId).get();

        entity.setName(dto.getName());
        entity.setBrigade(brigade);
        return workerRepository.save(entity);
    }

    public Worker getById(Integer id){return workerRepository.findById(id).get();}
    public Iterable<Worker> getAll(){return workerRepository.findAll();}
    public void delete(Integer id){workerRepository.deleteById(id);}

}

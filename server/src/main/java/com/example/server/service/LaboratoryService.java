package com.example.server.service;

import com.example.server.entity.Brigade;
import com.example.server.entity.Laboratory;
import com.example.server.entity.Worker;
import com.example.server.repository.LaboratoryRepository;
import com.example.server.repository.WorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LaboratoryService {
    @Autowired
    private LaboratoryRepository laboratoryRepository;
    @Autowired
    private WorkerRepository workerRepository;

    public Laboratory create(){
        Laboratory laboratory = new Laboratory();
        return laboratoryRepository.save(laboratory);
    }

    public Laboratory getById(Integer id){return laboratoryRepository.findById(id).get();}
    public List<Laboratory> getAll(){return laboratoryRepository.findAll();}

    public Laboratory setWorker(Integer labId, Integer workerId){
        Laboratory laboratory = laboratoryRepository.findById(labId).orElseThrow();
        Worker worker = workerRepository.findById(workerId).orElseThrow();
        List<Worker> workers = laboratory.getWorkers();
        workers.add(worker);
        laboratory.setWorkers(workers);
        return  laboratoryRepository.save(laboratory);
    }
    public void delete(Integer id){laboratoryRepository.deleteById(id);}
}

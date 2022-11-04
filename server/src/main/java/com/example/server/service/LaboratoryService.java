package com.example.server.service;

import com.example.server.dto.EquipmentDto;
import com.example.server.dto.GliderDto;
import com.example.server.dto.HangGliderDto;
import com.example.server.dto.LaboratoryDto;
import com.example.server.entity.Brigade;
import com.example.server.entity.Laboratory;
import com.example.server.entity.Ware.Glider;
import com.example.server.entity.Ware.HangGlider;
import com.example.server.entity.Worker;
import com.example.server.repository.LaboratoryRepository;
import com.example.server.repository.WorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public List<LaboratoryDto> getAll(){
        List<LaboratoryDto> response = new ArrayList<>();

        for (Laboratory item : laboratoryRepository.findAll()) {
            response.add(toDto(item));
        }
        return response;
    }

    public Laboratory setWorker(Integer labId, Integer workerId){
        Laboratory laboratory = laboratoryRepository.findById(labId).orElseThrow();
        Worker worker = workerRepository.findById(workerId).orElseThrow();
        List<Worker> workers = laboratory.getWorkers();
        workers.add(worker);
        laboratory.setWorkers(workers);
        return  laboratoryRepository.save(laboratory);
    }
    public void delete(Integer id){laboratoryRepository.deleteById(id);}

    public LaboratoryDto toDto(Laboratory entity){
        LaboratoryDto dto = new LaboratoryDto();
        dto.setId(entity.getId());
        dto.setEquipment(entity.getEquipment());
        dto.setShops(entity.getShops());
        dto.setWorkers(entity.getWorkers());
        return dto;
    }
}

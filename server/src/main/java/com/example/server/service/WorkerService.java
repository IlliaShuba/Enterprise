package com.example.server.service;

import com.example.server.dto.AreaDto;
import com.example.server.dto.WorkerDto;
import com.example.server.entity.*;
import com.example.server.repository.BrigadeRepository;
import com.example.server.repository.LaboratoryRepository;
import com.example.server.repository.WorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class WorkerService {
    @Autowired
    private WorkerRepository workerRepository;
    @Autowired
    private BrigadeRepository brigadeRepository;
    @Autowired
    private LaboratoryRepository laboratoryRepository;

    public Worker create(WorkerDto dto){
        Worker entity = new Worker();
        entity.setName(dto.getName());
        entity.setLastname(dto.getLastname());
        entity.setCategory(dto.getCategory());

        return workerRepository.save(entity);
    }

    public WorkerDto getById(Integer id){return toDto(workerRepository.findById(id).get());}
    public List<Worker> getAll(){return workerRepository.findAll();}
    public void delete(Integer id){workerRepository.deleteById(id);}

    public WorkerDto toDto(Worker entity){
        WorkerDto dto = new WorkerDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setLastname(entity.getLastname());
        dto.setCategory(entity.getCategory());
        if(entity.getBrigade() != null){
            dto.setNumber_of_space(entity.getBrigade().getId());
            dto.setType("brigade");
        }
        else if(entity.getLaboratory() != null){
            dto.setNumber_of_space(entity.getLaboratory().getId());
            dto.setType("laboratory");
        }

        return dto;
    }
}

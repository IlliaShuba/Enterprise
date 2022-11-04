package com.example.server.service;

import com.example.server.dto.AreaDto;
import com.example.server.dto.WorkerDto;
import com.example.server.entity.*;
import com.example.server.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class WorkerService {
    @Autowired
    private WorkerRepository workerRepository;
    @Autowired
    private ShopRepository shopRepository;
    @Autowired
    private AreaRepository areaRepository;
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
    public List<Worker> getByShop(Integer id){
        Shop shop = shopRepository.findById(id).get();
        List<Worker> response = new ArrayList<>();

        for(Area area : shop.getArea()){
            for(Brigade brigade : area.getBrigades()){
                response.addAll(brigade.getWorkers());
            }
        }

        return response;
    }
    public List<Worker> getByArea(Integer id){
        Area area = areaRepository.findById(id).get();
        List<Worker> response = new ArrayList<>();

        for(Brigade brigade : area.getBrigades()){
            response.addAll(brigade.getWorkers());
        }

        return response;
    }
    public List<Worker> getByLaboratory(Integer id){return laboratoryRepository.findById(id).get().getWorkers();}

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

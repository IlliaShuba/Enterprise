package com.example.server.service;

import com.example.server.dto.AreaDto;
import com.example.server.dto.BrigadeDto;
import com.example.server.entity.Area;
import com.example.server.entity.Brigade;
import com.example.server.entity.EngineeringStaff;
import com.example.server.entity.Worker;
import com.example.server.repository.AreaRepository;
import com.example.server.repository.BrigadeRepository;
import com.example.server.repository.WorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BrigadeService {

    @Autowired
    private BrigadeRepository brigadeRepository;
    @Autowired
    private AreaRepository areaRepository;
    @Autowired
    private WorkerRepository workerRepository;

    public Brigade createBrigade(Integer areaId, Integer headId){
        Brigade entity = new Brigade();
        Area area = areaRepository.findById(areaId).get();
        entity.setArea(area);
        Worker brigadier = workerRepository.findById(headId).get();
        entity.setBrigadier(brigadier);
        return brigadeRepository.save(entity);
    }

    public BrigadeDto getById(Integer id){return toDto(brigadeRepository.findById(id).get());}
    public List<BrigadeDto> getByAreaId(Integer id){return  brigadeRepository.queryFindAllByAreaId(id).stream().map(this::toDto).collect(Collectors.toList());}
    public List<BrigadeDto> getAll(){return brigadeRepository.findAll().stream().map(this::toDto).collect(Collectors.toList());}

    public Brigade setWorker(Integer brigadeId, Integer workerId){
        Brigade brigade = brigadeRepository.findById(brigadeId).orElseThrow();
        Worker worker = workerRepository.findById(workerId).orElseThrow();
        List<Worker> workers = brigade.getWorkers();
        workers.add(worker);
        brigade.setWorkers(workers);
        return  brigadeRepository.save(brigade);
    }

    public Brigade setHead(Integer id, Integer headId){
        Brigade brigade = brigadeRepository.findById(id).get();
        Worker head = workerRepository.findById(headId).get();
        brigade.setBrigadier(head);
        return brigadeRepository.save(brigade);
    }

    public void delete(Integer id){brigadeRepository.deleteById(id);}

    public BrigadeDto toDto(Brigade entity){
        BrigadeDto dto = new BrigadeDto();
        dto.setId(entity.getId());
        dto.setAreaId(entity.getArea().getId());
        dto.setBrigadier(entity.getBrigadier());
        dto.setWorkers(entity.getWorkers());
        return dto;
    }
}

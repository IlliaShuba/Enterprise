package com.example.server.service;

import com.example.server.dto.WorkerDto;
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
    @Autowired
    private AreaService areaService;

    public Worker create(WorkerDto dto, Integer brigadeId){
        Worker entity = new Worker();
        Brigade brigade = brigadeRepository.findById(brigadeId).get();

        entity.setName(dto.getName());
        entity.setBrigade(brigade);
        return workerRepository.save(entity);
    }

    public Iterable<Worker> getAll(){return workerRepository.findAll();}

   /* public Set<Brigade> getByShopId(Integer id){
        List<Area> areas = areaService.getByShopId(id);
        Set<Brigade> brigades = new HashSet<>();
        //Set<Worker> workers = new HashSet<>();
        for (Area area : areas) {
            brigades.addAll(brigadeRepository.queryFindAllByAreaId(area.getId()));
        }
        *//*for (Brigade brigade: brigades){
           workers.addAll(workerRepository.queryFindAllByBrigade_Id(brigade.getId()));
        }*//*
        return brigades;
    }*/
}

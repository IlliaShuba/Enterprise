package com.example.server.service;

import com.example.server.entity.Area;
import com.example.server.entity.Brigade;
import com.example.server.entity.Worker;
import com.example.server.repository.AreaRepository;
import com.example.server.repository.BrigadeRepository;
import com.example.server.repository.WorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrigadeService {

    @Autowired
    private BrigadeRepository brigadeRepository;
    @Autowired
    private AreaRepository areaRepository;
    @Autowired
    private WorkerRepository workerRepository;

    public Brigade createBrigade(Integer areaId){
        Brigade entity = new Brigade();
        Area area = areaRepository.findById(areaId).get();
        entity.setArea(area);
        entity.setBrigadier(null);
        return brigadeRepository.save(entity);
    }

    public Brigade getById(Integer id){return brigadeRepository.findById(id).get();}
    public List<Brigade> getByAreaId(Integer id){return  brigadeRepository.queryFindAllByAreaId(id);}
    public List<Brigade> getAll(){return brigadeRepository.findAll();}

    public Brigade setHead(Integer id, Integer headId){
        Brigade brigade = brigadeRepository.findById(id).get();
        Worker head = workerRepository.findById(headId).get();
        brigade.setBrigadier(head);
        return brigadeRepository.save(brigade);
    }
}

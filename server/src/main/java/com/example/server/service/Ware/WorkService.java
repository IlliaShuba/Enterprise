package com.example.server.service.Ware;

import com.example.server.dto.WorkDto;
import com.example.server.entity.Area;
import com.example.server.entity.Brigade;
import com.example.server.entity.Ware.Airplane;
import com.example.server.entity.Ware.Work;
import com.example.server.repository.AreaRepository;
import com.example.server.repository.BrigadeRepository;
import com.example.server.repository.WareRepo.AirplaneRepository;
import com.example.server.repository.WorkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class WorkService {
    @Autowired
    private WorkRepository workRepository;
    @Autowired
    private AreaRepository areaRepository;
    @Autowired
    private BrigadeRepository brigadeRepository;
    @Autowired
    private AirplaneRepository airplaneRepository;

    public Work create(WorkDto dto, Integer areaId, Integer brigadeId){
        Work work = new Work();
        Area area = areaRepository.findById(areaId).get();
        Brigade brigade = brigadeRepository.findById(brigadeId).get();

        work.setTypeOfWork(dto.getTypeOfWork());
        work.setStartWork(LocalDateTime.now());
        work.setFinishWork(null);
        work.setArea(area);
        work.setBrigade(brigade);

        switch (dto.getWare()){
            case ("airplane"):
                Airplane airplane = airplaneRepository.findById(dto.getWareId()).get();
                work.setAirplane(airplane);
                break;
            case ("glider"):
                Airplane glider = airplaneRepository.findById(dto.getWareId()).get();
                work.setAirplane(glider);
                break;
        }
        return workRepository.save(work);
    }

}

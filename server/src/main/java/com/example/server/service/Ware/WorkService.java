package com.example.server.service.Ware;

import com.example.server.dto.WorkDto;
import com.example.server.entity.Area;
import com.example.server.entity.Brigade;
import com.example.server.entity.Ware.*;
import com.example.server.repository.AreaRepository;
import com.example.server.repository.BrigadeRepository;
import com.example.server.repository.WareRepo.*;
import com.example.server.repository.WorkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

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
    @Autowired
    private GliderRepository gliderRepository;
    @Autowired
    private HangGliderRepository hangGliderRepository;
    @Autowired
    private HelicopterRepository helicopterRepository;
    @Autowired
    private MissileRepository missileRepository;

    public Work create(WorkDto dto, Integer areaId, Integer brigadeId){
        Work work = new Work();
        Area area = areaRepository.findById(areaId).get();
        Brigade brigade = brigadeRepository.findById(brigadeId).get();

        work.setTypeOfWork(dto.getTypeOfWork());
        work.setStartWork(LocalDate.now());
        work.setFinishWork(null);
        work.setArea(area);
        work.setBrigade(brigade);

        switch (dto.getWare()) {
            case ("airplane") -> {
                Airplane airplane = airplaneRepository.findById(dto.getWareId()).get();
                work.setAirplane(airplane);
            }
            case ("glider") -> {
                Glider glider = gliderRepository.findById(dto.getWareId()).get();
                work.setGlider(glider);
            }
            case ("hang_glider") -> {
                HangGlider hangGlider = hangGliderRepository.findById(dto.getWareId()).get();
                work.setHangGlider(hangGlider);
            }
            case ("helicopter") -> {
                Helicopter helicopter = helicopterRepository.findById(dto.getWareId()).get();
                work.setHelicopter(helicopter);
            }
            case ("missile") -> {
                Missile missile = missileRepository.findById(dto.getWareId()).get();
                work.setMissile(missile);
            }
        }
        return workRepository.save(work);
    }

    public void finish(Integer id){
        Work work = workRepository.findById(id).get();
        if(work.getFinishWork() == null)
            work.setFinishWork(LocalDate.now());
        workRepository.save(work);
    }

}

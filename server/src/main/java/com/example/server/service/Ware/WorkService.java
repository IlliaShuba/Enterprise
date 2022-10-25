package com.example.server.service.Ware;

import com.example.server.dto.AirplaneDto;
import com.example.server.dto.EquipmentDto;
import com.example.server.dto.WorkDto;
import com.example.server.entity.Area;
import com.example.server.entity.Brigade;
import com.example.server.entity.Equipment;
import com.example.server.entity.Ware.*;
import com.example.server.repository.AreaRepository;
import com.example.server.repository.BrigadeRepository;
import com.example.server.repository.WareRepo.*;
import com.example.server.repository.WorkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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
                if(!airplane.getWork().isEmpty()){
                    List<Integer> ids = new ArrayList<>();
                    for (Work item : airplane.getWork()) {
                        ids.add(item.getId());
                    }
                    Integer id = Collections.max(ids);
                    finish(id);
                }
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
   public List<WorkDto> getByType(String ware) {
       List<Work> works = new ArrayList<>();
       switch (ware) {
           case "airplane": works = workRepository.queryAllByAirplaneNotNull();break;
           case "glider": works = workRepository.queryAllByGliderNotNull();break;
           case "hang-glider": works = workRepository.queryAllByHangGliderNotNull();break;
           case "helicopter": works = workRepository.queryAllByHelicopterNotNull();break;
           case "missile": works = workRepository.queryAllByMissileNotNull();break;
       }
       return works.stream().map(this::toDto).collect(Collectors.toList());
   }

    public List<WorkDto> getByWareId(String ware, Integer id) {
        List<Work> works = new ArrayList<>();
        switch (ware) {
            case "airplane": works = workRepository.queryWorksByAirplane_Id(id);break;
            case "glider": works = workRepository.queryWorksByGlider_Id(id);break;
            case "hang-glider": works = workRepository.queryWorksByHangGlider_Id(id);break;
            case "helicopter": works = workRepository.queryWorksByHelicopter_Id(id);break;
            case "missile": works = workRepository.queryWorksByMissile_Id(id);break;
        }
        return works.stream().map(this::toDto).collect(Collectors.toList());
    }

        public void finish(Integer id){
        Work work = workRepository.findById(id).get();
        if(work.getFinishWork() == null)
            work.setFinishWork(LocalDate.now());
        workRepository.save(work);
    }

    public WorkDto toDto(Work entity){
        WorkDto dto = new WorkDto();
        dto.setId(entity.getId());
        dto.setTypeOfWork(entity.getTypeOfWork());
        dto.setStartWork(entity.getStartWork());
        dto.setFinishWork(entity.getFinishWork());
        if(entity.getAirplane() != null){
            dto.setWare("airplane");
            dto.setWareId(entity.getAirplane().getId());
        }
        else if(entity.getGlider() != null){
            dto.setWare("glider");
            dto.setWareId(entity.getGlider().getId());
        }
        else if(entity.getHangGlider() != null){
            dto.setWare("hang-glider");
            dto.setWareId(entity.getHangGlider().getId());
        }
        else if(entity.getHelicopter() != null){
            dto.setWare("helicopter");
            dto.setWareId(entity.getHelicopter().getId());
        }
        else if(entity.getMissile() != null){
            dto.setWare("missile");
            dto.setWareId(entity.getMissile().getId());
        }

        return dto;
    }
}

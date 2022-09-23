package com.example.server.service.Ware;

import com.example.server.dto.AirplaneDto;
import com.example.server.entity.Area;
import com.example.server.entity.Equipment;
import com.example.server.entity.Laboratory;
import com.example.server.entity.Shop;
import com.example.server.entity.Ware.Airplane;
import com.example.server.repository.AreaRepository;
import com.example.server.repository.EquipmentRepository;
import com.example.server.repository.LaboratoryRepository;
import com.example.server.repository.ShopRepository;
import com.example.server.repository.WareRepo.AirplaneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Service
public class AirplaneService {
    @Autowired
    private AirplaneRepository airplaneRepository;
    @Autowired
    private ShopRepository shopRepository;
    @Autowired
    private AreaRepository areaRepository;
    @Autowired
    private LaboratoryRepository laboratoryRepository;
    @Autowired
    private EquipmentRepository equipmentRepository;


    public Airplane create(AirplaneDto dto){
        Airplane airplane = new Airplane();
        airplane.setTypeOfWorks(dto.getTypeOfWorks());
        airplane.setNumberOfEngines(dto.getNumberOfEngines());
        airplane.setStartCreate(LocalDateTime.now());
        airplane.setFinishCreate(null);
        airplane.setStartTest(null);
        airplane.setFinishTest(null);

        Shop shop = shopRepository.findById(dto.getShop()).get();
        Area area = areaRepository.findById(dto.getArea()).get();
        Laboratory laboratory = laboratoryRepository.findById(dto.getLab()).get();
        airplane.setShop(shop);
        airplane.setArea(area);
        airplane.setLaboratory(laboratory);
        for (Integer id : dto.getEquipment()) {
            Equipment equipment = equipmentRepository.findById(id).get();
            equipment.setAirplane(airplane);
        }
        return airplaneRepository.save(airplane);
    }
    public AirplaneDto getById(Integer id){
        Airplane airplane = airplaneRepository.findById(id).get();
        return toDto(airplane);
    }

    public AirplaneDto toDto(Airplane entity){
        AirplaneDto dto = new AirplaneDto();
        dto.setId(entity.getId());
        dto.setTypeOfWorks(entity.getTypeOfWorks());
        dto.setNumberOfEngines(entity.getNumberOfEngines());
        dto.setStartCreate(entity.getStartCreate());
        dto.setFinishCreate(entity.getFinishCreate());
        dto.setStartTest(entity.getStartTest());
        dto.setFinishTest(entity.getFinishTest());
        dto.setShop(entity.getShop().getId());
        dto.setArea(entity.getArea().getId());
        dto.setLab(entity.getLaboratory().getId());
        Set<Integer> equipment= new HashSet<>();
        for (Equipment item :entity.getEquipment()) {
            equipment.add(item.getId());
        }
        dto.setEquipment(equipment);
        return dto;
    }
}
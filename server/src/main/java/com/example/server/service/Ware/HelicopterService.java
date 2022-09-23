package com.example.server.service.Ware;

import com.example.server.dto.HelicopterDto;
import com.example.server.entity.Area;
import com.example.server.entity.Equipment;
import com.example.server.entity.Laboratory;
import com.example.server.entity.Shop;
import com.example.server.entity.Ware.Helicopter;
import com.example.server.repository.AreaRepository;
import com.example.server.repository.EquipmentRepository;
import com.example.server.repository.LaboratoryRepository;
import com.example.server.repository.ShopRepository;
import com.example.server.repository.WareRepo.HelicopterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Service
public class HelicopterService {
    @Autowired
    private HelicopterRepository helicopterRepository;
    @Autowired
    private ShopRepository shopRepository;
    @Autowired
    private AreaRepository areaRepository;
    @Autowired
    private LaboratoryRepository laboratoryRepository;
    @Autowired
    private EquipmentRepository equipmentRepository;


    public Helicopter create(HelicopterDto dto){
        Helicopter helicopter = new Helicopter();
        helicopter.setTypeOfWorks(dto.getTypeOfWorks());
        helicopter.setEnginePower(dto.getEnginePower());
        helicopter.setStartCreate(LocalDateTime.now());
        helicopter.setFinishCreate(null);
        helicopter.setStartTest(null);
        helicopter.setFinishTest(null);

        Shop shop = shopRepository.findById(dto.getShop()).get();
        Area area = areaRepository.findById(dto.getArea()).get();
        Laboratory laboratory = laboratoryRepository.findById(dto.getLab()).get();
        helicopter.setShop(shop);
        helicopter.setArea(area);
        helicopter.setLaboratory(laboratory);
        for (Integer id : dto.getEquipment()) {
            Equipment equipment = equipmentRepository.findById(id).get();
            equipment.setHelicopter(helicopter);
        }
        return helicopterRepository.save(helicopter);
    }
    public HelicopterDto getById(Integer id){
        Helicopter helicopter = helicopterRepository.findById(id).get();
        return toDto(helicopter);
    }

    public HelicopterDto toDto(Helicopter entity){
        HelicopterDto dto = new HelicopterDto();
        dto.setId(entity.getId());
        dto.setTypeOfWorks(entity.getTypeOfWorks());
        dto.setEnginePower(entity.getEnginePower());
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

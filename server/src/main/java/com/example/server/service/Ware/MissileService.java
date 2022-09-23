package com.example.server.service.Ware;

import com.example.server.dto.MissileDto;
import com.example.server.entity.Area;
import com.example.server.entity.Equipment;
import com.example.server.entity.Laboratory;
import com.example.server.entity.Shop;
import com.example.server.entity.Ware.Missile;
import com.example.server.repository.AreaRepository;
import com.example.server.repository.EquipmentRepository;
import com.example.server.repository.LaboratoryRepository;
import com.example.server.repository.ShopRepository;
import com.example.server.repository.WareRepo.MissileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Service
public class MissileService {
    @Autowired
    private MissileRepository missileRepository;
    @Autowired
    private ShopRepository shopRepository;
    @Autowired
    private AreaRepository areaRepository;
    @Autowired
    private LaboratoryRepository laboratoryRepository;
    @Autowired
    private EquipmentRepository equipmentRepository;


    public Missile create(MissileDto dto){
        Missile missile = new Missile();
        missile.setTypeOfWorks(dto.getTypeOfWorks());
        missile.setChargePower(dto.getChargePower());
        missile.setStartCreate(LocalDateTime.now());
        missile.setFinishCreate(null);
        missile.setStartTest(null);
        missile.setFinishTest(null);

        Shop shop = shopRepository.findById(dto.getShop()).get();
        Area area = areaRepository.findById(dto.getArea()).get();
        Laboratory laboratory = laboratoryRepository.findById(dto.getLab()).get();
        missile.setShop(shop);
        missile.setArea(area);
        missile.setLaboratory(laboratory);
        for (Integer id : dto.getEquipment()) {
            Equipment equipment = equipmentRepository.findById(id).get();
            equipment.setMissile(missile);
        }
        return missileRepository.save(missile);
    }
    public MissileDto getById(Integer id){
        Missile missile = missileRepository.findById(id).get();
        return toDto(missile);
    }

    public MissileDto toDto(Missile entity){
        MissileDto dto = new MissileDto();
        dto.setId(entity.getId());
        dto.setTypeOfWorks(entity.getTypeOfWorks());
        dto.setChargePower(entity.getChargePower());
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

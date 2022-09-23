package com.example.server.service.Ware;

import com.example.server.dto.HangGliderDto;
import com.example.server.entity.Area;
import com.example.server.entity.Equipment;
import com.example.server.entity.Laboratory;
import com.example.server.entity.Shop;
import com.example.server.entity.Ware.HangGlider;
import com.example.server.repository.AreaRepository;
import com.example.server.repository.EquipmentRepository;
import com.example.server.repository.LaboratoryRepository;
import com.example.server.repository.ShopRepository;
import com.example.server.repository.WareRepo.HangGliderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Service
public class HangGliderService {
    @Autowired
    private HangGliderRepository hangGliderRepository;
    @Autowired
    private ShopRepository shopRepository;
    @Autowired
    private AreaRepository areaRepository;
    @Autowired
    private LaboratoryRepository laboratoryRepository;
    @Autowired
    private EquipmentRepository equipmentRepository;


    public HangGlider create(HangGliderDto dto){
        HangGlider hangGlider = new HangGlider();
        hangGlider.setTypeOfWorks(dto.getTypeOfWorks());
        hangGlider.setWeight(dto.getWeight());
        hangGlider.setStartCreate(LocalDateTime.now());
        hangGlider.setFinishCreate(null);
        hangGlider.setStartTest(null);
        hangGlider.setFinishTest(null);

        Shop shop = shopRepository.findById(dto.getShop()).get();
        Area area = areaRepository.findById(dto.getArea()).get();
        Laboratory laboratory = laboratoryRepository.findById(dto.getLab()).get();
        hangGlider.setShop(shop);
        hangGlider.setArea(area);
        hangGlider.setLaboratory(laboratory);
        for (Integer id : dto.getEquipment()) {
            Equipment equipment = equipmentRepository.findById(id).get();
            equipment.setHangGlider(hangGlider);
        }
        return hangGliderRepository.save(hangGlider);
    }
    public HangGliderDto getById(Integer id){
        HangGlider hangGlider = hangGliderRepository.findById(id).get();
        return toDto(hangGlider);
    }

    public HangGliderDto toDto(HangGlider entity){
        HangGliderDto dto = new HangGliderDto();
        dto.setId(entity.getId());
        dto.setTypeOfWorks(entity.getTypeOfWorks());
        dto.setWeight(entity.getWeight());
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

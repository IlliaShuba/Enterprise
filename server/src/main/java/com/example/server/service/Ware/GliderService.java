package com.example.server.service.Ware;

import com.example.server.dto.GliderDto;
import com.example.server.entity.Equipment;
import com.example.server.entity.Laboratory;
import com.example.server.entity.Shop;
import com.example.server.entity.Ware.Glider;
import com.example.server.repository.AreaRepository;
import com.example.server.repository.EquipmentRepository;
import com.example.server.repository.LaboratoryRepository;
import com.example.server.repository.ShopRepository;
import com.example.server.repository.WareRepo.GliderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class GliderService {
    @Autowired
    private GliderRepository gliderRepository;
    @Autowired
    private ShopRepository shopRepository;
    @Autowired
    private AreaRepository areaRepository;
    @Autowired
    private LaboratoryRepository laboratoryRepository;
    @Autowired
    private EquipmentRepository equipmentRepository;


    public Glider create(GliderDto dto){
        Glider glider = new Glider();
        glider.setWeight(dto.getWeight());
        glider.setStartCreate(LocalDate.now());
        glider.setFinishCreate(null);
        glider.setStartTest(null);
        glider.setFinishTest(null);

        Shop shop = shopRepository.findById(dto.getShop()).get();
        Laboratory laboratory = laboratoryRepository.findById(dto.getLab()).get();
        glider.setShop(shop);
        glider.setLaboratory(laboratory);
        for (Integer id : dto.getEquipment()) {
            Equipment equipment = equipmentRepository.findById(id).get();
            equipment.setGlider(glider);
        }
        return gliderRepository.save(glider);
    }
    public GliderDto getById(Integer id){
        Glider glider = gliderRepository.findById(id).get();
        return toDto(glider);
    }

    public GliderDto toDto(Glider entity){
        GliderDto dto = new GliderDto();
        dto.setId(entity.getId());
        dto.setWeight(entity.getWeight());
        dto.setStartCreate(entity.getStartCreate());
        dto.setFinishCreate(entity.getFinishCreate());
        dto.setStartTest(entity.getStartTest());
        dto.setFinishTest(entity.getFinishTest());
        dto.setShop(entity.getShop().getId());
        dto.setLab(entity.getLaboratory().getId());
        List<Integer> equipment= new ArrayList<>();
        for (Equipment item :entity.getEquipment()) {
            equipment.add(item.getId());
        }
        dto.setEquipment(equipment);
        return dto;
    }
}

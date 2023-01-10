package com.example.server.service.Ware;

import com.example.server.dto.AirplaneDto;
import com.example.server.entity.Equipment;
import com.example.server.entity.Laboratory;
import com.example.server.entity.Shop;
import com.example.server.entity.Ware.Airplane;
import com.example.server.repository.EquipmentRepository;
import com.example.server.repository.LaboratoryRepository;
import com.example.server.repository.ShopRepository;
import com.example.server.repository.WareRepo.AirplaneRepository;
import com.example.server.service.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
@Service
public abstract class AirplaneBuilder {
    @Autowired
    private ShopRepository shopRepository;
    @Autowired
    private LaboratoryRepository laboratoryRepository;
    @Autowired
    private EquipmentRepository equipmentRepository;

    Airplane airplane;

    void create(AirplaneDto dto){
        airplane = new Airplane();
        airplane.setStartCreate(LocalDate.now());
        airplane.setFinishCreate(null);
        airplane.setStartTest(null);
        airplane.setFinishTest(null);

        Shop shop = shopRepository.findById(dto.getShop()).get();
        Laboratory laboratory = laboratoryRepository.findById(dto.getLab()).get();
        airplane.setShop(shop);
        airplane.setLaboratory(laboratory);
        for (Integer id : dto.getEquipment()) {
            Equipment equipment = equipmentRepository.findById(id).get();
            equipment.setAirplane(airplane);
        }
    }

    abstract void buildEngines();
    abstract void buildWeight();
    abstract void buildDescription();

    Airplane getAirplane(){
        return airplane;
    }

}

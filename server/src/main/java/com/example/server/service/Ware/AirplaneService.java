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

import java.util.Collection;
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


    public Airplane create(AirplaneDto dto, Integer shopId, Integer areaId, Integer labId, Iterable<Integer> equipmentId){
        Airplane airplane = new Airplane();
        airplane.setTypeOfWorks(dto.getTypeOfWorks());
        airplane.setNumberOfEngines(dto.getNumberOfEngines());
        airplane.setStartCreate(dto.getStartCreate());
        airplane.setFinishCreate(dto.getFinishCreate());
        airplane.setStartTest(dto.getStartTest());
        airplane.setFinishTest(dto.getFinishTest());

        Shop shop = shopRepository.findById(shopId).get();
        Area area = areaRepository.findById(areaId).get();
        Laboratory laboratory = laboratoryRepository.findById(labId).get();
        Set<Equipment> equipment = new HashSet<>((Collection<? extends Equipment>) equipmentRepository.findAllById(equipmentId));

        airplane.setShop(shop);
        airplane.setArea(area);
        airplane.setLaboratory(laboratory);
        airplane.setEquipment(equipment);
        return airplaneRepository.save(airplane);
    }
    public Airplane getById(Integer id){return airplaneRepository.findById(id).get();}

}

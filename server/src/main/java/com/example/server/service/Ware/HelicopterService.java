package com.example.server.service.Ware;

import com.example.server.dto.AirplaneDto;
import com.example.server.dto.EquipmentDto;
import com.example.server.dto.HangGliderDto;
import com.example.server.dto.HelicopterDto;
import com.example.server.entity.Equipment;
import com.example.server.entity.Laboratory;
import com.example.server.entity.Shop;
import com.example.server.entity.Ware.Airplane;
import com.example.server.entity.Ware.HangGlider;
import com.example.server.entity.Ware.Helicopter;
import com.example.server.repository.AreaRepository;
import com.example.server.repository.EquipmentRepository;
import com.example.server.repository.LaboratoryRepository;
import com.example.server.repository.ShopRepository;
import com.example.server.repository.WareRepo.HelicopterRepository;
import com.example.server.service.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class HelicopterService {
    @Autowired
    private HelicopterRepository helicopterRepository;
    @Autowired
    private ShopRepository shopRepository;
    @Autowired
    private LaboratoryRepository laboratoryRepository;
    @Autowired
    private EquipmentRepository equipmentRepository;
    @Autowired
    private EquipmentService equipmentService;


    public Helicopter create(HelicopterDto dto){
        Helicopter helicopter = new Helicopter();
        helicopter.setEnginePower(dto.getEnginePower());
        helicopter.setStartCreate(LocalDate.now());
        helicopter.setFinishCreate(null);
        helicopter.setStartTest(null);
        helicopter.setFinishTest(null);

        Shop shop = shopRepository.findById(dto.getShop()).get();
        Laboratory laboratory = laboratoryRepository.findById(dto.getLab()).get();
        helicopter.setShop(shop);
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
    public List<HelicopterDto> getAll(){
        List<HelicopterDto> response = new ArrayList<>();

        for (Helicopter item : helicopterRepository.findAll()) {
            response.add(toDto(item));
        }
        return response;
    }

    public List<HelicopterDto> getByShop(Integer id){
        List<HelicopterDto> response = new ArrayList<>();

        for (Helicopter item : helicopterRepository.queryHelicopterByShop_Id(id)) {
            response.add(toDto(item));
        }
        return response;
    }
    public List<HelicopterDto> getByLaboratory(Integer id){
        List<HelicopterDto> response = new ArrayList<>();

        for (Helicopter item : helicopterRepository.queryHelicopterByLaboratory_Id(id)) {
            response.add(toDto(item));
        }
        return response;
    }

    public List<Helicopter> getByInterval(String firstDate,String secondDate){
        return helicopterRepository.queryFirstByFinishCreateAfterAndFinishCreateBefore(LocalDate.parse(firstDate), LocalDate.parse(secondDate));
    }

    public Helicopter finishCreate(Integer id){
        Helicopter helicopter = helicopterRepository.findById(id).get();
        helicopter.setFinishCreate(LocalDate.now());
        helicopter.setStartTest(LocalDate.now());
        return helicopterRepository.save(helicopter);
    }
    public Helicopter finishTest(Integer id){
        Helicopter helicopter = helicopterRepository.findById(id).get();
        helicopter.setFinishTest(LocalDate.now());
        return helicopterRepository.save(helicopter);
    }
    public void delete(Integer id){
        helicopterRepository.deleteById(id);
    }

    public HelicopterDto toDto(Helicopter entity){
        HelicopterDto dto = new HelicopterDto();
        dto.setId(entity.getId());
        dto.setEnginePower(entity.getEnginePower());
        dto.setStartCreate(entity.getStartCreate());
        dto.setFinishCreate(entity.getFinishCreate());
        dto.setStartTest(entity.getStartTest());
        dto.setFinishTest(entity.getFinishTest());
        dto.setShop(entity.getShop().getId());
        dto.setLab(entity.getLaboratory().getId());

        List<EquipmentDto> equipment = equipmentService.getByWareId(entity.getId(), "helicopter");
        List<Integer> equipment_ids= new ArrayList<>();
        for (EquipmentDto elem : equipment){
            equipment_ids.add(elem.getId());
        }
        dto.setEquipment(equipment_ids);
        return dto;
    }
}

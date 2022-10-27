package com.example.server.service.Ware;

import com.example.server.dto.AirplaneDto;
import com.example.server.dto.EquipmentDto;
import com.example.server.entity.Equipment;
import com.example.server.entity.Laboratory;
import com.example.server.entity.Shop;
import com.example.server.entity.Ware.Airplane;
import com.example.server.entity.Ware.Work;
import com.example.server.repository.AreaRepository;
import com.example.server.repository.EquipmentRepository;
import com.example.server.repository.LaboratoryRepository;
import com.example.server.repository.ShopRepository;
import com.example.server.repository.WareRepo.AirplaneRepository;
import com.example.server.service.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class AirplaneService {
    @Autowired
    private AirplaneRepository airplaneRepository;
    @Autowired
    private ShopRepository shopRepository;
    @Autowired
    private LaboratoryRepository laboratoryRepository;
    @Autowired
    private EquipmentRepository equipmentRepository;
    @Autowired
    private EquipmentService equipmentService;
    @Autowired
    private WorkService workService;


    public Airplane create(AirplaneDto dto){
        Airplane airplane = new Airplane();
        airplane.setNumberOfEngines(dto.getNumberOfEngines());
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
        return airplaneRepository.save(airplane);
    }
    public AirplaneDto getById(Integer id){
        Airplane airplane = airplaneRepository.findById(id).get();
        return toDto(airplane);
    }

    public List<AirplaneDto> getAll(){
        List<AirplaneDto> response = new ArrayList<>();

        for (Airplane item : airplaneRepository.findAll()) {
            response.add(toDto(item));
        }
        return response;
    }
    public List<AirplaneDto> getByShop(Integer id){
        List<AirplaneDto> response = new ArrayList<>();

        for (Airplane item : airplaneRepository.queryAirplanesByShop_Id(id)) {
            response.add(toDto(item));
        }
        return response;
    }
    public List<AirplaneDto> getByLaboratory(Integer id){
        List<AirplaneDto> response = new ArrayList<>();

        for (Airplane item : airplaneRepository.queryAirplanesByLaboratory_Id(id)) {
            response.add(toDto(item));
        }
        return response;
    }

    public List<Airplane> getByInterval(String firstDate,String secondDate){
        return airplaneRepository.queryFirstByFinishCreateAfterAndFinishCreateBefore(LocalDate.parse(firstDate), LocalDate.parse(secondDate));
    }


    public Airplane finishCreate(Integer id){
        Airplane airplane = airplaneRepository.findById(id).get();
        airplane.setFinishCreate(LocalDate.now());
        airplane.setStartTest(LocalDate.now());

        if(!airplane.getWork().isEmpty()){
            List<Integer> ids = new ArrayList<>();
            for (Work item : airplane.getWork()) {
                ids.add(item.getId());
            }
            Integer work = Collections.max(ids);
            workService.finish(work);
        }
        return airplaneRepository.save(airplane);
    }
    public Airplane finishTest(Integer id){
        Airplane airplane = airplaneRepository.findById(id).get();
        airplane.setFinishTest(LocalDate.now());
        return airplaneRepository.save(airplane);
    }
    public void delete(Integer id){
        airplaneRepository.deleteById(id);
    }

    public AirplaneDto toDto(Airplane entity){
        AirplaneDto dto = new AirplaneDto();
        dto.setId(entity.getId());
        dto.setNumberOfEngines(entity.getNumberOfEngines());
        dto.setStartCreate(entity.getStartCreate());
        dto.setFinishCreate(entity.getFinishCreate());
        dto.setStartTest(entity.getStartTest());
        dto.setFinishTest(entity.getFinishTest());
        dto.setShop(entity.getShop().getId());
        dto.setLab(entity.getLaboratory().getId());

        List<EquipmentDto> equipment = equipmentService.getByWareId(entity.getId());
        List<Integer> equipment_ids= new ArrayList<>();
        for (EquipmentDto elem : equipment){
            equipment_ids.add(elem.getId());
        }
        dto.setEquipment(equipment_ids);
        return dto;
    }
}

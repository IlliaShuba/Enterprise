package com.example.server.service.Ware;

import com.example.server.dto.AirplaneDto;
import com.example.server.dto.EquipmentDto;
import com.example.server.dto.GliderDto;
import com.example.server.entity.Equipment;
import com.example.server.entity.Laboratory;
import com.example.server.entity.Shop;
import com.example.server.entity.Ware.Airplane;
import com.example.server.entity.Ware.Glider;
import com.example.server.repository.AreaRepository;
import com.example.server.repository.EquipmentRepository;
import com.example.server.repository.LaboratoryRepository;
import com.example.server.repository.ShopRepository;
import com.example.server.repository.WareRepo.GliderRepository;
import com.example.server.service.EquipmentService;
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
    @Autowired
    private EquipmentService equipmentService;


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
    public List<GliderDto> getAll(){
        List<GliderDto> response = new ArrayList<>();

        for (Glider item : gliderRepository.findAll()) {
            response.add(toDto(item));
        }
        return response;
    }

    public List<GliderDto> getByShop(Integer id){
        List<GliderDto> response = new ArrayList<>();

        for (Glider item : gliderRepository.queryGliderByShop_Id(id)) {
            response.add(toDto(item));
        }
        return response;
    }
    public List<GliderDto> getByLaboratory(Integer id){
        List<GliderDto> response = new ArrayList<>();

        for (Glider item : gliderRepository.queryGliderByLaboratory_Id(id)) {
            response.add(toDto(item));
        }
        return response;
    }

    public List<Glider> getByInterval(String firstDate,String secondDate){
        return gliderRepository.queryFirstByFinishCreateAfterAndFinishCreateBefore(LocalDate.parse(firstDate), LocalDate.parse(secondDate));
    }


    public Glider finishCreate(Integer id){
        Glider glider = gliderRepository.findById(id).get();
        glider.setFinishCreate(LocalDate.now());
        glider.setStartTest(LocalDate.now());
        return gliderRepository.save(glider);
    }
    public Glider finishTest(Integer id){
        Glider glider = gliderRepository.findById(id).get();
        glider.setFinishTest(LocalDate.now());
        return gliderRepository.save(glider);
    }
    public void delete(Integer id){
        gliderRepository.deleteById(id);
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

        List<EquipmentDto> equipment = equipmentService.getByWareId(entity.getId(), "glider");
        List<Integer> equipment_ids= new ArrayList<>();
        for (EquipmentDto elem : equipment){
            equipment_ids.add(elem.getId());
        }
        dto.setEquipment(equipment_ids);
        return dto;
    }
}

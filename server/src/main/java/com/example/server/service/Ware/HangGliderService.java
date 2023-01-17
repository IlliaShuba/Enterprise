package com.example.server.service.Ware;

import com.example.server.dto.AirplaneDto;
import com.example.server.dto.EquipmentDto;
import com.example.server.dto.GliderDto;
import com.example.server.dto.HangGliderDto;
import com.example.server.entity.Equipment;
import com.example.server.entity.Laboratory;
import com.example.server.entity.Shop;
import com.example.server.entity.Ware.Airplane;
import com.example.server.entity.Ware.Glider;
import com.example.server.entity.Ware.HangGlider;
import com.example.server.repository.AreaRepository;
import com.example.server.repository.EquipmentRepository;
import com.example.server.repository.LaboratoryRepository;
import com.example.server.repository.ShopRepository;
import com.example.server.repository.WareRepo.HangGliderRepository;
import com.example.server.service.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class HangGliderService{
    @Autowired
    private HangGliderRepository hangGliderRepository;
    @Autowired
    private ShopRepository shopRepository;
    @Autowired
    private LaboratoryRepository laboratoryRepository;
    @Autowired
    private EquipmentRepository equipmentRepository;
    @Autowired
    private EquipmentService equipmentService;


    public HangGlider create(HangGliderDto dto){
        HangGlider hangGlider = new HangGlider();
        hangGlider.setWeight(dto.getWeight());
        hangGlider.setStartCreate(LocalDate.now());
        hangGlider.setFinishCreate(null);
        hangGlider.setStartTest(null);
        hangGlider.setFinishTest(null);

        Shop shop = shopRepository.findById(dto.getShop()).get();
        Laboratory laboratory = laboratoryRepository.findById(dto.getLab()).get();
        hangGlider.setShop(shop);
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
    public List<HangGliderDto> getAll(){
        List<HangGliderDto> response = new ArrayList<>();

        for (HangGlider item : hangGliderRepository.findAll()) {
            response.add(toDto(item));
        }
        return response;
    }

    public List<HangGliderDto> getByShop(Integer id){
        List<HangGliderDto> response = new ArrayList<>();

        for (HangGlider item : hangGliderRepository.queryHangGliderByShop_Id(id)) {
            response.add(toDto(item));
        }
        return response;
    }
    public List<HangGliderDto> getByLaboratory(Integer id){
        List<HangGliderDto> response = new ArrayList<>();

        for (HangGlider item : hangGliderRepository.queryHangGliderByLaboratory_Id(id)) {
            response.add(toDto(item));
        }
        return response;
    }

    public List<HangGlider> getByInterval(String firstDate,String secondDate){
        return hangGliderRepository.queryFirstByFinishCreateAfterAndFinishCreateBefore(LocalDate.parse(firstDate), LocalDate.parse(secondDate));
    }

    public HangGlider finishCreate(Integer id){
        HangGlider hangGlider = hangGliderRepository.findById(id).get();
        hangGlider.setFinishCreate(LocalDate.now());
        hangGlider.setStartTest(LocalDate.now());
        return hangGliderRepository.save(hangGlider);
    }
    public HangGlider finishTest(Integer id){
        HangGlider hangGlider = hangGliderRepository.findById(id).get();
        hangGlider.setFinishTest(LocalDate.now());
        return hangGliderRepository.save(hangGlider);
    }
    public void delete(Integer id){
        hangGliderRepository.deleteById(id);
    }

    public HangGliderDto toDto(HangGlider entity){
        HangGliderDto dto = new HangGliderDto();
        dto.setId(entity.getId());
        dto.setWeight(entity.getWeight());
        dto.setStartCreate(entity.getStartCreate());
        dto.setFinishCreate(entity.getFinishCreate());
        dto.setStartTest(entity.getStartTest());
        dto.setFinishTest(entity.getFinishTest());
        dto.setShop(entity.getShop().getId());
        dto.setLab(entity.getLaboratory().getId());

        List<EquipmentDto> equipment = equipmentService.getByWareId(entity.getId(), "hang-glider");
        List<Integer> equipment_ids= new ArrayList<>();
        for (EquipmentDto elem : equipment){
            equipment_ids.add(elem.getId());
        }
        dto.setEquipment(equipment_ids);
        return dto;
    }
}

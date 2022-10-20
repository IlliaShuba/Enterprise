package com.example.server.service.Ware;

import com.example.server.dto.HelicopterDto;
import com.example.server.dto.MissileDto;
import com.example.server.entity.Equipment;
import com.example.server.entity.Laboratory;
import com.example.server.entity.Shop;
import com.example.server.entity.Ware.Helicopter;
import com.example.server.entity.Ware.Missile;
import com.example.server.repository.AreaRepository;
import com.example.server.repository.EquipmentRepository;
import com.example.server.repository.LaboratoryRepository;
import com.example.server.repository.ShopRepository;
import com.example.server.repository.WareRepo.MissileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class MissileService {
    @Autowired
    private MissileRepository missileRepository;
    @Autowired
    private ShopRepository shopRepository;
    @Autowired
    private LaboratoryRepository laboratoryRepository;
    @Autowired
    private EquipmentRepository equipmentRepository;


    public Missile create(MissileDto dto){
        Missile missile = new Missile();
        missile.setChargePower(dto.getChargePower());
        missile.setStartCreate(LocalDate.now());
        missile.setFinishCreate(null);
        missile.setStartTest(null);
        missile.setFinishTest(null);

        Shop shop = shopRepository.findById(dto.getShop()).get();
        Laboratory laboratory = laboratoryRepository.findById(dto.getLab()).get();
        missile.setShop(shop);
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
    public List<MissileDto> getAll(){
        List<MissileDto> response = new ArrayList<>();

        for (Missile item : missileRepository.findAll()) {
            response.add(toDto(item));
        }
        return response;
    }

    public List<Missile> getByInterval(String firstDate,String secondDate){
        return missileRepository.queryFirstByFinishCreateAfterAndFinishCreateBefore(LocalDate.parse(firstDate), LocalDate.parse(secondDate));
    }

    public Missile finishCreate(Integer id){
        Missile missile = missileRepository.findById(id).get();
        missile.setFinishCreate(LocalDate.now());
        missile.setStartTest(LocalDate.now());
        return missileRepository.save(missile);
    }
    public Missile finishTest(Integer id){
        Missile missile = missileRepository.findById(id).get();
        missile.setFinishTest(LocalDate.now());
        return missileRepository.save(missile);
    }
    public void delete(Integer id){
        missileRepository.deleteById(id);
    }

    public MissileDto toDto(Missile entity){
        MissileDto dto = new MissileDto();
        dto.setId(entity.getId());
        dto.setChargePower(entity.getChargePower());
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

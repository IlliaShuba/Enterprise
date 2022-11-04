package com.example.server.service;

import com.example.server.dto.EquipmentDto;
import com.example.server.entity.Equipment;
import com.example.server.entity.Laboratory;
import com.example.server.entity.Shop;
import com.example.server.repository.EquipmentRepository;
import com.example.server.repository.LaboratoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EquipmentService {
    @Autowired
    private EquipmentRepository equipmentRepository;
    @Autowired
    private LaboratoryRepository laboratoryRepository;

    public Equipment create(EquipmentDto dto, Integer labId) {
        Equipment equipment = new Equipment();
        Laboratory laboratory = laboratoryRepository.findById(labId).get();

        equipment.setType(dto.getType());
        equipment.setLaboratory(laboratory);
        return equipmentRepository.save(equipment);
    }

    public EquipmentDto getById(Integer id) {
        return toDto(equipmentRepository.findById(id).get());
    }

    public List<EquipmentDto> getAll() {
        List<Equipment> shops = equipmentRepository.findAll();
        return shops.stream().map(this::toDto).collect(Collectors.toList());
    }

    public List<EquipmentDto> getByWareId(Integer id, String type) {
        List<Equipment> equipment = new ArrayList<>();
        switch (type) {
            case "airplane":
                equipment = equipmentRepository.queryAllByAirplaneId(id);
                return equipment.stream().map(this::toDto).collect(Collectors.toList());
            case "glider":
                equipment = equipmentRepository.queryAllByGliderId(id);
                return equipment.stream().map(this::toDto).collect(Collectors.toList());
            case "hang-glider":
                equipment = equipmentRepository.queryAllByHangGliderId(id);
                return equipment.stream().map(this::toDto).collect(Collectors.toList());
            case "helicopter":
                equipment = equipmentRepository.queryAllByHelicopterId(id);
                return equipment.stream().map(this::toDto).collect(Collectors.toList());
            case "missile":
                equipment = equipmentRepository.queryAllByMissileId(id);
                return equipment.stream().map(this::toDto).collect(Collectors.toList());
            default:
                return null;
        }
    }

    public void delete(Integer id){equipmentRepository.deleteById(id);}

    public EquipmentDto toDto(Equipment entity){
        EquipmentDto dto = new EquipmentDto();
        dto.setId(entity.getId());
        dto.setType(entity.getType());
        dto.setNumber_laboratory(entity.getLaboratory().getId());
        return dto;
    }
}

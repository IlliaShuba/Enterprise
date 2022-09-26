package com.example.server.service;

import com.example.server.dto.EquipmentDto;
import com.example.server.entity.Equipment;
import com.example.server.entity.Laboratory;
import com.example.server.repository.EquipmentRepository;
import com.example.server.repository.LaboratoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class EquipmentService {
    @Autowired
    private EquipmentRepository equipmentRepository;
    @Autowired
    private LaboratoryRepository laboratoryRepository;

    public Equipment create(EquipmentDto dto, Integer labId){
        Equipment equipment = new Equipment();
        Laboratory laboratory = laboratoryRepository.findById(labId).get();

        equipment.setName(dto.getName());
        equipment.setLaboratory(laboratory);
        return equipmentRepository.save(equipment);
    }

    public Iterable<Equipment> getAll(){return equipmentRepository.findAll();}

    public Set<EquipmentDto> getByWareId(Integer id){
        Set<EquipmentDto> response = new HashSet<>();
        for (Equipment equipment : equipmentRepository.queryAllByAirplaneId(id)) {
            response.add(toDto(equipment));
        }
        return response;
    }

    public EquipmentDto toDto(Equipment entity){
        EquipmentDto dto = new EquipmentDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        return dto;
    }
}

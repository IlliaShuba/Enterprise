package com.example.server.service;

import com.example.server.dto.EquipmentDto;
import com.example.server.entity.Equipment;
import com.example.server.entity.Laboratory;
import com.example.server.repository.EquipmentRepository;
import com.example.server.repository.LaboratoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}

package com.example.server.repository;

import com.example.server.entity.Equipment;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface EquipmentRepository extends CrudRepository<Equipment, Integer> {
    Set<Equipment> queryAllByAirplaneId(Integer id);
}

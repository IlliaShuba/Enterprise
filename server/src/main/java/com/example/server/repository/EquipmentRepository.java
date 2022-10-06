package com.example.server.repository;

import com.example.server.entity.Equipment;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EquipmentRepository extends CrudRepository<Equipment, Integer> {
    List<Equipment> queryAllByAirplaneId(Integer id);
}

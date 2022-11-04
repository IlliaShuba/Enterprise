package com.example.server.repository;

import com.example.server.entity.Equipment;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EquipmentRepository extends CrudRepository<Equipment, Integer> {
    List<Equipment> findAll();
    List<Equipment> queryAllByAirplaneId(Integer id);
    List<Equipment> queryAllByHangGliderId(Integer id);
    List<Equipment> queryAllByGliderId(Integer id);
    List<Equipment> queryAllByHelicopterId(Integer id);
    List<Equipment> queryAllByMissileId(Integer id);
}

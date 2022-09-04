package com.example.server.repository;

import com.example.server.entity.Equipment;
import org.springframework.data.repository.CrudRepository;

public interface EquipmentRepository extends CrudRepository<Equipment, Integer> {
}

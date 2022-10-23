package com.example.server.repository;

import com.example.server.entity.EngineeringStaff;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EngineeringStaffRepository extends CrudRepository<EngineeringStaff, Integer> {
    List<EngineeringStaff> findAll();
}

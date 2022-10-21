package com.example.server.repository;

import com.example.server.entity.Laboratory;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LaboratoryRepository extends CrudRepository<Laboratory, Integer> {
    List<Laboratory> findAll();
}

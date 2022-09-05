package com.example.server.repository;

import com.example.server.entity.Area;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface AreaRepository extends CrudRepository<Area, Integer> {
    Set<Area> findAllByShopId(Integer id);
}

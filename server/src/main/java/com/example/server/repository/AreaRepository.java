package com.example.server.repository;

import com.example.server.entity.Area;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AreaRepository extends CrudRepository<Area, Integer> {
    List<Area> queryFindAllByShopId(Integer id);
}

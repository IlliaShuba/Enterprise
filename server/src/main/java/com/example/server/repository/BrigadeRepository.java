package com.example.server.repository;

import com.example.server.entity.Brigade;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Set;

public interface BrigadeRepository extends CrudRepository<Brigade, Integer>{
    List<Brigade> queryFindAllByAreaId(Integer id);
    List<Brigade> findAll();
}

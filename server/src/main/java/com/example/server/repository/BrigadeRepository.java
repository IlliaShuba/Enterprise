package com.example.server.repository;

import com.example.server.entity.Brigade;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface BrigadeRepository extends CrudRepository<Brigade, Integer>{
    Set<Brigade> queryFindAllByAreaId(Integer id);
}

package com.example.server.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.server.entity.Ware;

public interface WareRepository extends CrudRepository<Ware, Integer>{
}

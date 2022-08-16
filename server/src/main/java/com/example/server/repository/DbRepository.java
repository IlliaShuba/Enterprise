package com.example.server.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.server.models.Db;

public interface DbRepository extends CrudRepository<Db, Integer> {

}

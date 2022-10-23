package com.example.server.repository;

import com.example.server.entity.Worker;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Set;

public interface WorkerRepository extends CrudRepository<Worker, Integer> {
    List<Worker> findAll();
}

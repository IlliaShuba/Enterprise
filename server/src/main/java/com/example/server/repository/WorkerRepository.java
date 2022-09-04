package com.example.server.repository;

import com.example.server.entity.Worker;
import org.springframework.data.repository.CrudRepository;

public interface WorkerRepository extends CrudRepository<Worker, Integer> {
}

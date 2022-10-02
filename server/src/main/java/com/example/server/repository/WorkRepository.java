package com.example.server.repository;

import com.example.server.entity.Ware.Work;
import org.springframework.data.repository.CrudRepository;

public interface WorkRepository extends CrudRepository<Work, Integer> {
}

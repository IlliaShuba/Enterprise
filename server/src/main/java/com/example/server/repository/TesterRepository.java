package com.example.server.repository;

import com.example.server.entity.Tester;
import org.springframework.data.repository.CrudRepository;

public interface TesterRepository extends CrudRepository<Tester, Integer> {
}

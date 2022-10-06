package com.example.server.repository.WareRepo;

import com.example.server.entity.Ware.Glider;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;

public interface GliderRepository extends CrudRepository<Glider, Integer>{
    List<Glider> queryFirstByFinishCreateAfterAndFinishCreateBefore(LocalDate firstDate, LocalDate secondDate);
}

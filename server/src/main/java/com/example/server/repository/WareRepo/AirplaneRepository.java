package com.example.server.repository.WareRepo;

import com.example.server.entity.Ware.Airplane;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface AirplaneRepository extends CrudRepository<Airplane, Integer>{
    List<Airplane> queryFirstByFinishTestAfterAndFinishTestBefore(LocalDateTime firstDate,LocalDateTime secondDate);
}

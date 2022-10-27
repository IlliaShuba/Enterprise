package com.example.server.repository.WareRepo;

import com.example.server.entity.Ware.Airplane;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;

public interface AirplaneRepository extends CrudRepository<Airplane, Integer>{
    List<Airplane> queryFirstByFinishCreateAfterAndFinishCreateBefore(LocalDate firstDate, LocalDate secondDate);
    List<Airplane> queryAirplanesByShop_Id(Integer id);
    List<Airplane> queryAirplanesByLaboratory_Id(Integer id);
}

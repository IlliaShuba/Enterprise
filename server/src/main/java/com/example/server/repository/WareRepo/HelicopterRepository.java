package com.example.server.repository.WareRepo;

import com.example.server.entity.Ware.Airplane;
import com.example.server.entity.Ware.Helicopter;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;

public interface HelicopterRepository extends CrudRepository<Helicopter, Integer>{
    List<Helicopter> queryFirstByFinishCreateAfterAndFinishCreateBefore(LocalDate firstDate, LocalDate secondDate);
    List<Helicopter> queryHelicopterByShop_Id(Integer id);
    List<Helicopter> queryHelicopterByLaboratory_Id(Integer id);

}

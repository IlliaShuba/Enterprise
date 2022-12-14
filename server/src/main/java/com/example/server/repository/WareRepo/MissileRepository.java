package com.example.server.repository.WareRepo;

import com.example.server.entity.Ware.Airplane;
import com.example.server.entity.Ware.Missile;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;

public interface MissileRepository extends CrudRepository<Missile, Integer>{
    List<Missile> queryFirstByFinishCreateAfterAndFinishCreateBefore(LocalDate firstDate, LocalDate secondDate);
    List<Missile> queryMissileByShop_Id(Integer id);
    List<Missile> queryMissileByLaboratory_Id(Integer id);
}

package com.example.server.repository.WareRepo;

import com.example.server.entity.Ware.Airplane;
import com.example.server.entity.Ware.HangGlider;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;

public interface HangGliderRepository extends CrudRepository<HangGlider, Integer>{
    List<HangGlider> queryFirstByFinishCreateAfterAndFinishCreateBefore(LocalDate firstDate, LocalDate secondDate);
    List<HangGlider> queryHangGliderByShop_Id(Integer id);
    List<HangGlider> queryHangGliderByLaboratory_Id(Integer id);
}

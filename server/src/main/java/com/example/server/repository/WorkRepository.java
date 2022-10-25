package com.example.server.repository;

import com.example.server.entity.Ware.Work;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface WorkRepository extends CrudRepository<Work, Integer> {
    List<Work> queryWorksByAirplane_Id(Integer id);
    List<Work> queryWorksByGlider_Id(Integer id);
    List<Work> queryWorksByHangGlider_Id(Integer id);
    List<Work> queryWorksByHelicopter_Id(Integer id);
    List<Work> queryWorksByMissile_Id(Integer id);

    List<Work> queryAllByAirplaneNotNull();
    List<Work> queryAllByGliderNotNull();
    List<Work> queryAllByHangGliderNotNull();
    List<Work> queryAllByHelicopterNotNull();
    List<Work> queryAllByMissileNotNull();

}

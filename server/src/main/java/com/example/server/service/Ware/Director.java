package com.example.server.service.Ware;

import com.example.server.dto.AirplaneDto;
import com.example.server.entity.Ware.Airplane;
import com.example.server.repository.WareRepo.AirplaneRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class Director {
    @Autowired
    private AirplaneRepository airplaneRepository;
    AirplaneBuilder builder;

    public void setBuilder(AirplaneBuilder builder){ this.builder = builder;}

    public Airplane buildAirplane(){
        builder.create();
        builder.buildEngines();
        builder.buildWeight();
        builder.buildDescription();

        Airplane airplane = builder.getAirplane();

        return airplane;
    }
}

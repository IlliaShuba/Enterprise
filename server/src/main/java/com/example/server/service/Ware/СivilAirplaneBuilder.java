package com.example.server.service.Ware;

public class СivilAirplaneBuilder extends AirplaneBuilder{
    @Override
    void buildEngines() {
        airplane.setNumberOfEngines(2);
    }

    @Override
    void buildWeight() {
        airplane.setWeight(400);
    }

    @Override
    void buildDescription() {
        airplane.setDescription("Description for civil plane");
    }
}

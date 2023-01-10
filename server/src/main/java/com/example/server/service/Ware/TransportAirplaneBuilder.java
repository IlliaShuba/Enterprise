package com.example.server.service.Ware;

public class TransportAirplaneBuilder extends AirplaneBuilder{

    @Override
    void buildEngines() {
        airplane.setNumberOfEngines(4);
    }

    @Override
    void buildWeight() {
        airplane.setWeight(1000);
    }

    @Override
    void buildDescription() {
        airplane.setDescription("Description for transport plane");
    }
}

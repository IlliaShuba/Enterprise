package com.example.server.entity.Ware;

public class HelicopterDecorator implements Ware{
    Helicopter helicopter;

    public HelicopterDecorator(Helicopter helicopter) {
        this.helicopter = helicopter;
    }

    @Override
    public String showDescription() {
        return helicopter.showDescription();
    }
}

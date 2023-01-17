package com.example.server.entity.Ware;

public class CombatHelicopter extends HelicopterDecorator {

    public CombatHelicopter(Helicopter helicopter) {
        super(helicopter);
    }

    public String attack() {
        return " This type of helicopter can attack targets.";
    }

    @Override
    public String showDescription() {
        return super.showDescription() + attack();
    }
}

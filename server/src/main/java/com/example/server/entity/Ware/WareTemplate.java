package com.example.server.entity.Ware;

public abstract class WareTemplate {

    public void showInfo(){
        System.out.println("Ware Info: \n");
        showWareInfo();
        System.out.println("_______________");
    }

    public abstract void showWareInfo();

}

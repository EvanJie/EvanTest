package com.example.evan.evantestapplication;

/**
 * Created by Evan (JieYu.Wang) on 2018/4/8.
 */

class ElectricHeater implements Heater {
    boolean heating;

    public ElectricHeater() {
        System.out.println("di--->ElectricHeater");
    }

    @Override
    public void on() {

        System.out.println("~ ~ ~ heating ~ ~ ~");

        this.heating = true;

    }


    @Override
    public void off() {

        this.heating = false;

    }


    @Override
    public boolean isHot() {

        return heating;

    }
}

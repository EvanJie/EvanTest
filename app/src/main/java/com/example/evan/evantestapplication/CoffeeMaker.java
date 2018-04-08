package com.example.evan.evantestapplication;

import javax.inject.Inject;

import dagger.Lazy;

/**
 * Created by Evan (JieYu.Wang) on 2018/4/8.
 */

public class CoffeeMaker {
    private final Lazy<Heater> heater; // Create a possibly costly heater only when we use it.

    private final Pump pump;



    @Inject
    CoffeeMaker(Lazy<Heater> heater, Pump pump) {
        System.out.println("di--->CoffeeMaker");

        this.heater = heater;

        this.pump = pump;

    }



    public void brew() {
        System.out.println("di--->CoffeeMaker  brew");
        heater.get().on();

        pump.pump();

        System.out.println(" [_]P coffee! [_]P ");

        heater.get().off();

    }
}

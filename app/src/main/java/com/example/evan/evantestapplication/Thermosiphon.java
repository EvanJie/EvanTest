package com.example.evan.evantestapplication;

import javax.inject.Inject;

/**
 * Created by Evan (JieYu.Wang) on 2018/4/8.
 */

public class Thermosiphon implements Pump {
    private final Heater heater;


    @Inject
    Thermosiphon(Heater heater) {
        System.out.println("di--->Thermosiphon");
        this.heater = heater;
    }

    @Override
    public void pump() {
        System.out.println("~~~~Thermosiphon~~~");
    }
}

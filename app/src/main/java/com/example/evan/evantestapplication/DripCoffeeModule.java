package com.example.evan.evantestapplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Evan (JieYu.Wang) on 2018/4/8.
 */

@Module(includes = PumpModule.class)
class DripCoffeeModule {

    @Provides
    @Singleton
    Heater provideHeater() {

        return new ElectricHeater();

    }
}

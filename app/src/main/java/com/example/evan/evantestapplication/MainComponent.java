package com.example.evan.evantestapplication;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Evan (JieYu.Wang) on 2018/4/24.
 */
@Singleton
@Component(modules = {DripCoffeeModule.class, ShoeModule.class})
public interface MainComponent {
    void inject(MainActivity mainActivity);
}

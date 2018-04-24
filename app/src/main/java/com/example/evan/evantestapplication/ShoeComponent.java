package com.example.evan.evantestapplication;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Evan (JieYu.Wang) on 2018/4/24.
 */
@Singleton
@Component(modules = ShoeModule.class)
public interface ShoeComponent {
    void inject(MainActivity activity);

    void inject(SecondActivity activity);
}

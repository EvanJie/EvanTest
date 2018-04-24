package com.example.evan.evantestapplication;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Evan (JieYu.Wang) on 2018/4/24.
 */
@Component(modules = {ShoeModule.class})
public interface TestApplicationComponent {
    void inject(Application application);
}

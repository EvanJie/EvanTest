package com.example.evan.evantestapplication;

import android.app.Application;

/**
 * Created by Evan (JieYu.Wang) on 2018/4/24.
 */


public class TestApplication extends Application {
    private static ShoeComponent sShoeComponent;

    public static ShoeComponent getShoeComponent() {
        return sShoeComponent;
    }

    @Override

    public void onCreate() {
        super.onCreate();
        sShoeComponent = DaggerShoeComponent.create();
    }
}

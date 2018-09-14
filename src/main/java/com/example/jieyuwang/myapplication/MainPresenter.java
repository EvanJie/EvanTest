package com.example.jieyuwang.myapplication;


import android.util.Log;

/**
 * Created by Evan (JieYu.Wang) on 2018/9/4.
 */

public class MainPresenter implements IPresenter {
    @Override
    public void onStateChanged(android.arch.lifecycle.LifecycleOwner source, android.arch.lifecycle.Lifecycle.Event event) {
        Log.e(MainPresenter.class.getSimpleName(), event.name());
        Log.e(MainPresenter.class.getSimpleName(), source.getLifecycle().getCurrentState().name());
    }
}


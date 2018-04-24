package com.example.evan.evantestapplication;

import com.example.evan.evantestapplication.bean.Shoe;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Evan (JieYu.Wang) on 2018/4/24.
 */
@Module
public class ShoeModule {
    @Provides
    @Singleton
    Shoe providerShoe() {
        return new Shoe();
    }
}

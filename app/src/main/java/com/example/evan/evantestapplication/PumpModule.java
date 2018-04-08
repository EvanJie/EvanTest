package com.example.evan.evantestapplication;

import dagger.Binds;
import dagger.Module;

/**
 * Created by Evan (JieYu.Wang) on 2018/4/8.
 */

@Module
abstract class PumpModule {
    @Binds
    abstract Pump providePump(Thermosiphon pump);
}

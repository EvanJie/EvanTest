package com.example.evan.evantestapplication;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by Evan (JieYu.Wang) on 2018/4/24.
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface UserScope {

}

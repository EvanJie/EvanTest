package com.example.evan.evantestapplication;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Evan (JieYu.Wang) on 2018/4/8.
 */

public class CoffeeApp {
//    @Singleton
//    @Component(modules = { DripCoffeeModule.class })
    public interface CoffeeShop {

        CoffeeMaker maker();

    }



    public static void main(String[] args) {

//        CoffeeShop coffeeShop = DaggerCoffeeApp_CoffeeShop.builder().build();
//
//        coffeeShop.maker().brew();

    }
}

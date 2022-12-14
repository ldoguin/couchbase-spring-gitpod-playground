package com.couchbase.demo;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.jeasy.random.api.Randomizer;

public class MyCountryRandomizer implements Randomizer<String> {

    // private final List<String> countries = Flux.just(Locale.getAvailableLocales()).map(l -> l.getDisplayCountry())
    //         .collectList().block();

    private List<String> names = Arrays.asList("France", "Germany");

    @Override
    public String getRandomValue() {
        return names.get(new Random().nextInt(2));
    }

}

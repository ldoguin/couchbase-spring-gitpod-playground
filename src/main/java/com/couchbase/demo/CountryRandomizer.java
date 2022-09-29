package com.couchbase.demo;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import org.jeasy.random.api.Randomizer;

import reactor.core.publisher.Flux;

public class CountryRandomizer implements Randomizer<String> {

    public static final List<String> countries = Flux.just(Locale.getAvailableLocales()).map(l -> l.getDisplayCountry())
            .collectList().block();

    private List<String> names = Arrays.asList("France", "Germany");

    @Override
    public String getRandomValue() {
        return names.get(new Random().nextInt(2));
    }

}

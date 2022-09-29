package com.couchbase.demo;

import org.jeasy.random.EasyRandom;
import org.jeasy.random.api.Randomizer;

public class OldPeakRandomizer implements Randomizer<Double> {

    @Override
    public Double getRandomValue() {
        double random = 0 + Math.random() * (4);
        return Math.round(random * 100.0) / 100.0;
    }
    
}


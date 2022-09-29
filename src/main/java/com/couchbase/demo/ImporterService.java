package com.couchbase.demo;

import java.time.Duration;

import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;
import org.springframework.stereotype.Service;

import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.kv.MutationResult;

import reactor.core.publisher.Flux;

@Service
public class ImporterService {

    public void javaImporter(Cluster cluster) {
        EasyRandomParameters parameters = new EasyRandomParameters()
                .seed(123L)
                // .objectPoolSize(100)
                // .randomizationDepth(3)
                // .charset(forName("UTF-8"))
                // .timeRange(nine, five)
                // .dateRange(today, tomorrow)
                // .stringLengthRange(5, 50)
                // .collectionSizeRange(1, 10)
                // .scanClasspathForConcreteTypes(true)
                // .overrideDefaultInitialization(false)
                .ignoreRandomizationErrors(true);

        EasyRandom easyRandom = new EasyRandom(parameters);
        Flux<User> users = Flux.interval(Duration.ofMillis(10)).map( i -> easyRandom.nextObject(User.class));
        Flux<MutationResult> mutationtRestulFlux = users.map(user -> cluster.bucket("default").defaultCollection().upsert(user.getUserId(),user));
        mutationtRestulFlux.subscribe();
    }

}

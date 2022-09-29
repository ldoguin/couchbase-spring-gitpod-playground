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

    ClusterPropeties clusterProperties;
    Cluster cluster;

    public ImporterService(ClusterPropeties clusterProperties, Cluster cluster) {
        this.cluster = cluster;
        this.clusterProperties = clusterProperties;
    }

    public void javaImporter() {
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
        
        Flux<Patient> patients = Flux.interval(Duration.ofMillis(10)).map( i -> easyRandom.nextObject(Patient.class)).doOnNext(System.out::println);
        Flux<MutationResult> mutationtRestulFlux = patients.map(user -> cluster.bucket(clusterProperties.defaultBucket()).collection(clusterProperties.defaultCollection()).upsert(user.getUserId(),user));
        mutationtRestulFlux.subscribe();
    }

}

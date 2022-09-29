package com.couchbase.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.couchbase.client.java.Cluster;

@Component
public class SetupDataLoader implements
    ApplicationListener<ContextRefreshedEvent> {

  boolean alreadySetup = false;

  @Autowired
  Cluster cluster;

  @Autowired
  ImporterService importerService;

  @Override
  public void onApplicationEvent(ContextRefreshedEvent event) {

    if (alreadySetup)
      return;

    importerService.javaImporter(cluster);
    // Collection collection = cluster.bucket("default")
    // .defaultCollection();
    // User patient = new User("employee:1234", "firstName", "lastName", "email",
    // "password", "France", true, true);

    // collection.upsert(patient.getUserId(), patient);
    // JsonObject encrypted = collection.get(patient.getUserId())
    // .contentAsObject();
    // System.out.println(encrypted.get("encrypted$password"));

    // User readItBack = collection.get(patient.getUserId())
    // .contentAs(User.class);
    // System.out.println(readItBack.getPassword());

    alreadySetup = true;
  }

}
package com.couchbase.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class ReactorCouchbasePatternApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReactorCouchbasePatternApplication.class, args);
	}

}

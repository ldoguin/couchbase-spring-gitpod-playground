package com.couchbase.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class ReactorCouchbasePatternApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = SpringApplication.run(ReactorCouchbasePatternApplication.class, args);
		ctx.close();
	}

}

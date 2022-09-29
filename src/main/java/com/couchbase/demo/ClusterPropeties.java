package com.couchbase.demo;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "couchbase")
public record ClusterPropeties(String connectionString, String username, String password, String defaultBucket,String defaultCollection, String encryptionKey) {
}

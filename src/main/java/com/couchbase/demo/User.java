package com.couchbase.demo;

import java.util.Collection;

import org.springframework.data.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.data.couchbase.core.mapping.id.GeneratedValue;
import org.springframework.data.couchbase.core.mapping.id.GenerationStrategy;
import org.springframework.data.couchbase.core.query.N1qlJoin;

import com.couchbase.client.java.encryption.annotation.Encrypted;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private String userId;

    private String firstName;

    private String lastName;

    private String email;

    @Encrypted
    private String password;
    
    private String country;

    private boolean enabled;

    private boolean tokenExpired;
}
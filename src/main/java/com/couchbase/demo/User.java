package com.couchbase.demo;

import java.util.List;
import java.util.Locale;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import org.jeasy.random.annotation.Randomizer;
import org.jeasy.random.randomizers.CountryRandomizer;
import org.jeasy.random.randomizers.EmailRandomizer;
import org.jeasy.random.randomizers.FirstNameRandomizer;
import org.jeasy.random.randomizers.LastNameRandomizer;
import org.springframework.data.couchbase.core.mapping.Document;

import com.couchbase.client.java.encryption.annotation.Encrypted;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import reactor.core.publisher.Flux;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private String userId;

    @Randomizer(FirstNameRandomizer.class)
    private String firstName;

    @Randomizer(LastNameRandomizer.class)
    private String lastName;

    @Randomizer(EmailRandomizer.class)
    private String email;

    @Encrypted
    private String password;

    @Randomizer(CountryRandomizer.class)
    private String country;

    private boolean enabled;

    private boolean tokenExpired;
}
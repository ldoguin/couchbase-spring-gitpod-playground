package com.couchbase.demo;

import java.math.BigDecimal;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.jeasy.random.annotation.Randomizer;
import org.jeasy.random.annotation.RandomizerArgument;
import org.jeasy.random.randomizers.EmailRandomizer;
import org.jeasy.random.randomizers.FirstNameRandomizer;
import org.jeasy.random.randomizers.LastNameRandomizer;
import org.jeasy.random.randomizers.RegularExpressionRandomizer;
import org.springframework.data.couchbase.core.mapping.Document;

import com.couchbase.client.java.encryption.annotation.Encrypted;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Patient {

    private String userId;

    @Randomizer(FirstNameRandomizer.class)
    private String first_name;

    @Randomizer(LastNameRandomizer.class)
    private String last_name;

    @Randomizer(value = RegularExpressionRandomizer.class, args = @RandomizerArgument(value = "[0-9]{3}-[0-9]{2}-[0-9]{3}", type = String.class))
    private String security_number;

    @Randomizer(EmailRandomizer.class)
    private String email;

    @Min(value = 20, message = "Age should not be less than 20")
    @Max(value = 100, message = "Age should not be greater than 100")
    private int age;

    @Min(value = 0)
    @Max(value = 1)
    private int sex;

    @Min(value = 0)
    @Max(value = 2)
    private int cp;

    @Min(value = 120)
    @Max(value = 180)
    private int trestbps;

    @Min(value = 120)
    @Max(value = 400)
    private int chol;

    @Min(value = 0)
    @Max(value = 1)
    private int fbs;

    @Min(value = 0)
    @Max(value = 2)
    private int restecg;

    @Min(value = 95)
    @Max(value = 200)
    private int thalach;

    @Min(value = 1)
    @Max(value = 1)
    private int exang;

    @Randomizer(OldPeakRandomizer.class)
    private Double oldpeak;

    @Min(value = 0)
    @Max(value = 2)
    private int slope;

    @Min(value = 0)
    @Max(value = 4)
    private int ca;

    @Min(value = 1)
    @Max(value = 3)
    private int thal;

    @Min(value = 0)
    @Max(value = 1)
    private int target;

    @Encrypted
    private String password;

    @Randomizer(MyCountryRandomizer.class)
    private String country;

}
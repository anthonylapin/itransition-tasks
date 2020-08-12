package com.alapin;

import com.github.javafaker.Faker;

public class UserData {
    public static String generateName(Faker faker, String localeString) {
        String randomName = faker.name().nameWithMiddle();
        if(localeString.equals("ru")) {
            return randomName;
        }
        // belarusian/usa name format generated with english prefix
        return Helper.unprefixify(randomName);
    }

    public static String generateAddress(Faker faker, String localeString) {
        String randomAddress = faker.address().fullAddress();

        if(localeString.equals("en-US")) {
            return randomAddress;
        }

        // Post index filled with # for non en_US region
        return Helper.numerify(randomAddress);
    }

    public static String generatePhoneNumber(Faker faker, String localeString) {
        // Java.Faker generates US phone number incorrectly
        if(localeString.equals("en-US")) {
            return Helper.numerify("+1(###)###-####");
        }
        return faker.phoneNumber().phoneNumber();
    }

}

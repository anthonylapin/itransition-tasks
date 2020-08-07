package com.alapin;

import com.github.javafaker.Faker;

public class UserData {
    // generating random name
    public static String generateName(Faker faker, String localeString) {
        String randomName = faker.name().nameWithMiddle();
        if(localeString.equals("ru")) {
            return randomName;
        }
        // Java.Faker generates belarusian/usa name format with prefix, so delete prefix.
        return Helper.unprefixify(randomName);
    }

    // generating random address
    public static String generateAddress(Faker faker, String localeString) {
        String randomAddress = faker.address().fullAddress();

        // If region is en-US, Java Faker generates correct address
        if(localeString.equals("en-US")) {
            return randomAddress;
        }

        // if region is not en-US, fill # by digits in post index
        return Helper.numerify(randomAddress);
    }

    // generating random phone number
    public static String generatePhoneNumber(Faker faker, String localeString) {
        // Java.Faker generates US phone number incorrectly, so do it by Helper
        if(localeString.equals("en-US")) {
            return Helper.numerify("+1(###)###-####");
        }
        return faker.phoneNumber().phoneNumber();
    }

}

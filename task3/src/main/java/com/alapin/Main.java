package com.alapin;

import com.github.javafaker.Faker;
import java.io.File;
import java.net.URL;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.exit(1);
        }

        final String localeString = Helper.getLocaleString(args[0]);
        final int generatedRecords = Integer.parseInt(args[1]);
        final float errorAverage = Helper.getErrorAverage(args);

        if (localeString == null || generatedRecords < 1 || errorAverage < 0) {
            System.exit(1);
        }

        Faker faker = new Faker(new Locale(localeString));
        int errorAmount = (int) Math.floor(errorAverage * generatedRecords);

        if(errorAmount == 0) {
            Helper.printNoErrorData(generatedRecords, faker, localeString);
            return;
        }

        Main main = new Main();
        File alphabetFile = main.getAlphabetFromResources();

        String[] dataArr = Helper.generateData(generatedRecords, localeString, errorAmount,
                new Random(), faker, alphabetFile);
        Helper.printErrorData(dataArr);
    }

    private File getAlphabetFromResources() {
        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource("Alphabet.json");

        if (resource == null) {
            throw new IllegalArgumentException("file is not found!");
        } else {
            return new File(resource.getFile());
        }
    }
}


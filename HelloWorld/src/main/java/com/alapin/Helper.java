package com.alapin;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;
import java.lang.StringBuilder;
import java.util.Iterator;
import com.github.javafaker.Faker;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

public class Helper {
    public static String numerify(String randomString) {
        Random rand = new Random();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < randomString.length(); i++) {
            if (randomString.charAt(i) == '#') {
                sb.append(rand.nextInt(10));
            } else {
                sb.append(randomString.charAt(i));
            }
        }
        return sb.toString();
    }

    public static String unprefixify(String str) {
        final String[] prefixArray = {"Mr. ", "Mrs. ", "Ms. ", "Miss ", "Dr. "};

        for (String s : prefixArray) {
            if (str.contains(s)) {
                str = str.replace(s, "");
                break;
            }
        }
        return str;
    }

    public static String getLocaleString(String arg) {
        switch (arg) {
            case "be_BY":
                return "by";
            case "ru_RU":
                return "ru";
            case "en_US":
                return "en-US";
            default:
                return null;
        }
    }

    public static float getErrorAverage(String[] args) {
        if (args.length == 3) {
            return Float.parseFloat(args[2]);
        } else {
            return 0;
        }
    }

    public static ArrayList<String> getAlphabet(String language, File alphabetFile) {
        ArrayList<String> letterArray = new ArrayList<>();
        try {
            String content = new String(Files.readAllBytes(alphabetFile.toPath()));
            Object obj = new JSONParser().parse(content);
            JSONObject jo = (JSONObject) obj;
            JSONArray ja = (JSONArray) jo.get(language);

            Iterator itr = ja.iterator();
            while (itr.hasNext()) {
                letterArray.add(itr.next().toString());
            }
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }

        return letterArray;
    }

    public static void printNoErrorData(int generatedRecords, Faker faker, String localeString) {
        for (int i = 0; i < generatedRecords; i++) {
            String str = UserData.generateName(faker, localeString) + "; "
                    + UserData.generateAddress(faker, localeString) + "; "
                    + UserData.generatePhoneNumber(faker, localeString);
            System.out.println(str);
        }
    }

    public static String[] generateData(int generatedRecords, String localeString,
                                        int errorAmount, Random rand, Faker faker,
                                        File alphabetFile) {
        ArrayList<String> alphabet = Helper.getAlphabet(localeString, alphabetFile);
        String[] dataArr = new String[generatedRecords];

        for(int i = 0; i < generatedRecords; i++) {
            dataArr[i] = UserData.generateName(faker, localeString) + "; "
                    + UserData.generateAddress(faker, localeString) + "; "
                    + UserData.generatePhoneNumber(faker, localeString);

            if(errorAmount != 0 && rand.nextBoolean()) {
                dataArr[i] = Error.makeError(dataArr[i], rand, alphabet);
                errorAmount--;
            }
        }

        if(errorAmount != 0) {
            dataArr = makeMoreErrors(dataArr, generatedRecords, errorAmount, rand, alphabet);
        }

        return dataArr;
    }

    private static String[] makeMoreErrors(String[] dataArr, int generatedRecords,
                                           int errorAmount, Random rand,
                                           ArrayList<String> alphabet) {
        while(errorAmount != 0) {
            int randomInt = rand.nextInt(generatedRecords);
            dataArr[randomInt] = Error.makeError(dataArr[randomInt], rand, alphabet);
            errorAmount--;
        }
        return dataArr;
    }

}

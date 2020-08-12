package com.alapin;

import java.util.Random;
import java.util.ArrayList;

public class Error {
    public static String makeError(String str, Random rand, ArrayList<String> alphabet) {
        switch(rand.nextInt(3)) {
            case 0:
                return removeRandomSymbol(str, rand, alphabet);
            case 1:
                return swapSymbols(str, rand, alphabet);
            default:
                return addRandomSymbol(str, rand, alphabet);
        }
    }

    private static String removeRandomSymbol(String str, Random rand, ArrayList<String> alphabet) {
        if(str.isEmpty()) {
            return addRandomSymbol(str, rand, alphabet);
        }

        int index = rand.nextInt(str.length());
        while(str.indexOf(';') == str.charAt(index)) {
            index = rand.nextInt(str.length());
        }

        return str.substring(0, index) + str.substring(index + 1);
    }

    private static String addRandomSymbol(String s, Random rand, ArrayList<String> alphabet) {
        String toInsert;
        if(rand.nextBoolean()) {
            toInsert = alphabet.get(rand.nextInt(alphabet.size()));
        } else {
            toInsert = Integer.toString(rand.nextInt(10));
        }

        StringBuffer str = new StringBuffer((s));

        if(s.isEmpty()) {
            s = String.valueOf(toInsert.charAt(0));
            return s;
        }

        str.insert(rand.nextInt(str.length()), toInsert.charAt(0));
        return str.toString();
    }

    private static String swapSymbols(String str, Random rand, ArrayList<String> alphabet) {
        if(str.isEmpty() || str.length() == 1) {
            return addRandomSymbol(str, rand, alphabet);
        }

        char[] ch = str.toCharArray();
        int index1 = rand.nextInt(ch.length);
        int index2;
        if(index1 == 0) {
            index2 = index1 + 1;
        } else if(index1 == str.length() - 1) {
            index2 = index1 - 1;
        } else {
            index2 = rand.nextBoolean() ? index1 + 1 : index1 - 1;
        }

        char temp = ch[index1];
        ch[index1] = ch[index2];
        ch[index2] = temp;
        return new String(ch);
    }
}

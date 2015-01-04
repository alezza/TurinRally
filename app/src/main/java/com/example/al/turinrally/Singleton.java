package com.example.al.turinrally;

import java.util.Random;

/**
 * Created by AL on 12/29/2014.
 */
public enum Singleton {
    INSTANCE;
    String currentAlphabet="FIAT";

    public char getRandomLetter() {
        char letterToReturn = 'A';
        final String alphabet = "FIAT";
        final int N = alphabet.length();

        int i;
        Random r = new Random();
        for (i = 0; i < 4; i++) {
            letterToReturn = alphabet.charAt(r.nextInt(4));
        }
        currentAlphabet = currentAlphabet.replaceAll("letterToReturn","");
        return letterToReturn;
    }

    /*
    private static String removeChar(String s, char c) {
        StringBuffer buf = new StringBuffer(s.length());
        buf.setLength(s.length());
        int current = 0;
        for (int i=0; i<s.length(); i++){
            char cur = s.charAt(i);
            if(cur != c) buf.setCharAt(current++, cur);
        }
        return buf.toString();
    }
    */

}

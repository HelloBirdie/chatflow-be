package com.hellobirdie.chatflow.utils;


public class StringUtils {

    public static String generateRandomPassword(int length) {
        String password = "";
        String[] letters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".split("");
        String[] nonLetters = "0123456789#!\"$%&'()*+,-./:;<=>?@[]^_`{|}~".split("");
        for (int i = 0; i < 8; i++) {
            password += letters[(int) (Math.random() * letters.length)];
        }
        for (int i = 0; i < length - 8; i++) {
            password += nonLetters[(int) (Math.random() * nonLetters.length)];
        }
        return password;

    }
}

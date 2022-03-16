package com.naresh.a_javabasics;

public class H_CharacterClass {
    public static void main(String[] args) {
        char c = ' ';//only one space is allowed
//        char c=''; //empty char is not a char
        System.out.println(Character.isWhitespace(c));//true
        System.out.println(Character.charCount(10));//1 //todo ?
        System.out.println(Character.isDigit('1'));//true
        System.out.println(Character.toLowerCase('a'));//true

        //many other methods there
    }
}

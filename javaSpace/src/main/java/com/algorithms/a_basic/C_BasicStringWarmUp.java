package com.algorithms.a_basic;

import java.util.Stack;
import java.util.regex.Pattern;

public class C_BasicStringWarmUp {
    public static void main(String[] args) {
        System.out.println("Check the given string is having vowels");
        String input = "Naresh is good boy";
//        System.out.println("Has vowels:"+input.toLowerCase().contains(".*[aeiou].*")); // wrong- contails only check for specific string
        System.out.println("Has vowels:" + input.toLowerCase().matches(".*[aeiou].*"));//This method internally  use Pattern as below
        System.out.println("Has vowels:" + Pattern.matches(".*[aeiou].*", input));

        System.out.println("Check the given string is polindrome are not");
        input = "madam";
        //way1
        StringBuffer buffer = new StringBuffer(input);
        if (buffer.reverse().toString().equals(input))
            System.out.println("Yes it is polindrome");
        //way2
        boolean result = true;
        int length = input.length() - 1;
        for (int i = 0; i < input.length() / 2; i++) {
//            if (input.charAt(i) != input.charAt(length--)) {
            if (input.charAt(i) != input.charAt(length - i)) {
                result = false;
                break;
            }
        }
        System.out.println("way 2polindrome:" + result);

        System.out.println("How to remove Whitespaces from String");
        //Character.isWhiteSpace(char), convert string to char[]>iterate>filter> append result to StringBuffer

        System.out.println("remove white spaces and starting and ending");
        input.trim();
        input.strip();//from java11 - recommended because it is unicode aware
        input.stripTrailing();//at the end
        input.stripLeading();


        System.out.println("Reverse given string using stack");
        input="naresh";
        char[] chars = input.toCharArray();
        Stack stack= new Stack(); //we cant define Stack<char> because char is primitive
        for(char c:chars){
            stack.push(c);
        }
        while(!stack.empty()){
            System.out.print(stack.pop());
        }


    }
}

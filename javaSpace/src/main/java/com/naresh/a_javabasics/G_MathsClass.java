package com.naresh.a_javabasics;

import java.security.SecureRandom;

public class G_MathsClass {
    public static void main(String[] args) {
        double random = Math.random();//0.0 to 1.0//sonar issue
        SecureRandom secureRandom = new SecureRandom();//to resolve sonar issue use this
        secureRandom.nextInt();

        System.out.println(Math.pow(2, 3));//8
        System.out.println(Math.round(25.3f));//25
        System.out.println(Math.abs(-25.3));//25.3
        System.out.println(Math.max(10, 20));//20
        System.out.println(Math.log(10));//2.3..
        //we have few other methods
    }
}

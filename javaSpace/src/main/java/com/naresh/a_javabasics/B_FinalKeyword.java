package com.naresh.a_javabasics;

/*
final class - cant extend
final method- cant overide
final variable - cant change
 */
public class B_FinalKeyword {
    private final int X = 10;
    private final int y;// can be initialized in constructor or initialization block
    private static final int z;// can be initialized in static blocks
    private final int a;//it must be initialized in all constructors

    static { //called at class loading
        z = 25;
        System.out.println("static block");
    }

    //Initializer block contains the code that is always executed whenever an instance is created.
    // It is used to declare/initialize the common part of various constructors of a class
    {//calling while obj creation before constructor is called
        y = 10;
        System.out.println("initialization block");
    }

    B_FinalKeyword() {
        a = 0;
    }

    public B_FinalKeyword(int a) {
        this.a = a;
        System.out.println("constructor block");
    }

    public static void main(String[] args) {
        final int b = 25;
        B_FinalKeyword obj = new B_FinalKeyword(5);
    }
}

package com.naresh.generics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class E_Inheritance {
    public static void main(String[] args) {

        SubGeneric<Integer> generic = new SubGeneric<>();
        generic.print(10);
    }
}

class Generic<T> {
    public Generic() {
        System.out.println("generic constructor");
    }

    public void print(T a) {
        System.out.println("parent" + a);
    }
}

class SubGeneric<P> extends Generic<P> {
    public SubGeneric() {
        super();
        System.out.println("SubGen Constructor");
    }

    @Override
    public void print(P a) {//TODO here P is from class
        System.out.println("child" + a);
    } // This method is overriding

//    public <P> void print(P a) {} // This method is not overriding because we declared a new type <P>

    public <P> void print2(P a) {
    }//here P is from local

    //    public static  void print3(P a) {}// static methods can't access class P

    public static <P> void print3(P a) {
    }

    public <T> List<T> genericMethod(T[] a) {
        return Arrays.stream(a).collect(Collectors.toList());
    }

    public <T extends List> void print4(T a){
        List ist=a;
        ArrayList b=(ArrayList) a;


    }


    public void test() {
//        print(10); //we can't pass int because P is from class level
//        print(Integer.valueOf(10));

        SubGeneric subGeneric = new SubGeneric();
        subGeneric.print(20);

        //
        genericMethod(new Integer[5]);

        //
        print2(10);// here we can pass int because P is local
    }
}

package com.venkat.java8;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class C_MethodReferences {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);

        list.stream()
//                .map(e->String.valueOf(e))
                .map(String::valueOf) //Static method reference, method is taking parameter
                .collect(Collectors.toList());

        list.stream()
//                .forEach(e->System.out.println(e))
                .forEach(System.out::println); // method reference to instance methods

        list.stream()
                .map(e -> e.toString())
                .map(String::toString) // parameter becomes a target,
                .collect(Collectors.toList());

        list.stream()
//                .map(e->new Double(e))
                .map(Double::new)//method reference to constructor, that take a parameter, TODO what if constructor doesn't take any params?
                .collect(Collectors.toList());

        //TODO methods taking two params

        list.stream()
//                .reduce(0, (total, e)->Integer.sum(total, e))
                .reduce(0, Integer::sum);// parameter should be in same order here both are params

        list.stream()
                .map(String::valueOf)
//                .reduce("", (carry, str)-> carry.concat(str))
                .reduce("", String::concat); // here one parameter is target other one is parameter

        //TODO Limitations
        /*
        - When compiler find one instance method and one static method then we can't use method reference
        - When there is manipulation of data //TODO why?
         */
    }
}

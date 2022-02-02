package com.venkat.java8.stream;

import java.util.List;
import java.util.stream.Stream;
import java.util.stream.Collectors;

public class E_StreamIterationMethods {
    public static void main(String[] args) {
        List<String> strList = List.of("naresh", "Lalitha", "Chareesh", "Tinku", "naresh");
        List<String> intList = List.of("10", "20", "30", "40");
        List<Integer> integerList = List.of(60, 10, 20, 30, 40, 50);
        List<Integer> integerList2 = List.of(10, 20, 30, 40, 50, 10, 20, 30, 40, 50, 10, 20, 30, 40, 50);

        List<Integer> collect1 = integerList2.stream()
                .limit(5) //allow only first 5 elements
                .peek(System.out::println)// print values and pass the stream as it is - Stream<T> peek(Consumer)
                .skip(5)/// discard first 5 elements
                .collect(Collectors.toList());
        System.out.println("collect1:" + collect1);// empty collection

        //from java 9
        List<Integer> collect2 = integerList2.stream()
                .takeWhile(e -> e < 40)// take all the elements untill the condition is true, once the condition is false then it will not take any elements
                .collect(Collectors.toList());
        System.out.println("collect2:" + collect2);//10,20,30

        List<Integer> collect3 = integerList2.stream()
                .dropWhile(e -> e < 40)// drop all the elements untill the condition is true, once the condition is false then take all the elements
                .collect(Collectors.toList());
        System.out.println("collect3:" + collect3);
    }
}

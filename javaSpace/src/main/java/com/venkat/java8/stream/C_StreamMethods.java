package com.venkat.java8.stream;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class C_StreamMethods {
    public static void main(String[] args) {
        List<String> strList = List.of("naresh", "Lalitha", "Chareesh", "Tinku", "naresh");
        List<String> intList = List.of("10", "20", "30", "40");
        List<Integer> integerList = List.of(60,10, 20, 30, 40, 50);
        List<Integer> integerList2 = List.of(10, 20, 30, 40, 50, 10, 20, 30, 40, 50, 10, 20, 30, 40, 50);

        //collectingAndThen
        String collect = strList.stream()
                .collect(Collectors.collectingAndThen( //params - downstream(collector), finisher(function)
                        Collectors.joining(), e -> "*" + e + "*"//whatever finisher is returning that is the result
                ));
        System.out.println("collectAndThen:" + collect);
        Map<String, String> collect1 = strList.stream()
                .collect(Collectors.groupingBy(Function.identity(),
                        Collectors.collectingAndThen(
                                Collectors.joining(), e -> "*" + e + "*"
                        )));


        //mapping
        List<String> collect2 = strList.stream()
                .collect(Collectors.mapping(s -> s + s, Collectors.toList()));//params - Function, Collectors(downstream)
        List<String> collect3 = strList.stream()
                .collect(Collectors.flatMapping(s -> Stream.of(s), Collectors.toList()));


    }
}

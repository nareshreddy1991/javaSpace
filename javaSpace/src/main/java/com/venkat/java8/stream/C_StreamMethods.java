package com.venkat.java8.stream;

import java.util.*;
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

        //matches
        Optional<String> any = strList.stream().findAny();
        Optional<String> first = strList.stream().findFirst();
        boolean b = strList.stream().allMatch(String::isEmpty);
        boolean b1 = strList.stream().anyMatch(String::isBlank);
        boolean b2 = strList.stream().noneMatch(String::isEmpty);

        //toArray
        Object[] objects = strList.stream().toArray();
        String[] strings = strList.stream().toArray(String[]::new); //TODO

        long count = strList.stream().count();
        Stream<String> distinct = Arrays.stream(strings).distinct();

        Stream<String> stream_is_closed = strList.stream()
                .onClose(() -> System.out.println("Stream is closed"));//called when stream is closed, taking Runnable(dont take, dont return)
        stream_is_closed.close();


    }
}

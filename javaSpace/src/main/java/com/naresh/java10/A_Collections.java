package com.naresh.java10;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
/*
Since java10 -- oracle decides to release open jdk relase every 6 months
and Oracle long term support(LTS) release every 3 years, last LTS version is java 8

oracle jdk/Open jdk
 */
public class A_Collections {
    public static void main(String[] args) {
        //TODO copyOf -- returns unmodifiable collections
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(1);
//        list.add(null);
        List<Integer> integers = List.copyOf(list);//return unmodifiable collection, throws NPE if any element is null
        System.out.println("Java 10 list:" + integers);
        Set<Integer> integers1 = Set.copyOf(list);
        System.out.println("Java 10 set:" + integers1);

        Map<Integer, Integer> map = Map.of(1, 2, 3, 4);
        Map<Integer, Integer> integerIntegerMap = Map.copyOf(map);//either key or value shouldn't be null its throws NPE

        //TODO Collectors.toUnmodifiableList()
        List<Integer> list1 = List.of(1, 2, 3, 4, 5, 6);
        list.stream()
//                .collect(Collectors.toUnmodifiableList());
//                .collect(Collectors.toUnmodifiableSet());
                .collect(Collectors.toUnmodifiableMap(Function.identity(), Function.identity()));
    }
}

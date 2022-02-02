package com.venkat.java8.stream;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class A_StreamBasics {
    public static void main(String[] args) {
        List<String> strList = List.of("naresh", "Lalitha", "Chareesh", "Tinku", "naresh");
        List<String> intList = List.of("10", "20", "30", "40");

        //different ways to collect to Collection
        strList.stream()
                .filter(e -> !e.isEmpty())
                .map(String::toUpperCase)
//                .collect(Collectors.toList())
//                .collect(Collectors.toSet())
//                .collect(Collectors.toUnmodifiableList())
//                .collect(Collectors.toUnmodifiableSet())
                .collect(Collectors.toCollection(ArrayDeque::new));//()->new ArrayDeque (supplier)

        //different ways to collect to Map(one to one)
        Map<String, String> map = strList.stream()// TODO what if key is null?
//                .collect(Collectors.toMap(Function.identity(), Function.identity()));//it fails if there is any duplicate keys
//                .collect(Collectors.toMap(Function.identity(),Function.identity(),(a,b)->a));//if duplicates it replace the entry
                .collect(Collectors.toMap(Function.identity(), Function.identity(), (a, b) -> a, TreeMap::new));//collecting to specified map
        System.out.println(map);

        //different ways to group(one to many)
        Map<String, List<String>> group1 = strList.stream()
                .collect(Collectors.groupingBy(Function.identity()));
        System.out.println("group1:" + group1);
//        Map<String, Set<String>> group2 = strList.stream()
//                .collect(Collectors.groupingBy(Function.identity(), Collectors.toSet())); //TODO error is coming?
//        TreeMap<String, Set<String>> group3 = strList.stream()
//                .collect(Collectors.groupingBy(Function.identity(), TreeMap::new, Collectors.toSet()));//TODO sometimes working sometimes compilation error
//        Map<String, String> group4 = strList.stream()
//                .collect(Collectors.groupingBy(Function.identity(), Collectors.reducing("*", (a, b) -> a + b))); //TODo failing
//        System.out.println("group4:"+group4);//group4:{Tinku=*Tinku, Lalitha=*Lalitha, Chareesh=*Chareesh, naresh=*nareshnaresh}
       /* Map<String, Integer> group5 = strList.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.collectingAndThen(
                        Collectors.toList(), e -> e.size()
                )));
        Map<String, Integer> group6 = strList.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.collectingAndThen(
                        Collectors.reducing("**", (a, b) -> a + b), str -> str.length()
                )));*/


        //joining
        String collect = strList.stream()
//                .collect(Collectors.joining());//nareshLalithaChareeshTinku
//                .collect(Collectors.joining("-"));//naresh-Lalitha-Chareesh-Tinku
//                .collect(Collectors.joining("-","\"","\"")); //"naresh-Lalitha-Chareesh-Tinku"
                .collect(Collectors.joining("\"-\"", "\"", "\""));//"naresh"-"Lalitha"-"Chareesh"-"Tinku"
        System.out.println(collect);

        //
       /* int a=(int)"10"; //compilation error
        long l= 10;  //valid
        int b= (int)20l; //valid
        */
        //converting between the different streams Stream<String> -> IntStrem -> DoubleStrem -> Stream<String>
        intList.stream()
                //string cant be cast to int so we need to pass function -ToIntFunction
                .mapToInt(Integer::valueOf)
                .mapToLong(e -> e)
                .mapToDouble(e -> e)
                .mapToLong(e -> (long) e)
                .mapToInt(e -> (int) e)
                //from IntStream we can convert to LongStrem & DoubleStream
                //from LongStream we can convert to DoubleStream
                .asDoubleStream()
                .mapToInt(e -> (int) e)
                .asLongStream()
                .mapToObj(String::valueOf)
                .mapToInt(Integer::valueOf);

        //number operations - sum, avg, min, max , count
        IntSummaryStatistics intSummary = intList.stream()
                .mapToInt(Integer::valueOf)
                .summaryStatistics();
        System.out.println("int Summary:" + intSummary.getSum() + intSummary.getAverage() + intSummary.getMax() + intSummary.getMax() + intSummary.getCount());

        LongSummaryStatistics longSummaryStatistics = intList.stream()
                .mapToLong(Long::valueOf)
                .summaryStatistics();

        DoubleSummaryStatistics doubleSummaryStatistics = intList.stream()
                .mapToDouble(Double::valueOf)
                .summaryStatistics();

        intList.stream()
//                .mapToInt(Integer::valueOf)
                .mapToLong(Long::valueOf)
                .mapToDouble(Double::valueOf)
//                .sum();  // these methods exists in int long double streams
//                .max()
//                .min()
//                .count()
                .average();
    }

}

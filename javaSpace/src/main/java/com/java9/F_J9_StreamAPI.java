package com.java9;

import java.util.List;
import java.util.stream.Stream;

/*
The takewhile() method of Stream API accepts all values until predicate returns false whereas dropWhile() method of Stream API drops all values until it matches
the predicate. If a stream is ordered, the takewhile() method returns a stream consisting of the longest prefix of elements taken from this stream that matches
predicate whereas the dropWhile() method returns the remaining stream after matching predicate. If the stream is unordered, the takewhile() method returns a stream
consisting of a subset of elements extracted from a stream that matches the given predicate whereas the dropWhile() method returns a stream consisting of the remaining
elements of a stream after dropping a subset of elements that matches the given predicate.
 */
public class F_J9_StreamAPI {
    public static void main(String[] args) {
        Stream.of("India", "Australia", "Newzealand", "", "South Africa", "England").takeWhile(o -> !o.isEmpty()).forEach(System.out::print);//output:IndiaAustraliaNewzealand
        System.out.println();
        Stream.of("India", "Australia", "Newzealand", "", "England", "Srilanka").dropWhile(o -> !o.isEmpty())//TODO drop when condition is true untill predicate return false
                .forEach(System.out::print);//EnglandSrilanka
        System.out.println();
        Stream.of("India", "", "Australia", "", "England", "Srilanka").dropWhile(o -> !o.isEmpty())//it will get all non empty string after first false condition
                .forEach(System.out::print);//AustraliaEnglandSrilanka
        System.out.println();
        List<Integer> numbers = List.of(4, 1, 6, 2, 3, 4, 5, 6, 7, 8, 9);
        //TODO takeWhile ordered data
        numbers.stream().sorted()
//                .filter(e -> e != 6) // it will check and filter all the elements
                .takeWhile(e -> e < 7)// since its a while, once condition false then it terminates the flow
                .forEach(System.out::print);

        System.out.println("\nDropwhile");
        numbers.stream().sorted().dropWhile(e -> e < 5)// drops till 4, get remaining values
                .forEach(System.out::print);
        System.out.println("\nunordered");
//TODO takeWhile unordered data
                /*
        In the case of the unordered stream, some elements of this stream match the given predicate and behaviour of this operation becomes nondeterministic;
         so this method is free to take any subset of matching elements.
         */
        numbers.stream().takeWhile(e -> e < 7)// working same as ordered
                .forEach(System.out::print);

        System.out.println("\nDropwhile");
        numbers.stream().dropWhile(e -> e < 5)//getting undeterministic results 623456789
                .forEach(System.out::print);

        //TODO iterate overloaded method
        Stream.iterate(0, i -> i < 10, i -> i + 1)
                .forEach(System.out::println);
        //TODO ofNullable
//        Stream.of(null);// throws NPE
        Stream.ofNullable(null)
                .forEach(System.out::println);//since stream is empty it will not print anything
    }
}

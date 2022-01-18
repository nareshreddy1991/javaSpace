package com.venkat.java8;

import sun.rmi.transport.StreamRemoteCall;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class D_FunctionComposition {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        //problem, double even numbers & sum

        //Declarative style - we need to go up and down to understand it
        int sum = 0;
        for (Integer num : numbers) {
            if (num % 2 == 0) {
                sum += num * 2;
            }
        }

        //Imperative style - THis is called function composition, its a single pass through
        numbers.stream().filter(e -> e % 2 == 0)// it takes Predicate
//                .map(e -> e * 2) // it takes Function<T ,R>
//                .reduce(0, Integer::sum);// its takes T, BinaryOperator<T>
                .mapToInt(e -> e * 2).sum();// sum is reduce operation, reduce converts collection of values to single value
        /*
                    filter      map         reduce(0.0 initial)
        x1             x1       x1*2          0.0+ x1*2 = 25 ( one element go to the complete pipeline then next element)
        x2             -        x2*2          25+ x2*2 = 75 etc
         */

        List<Integer> doubleofEven = new ArrayList<>();
        numbers.stream().forEach(e -> doubleofEven.add(e * 2));// don't do this, shared mutability is devils work, use collectors

        /*
        Streams are lazy , it will be evaluated only after calling the terminal operation(sum, collect etc)
        one element will go through the complete pipeline then second element will go etc...
         */

        Stream<Integer> integerStream = numbers.stream().filter(e -> e > 2);
        integerStream.collect(Collectors.toList());
        integerStream.collect(Collectors.toList());//IllegalStateException stream can't be reused

        // characterstics of streams sized, sorted,
        //un sized stream
        Stream.iterate(0, e -> e + 1); //0 1 2 ...infinite but this will not be evaluated untill terminal operation
        //infinite stream can't exits without lazyness , lazyness can't exits with out no side effects, no side effects can't exists with immutability
        Stream.iterate(0, e -> e + 1)
                .filter(e -> e % 2 == 0)  // unbounded, lazy
                .mapToInt(e -> e * 2) // unbounded , lazy
                .limit(10)// restrics to n // bounded Lazy once 10 element passes through then it will stop the process
                .sum();  // terminal operation

    }
}

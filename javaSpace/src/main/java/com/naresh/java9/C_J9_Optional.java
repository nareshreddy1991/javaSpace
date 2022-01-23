package com.naresh.java9;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class C_J9_Optional {
    public static void main(String[] args) {
        /*TODO or
        Sometimes, when our Optional is empty, we want to execute some other action that also returns an Optional.
Prior Java 9 the Optional class had only the orElse() and orElseGet() methods but both need to return unwrapped values.
Java 9 introduces the or() method that returns another Optional lazily if our Optional is empty. If our first Optional has a defined value,
the lambda passed to the or() method will not be invoked, and value will not be calculated and returned:
         */

        String val = null;
        Optional<String> optional = Optional.ofNullable(val);
        //orElse(), orElseGet() returns unWrapped value, just string only in the below case
        Optional<String> optional1 = optional.or(() -> Optional.of("Naresh")); //when optional if empty return some other optional
        System.out.println("or value:" + optional1.get());
        /*
        TODO ifPresentOrElse method
        When we have an Optional instance, often we want to execute a specific action on the underlying value of it.
         On the other hand, if the Optional is empty we want to log it or track that fact by incrementing some metric.
         */

        Optional<String> empty = Optional.empty();
        empty.ifPresentOrElse((s) -> {
            System.out.println("optional value:" + s); //if
        }, () -> {
            System.out.println("Optional is empty");//else
        });

        //TODO Stream method
        Optional<String> value = Optional.of("a");
        List<String> collect = value.stream()
                .map(String::toUpperCase)
                .collect(Collectors.toList());

    }
}

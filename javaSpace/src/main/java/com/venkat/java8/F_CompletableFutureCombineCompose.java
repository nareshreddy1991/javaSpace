package com.venkat.java8;

import java.util.concurrent.CompletableFuture;

/*
       Stream                              CompletableFuture
       --------------------------------------------------------------
       pipeline                            pipeline
       lazy                                lazy
       Zero, one or more data              zero or one
       only data channel                   data channel and error channel
       forEach                             thenAccept
       map                                 thenApply
       exception -- oops nope              error channel
       (( zip ))) not present              thenCombine
       flatMap                              thenCompose


        */

public class F_CompletableFutureCombineCompose {
    public static void main(String[] args) {
        //TODO combine two different CF, aggregate results, both type should be same
        create(2)
                .thenCombine(create(3), (result1, result2) -> result1 + result2)
                .thenApply(e -> e * 2)
                .thenAccept(System.out::println);
        //TODO compose
        /*
        Stream:
        function return data- map
        function return Stream - flatMap
        CF:
        Function return data - thenApply/thenAccept
        Function return CF - thenCompose
         */
        create(2)
//                .thenApply(e -> inc(e))
                .thenCompose(e -> inc(e))
                .thenAccept(System.out::println);
        //TODO allOf -- once all the CFs are completed are
        CompletableFuture<Integer> cf1 = CompletableFuture.supplyAsync(() -> 5)
                .thenApply(e -> e * 2);
        CompletableFuture<Integer> cf2 = CompletableFuture.supplyAsync(() -> 5)
                .thenApply(e -> e * 2);
        CompletableFuture<Void> allCf = CompletableFuture.allOf(cf1, cf2);
        allCf.join(); //return results when completed like get() it is also blocking

    }

    public static CompletableFuture<Integer> create(int n) {
        return CompletableFuture.supplyAsync(() -> n);
    }

    public static CompletableFuture<Integer> inc(int n) {
        return CompletableFuture.supplyAsync(() -> n + 1);
    }
}

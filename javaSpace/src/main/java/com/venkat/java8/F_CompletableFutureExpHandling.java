package com.venkat.java8;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class F_CompletableFutureExpHandling {
    public static void main(String[] args) throws Exception {
        //TODO exceptionally
        create()//exception is thrown
                .thenApply(e -> e * 2)// this is skipped
                .thenApply(e -> e + 1)
                .exceptionally(throwable -> handler(throwable))//exception is catch here & recovered
                .thenAccept(e -> System.out.println("Recovered:" + e)); // proceed from recovery

        //TODO lazy exceptionally
        CompletableFuture<Integer> cf = new CompletableFuture<>();
        cf.thenApply(e -> e * 2)
                .thenApply(e -> e + 1)
                .exceptionally(throwable -> handler(throwable))
                .thenAccept(e -> System.out.println("Lazy Recovered:" + e));
        if (true)
            cf.completeExceptionally(new Throwable());
        else
            cf.complete(10);
        //TODO timeout? java9
        //CompletableFuture has three states: Pending(how long...), Resolved, Rejected, once CF reaches Resolve or Rejected we can't change it
        //Dont do anything without timeout, java8 doesn't have any methods
        //cf.completeOnTimeout(-1, 1, TimeUnit.SECONDS) // in java9 we have this method, if CF is not completed then return -1
        //cf.orTimeout(2, TimeUnit.SECONDS) //throws CompletionException

        //TODO handle
        CompletableFuture completableFuture = CompletableFuture
                .supplyAsync(() -> Integer.parseInt("s"))
                .handle((result, e) -> { //recovered from the exception conditionally
                    if (e != null) {
                        System.out.println(e.getMessage());
                        return "Error!";
                    } else {
                        return result + 1;
                    }
                })
                .thenAccept(System.out::println);

        //TODO
        CompletableFuture<Void> completableFuture2 = CompletableFuture
                .supplyAsync(() -> Integer.parseInt("s"))
                .whenCompleteAsync((result, e) -> {//its a biconsumer so its not returning anything
                    if (e != null) System.out.println(e.getMessage());
                })
                .thenAcceptAsync(System.out::println);
    }

    private static Integer handler(Throwable throwable) {
        System.out.println("not a big deal we can recover");
        return 0;
    }

    private static Integer map(Integer e) throws RuntimeException {
        if (true)
            throw new RuntimeException();
        return e * 1;
    }

    public static CompletableFuture<Integer> create() throws Exception {
        return CompletableFuture.supplyAsync(() -> {
            if (true)
                throw new RuntimeException();
            return 10;
        });
    }
}

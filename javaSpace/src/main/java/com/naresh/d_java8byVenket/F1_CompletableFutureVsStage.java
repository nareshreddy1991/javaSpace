package com.naresh.d_java8byVenket;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadLocalRandom;

/*
CompletionStage Interface
java.util.concurrent.CompletionStage<T> interface represents a computation task (either synchronous or asynchronous).
 As all methods declared in this interface return an instance of CompletionStage itself, multiple CompletionStages can be chained together in different ways to
  complete a group of tasks.

CompletableFuture class
CompletableFuture<T> implements CompletionStage<T> and Future<T>.
The static factory methods in this class are the starting points for executing tasks.
On basic level, the tasks started by CompletableFuture can be divided in two categories:

The tasks which do not return any result.
The tasks which return a result.
 */
public class F1_CompletableFutureVsStage {
    public static void main(String[] args) {
        //TODO The tasks which do not return any result
        CompletableFuture<Void> cf =
                CompletableFuture.runAsync(() -> {//stage1
                    System.out.println("Stage1: running, in thread: " + Thread.currentThread().getName());
                }).thenRun(()->{//stage2
                    System.out.println("Stage2: running, in thread: " + Thread.currentThread().getName());
                });
        cf.join();//waits until task is completed
        try {
            cf.get(); //same as join() but throws exception
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("main exiting, thread: " + Thread.currentThread().getName());

        //TODO The tasks which return a result
        CompletableFuture<Integer> cf2 =
                CompletableFuture.supplyAsync(() -> ThreadLocalRandom.current().nextInt(1, 10));
        Integer integer = cf2.join();//similar to cf.get()
        System.out.println(integer);
    }
}

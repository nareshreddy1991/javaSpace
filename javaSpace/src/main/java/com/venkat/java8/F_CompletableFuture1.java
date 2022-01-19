package com.venkat.java8;

import com.defogTech.concurrency.ThreadUtils;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;

/*
Support Asyn programming, non blocking
    -Drawbacks of future
    -lesson from javascripts
        -Callbacks
            - lacks of consistancy
            - hard to deal with
         -Promises

      Promises: (zero or one data will be flown)   | in reactive stream more data will be flown, it has three channels
        Two channels
            data channel
            error channel(treat error as another form of data)

            data------f---------------f-------------------------------
                        \           /
            error----------f-------f-----------------------------------
      - Java script Promises are CompletableFutures in java8

      //Famous functional interfaces
      Supplier<T>       T get()             - Factories
      Predicate<T>   boolean test(T)        - filter
      Function<T,R>   R apply(T)            - map
      Consumer<T>   void accept(T)          - forEach

 */
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

         */
public class F_CompletableFuture1 {
    public static void main(String[] args) {
        create()
                .thenAccept(System.out::println)// accept is consumer is not returning anything
                .thenRun(() -> System.out.println("I am not dead"))//runnable is not taking anything & not returning anything
                .thenRun(() -> System.out.println("really I am not dead"));
        //TODO blocking
        CompletableFuture<Integer> cf = create();
        System.out.println("got it");
        try {
            System.out.println("Result" + cf.get()); //blocking operation
            System.out.println("Result" + cf.getNow(0)); //nonblocking operation, it gives default value if it is not done
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        //TODO which thread is executing what
        CompletableFuture<Integer> cf2 = create();
        ThreadUtils.sleep(100);
        //While executing this line if completableFuture is already done then main thread executes below line,
        //otherwise the same thread that created CF will continue executing
        cf2.thenAccept(e -> System.out.println(Thread.currentThread().getName() + ":" + e));

        //TODO async methods
        CompletableFuture.supplyAsync(() -> 50)//this runs in separate thread, the same thread will be used to run entire pipeline
                .thenApply(e -> e * 2)
                .thenAccept(System.out::println);
        //here every operation might run in separate thread because we used async in all the methods
        CompletableFuture.supplyAsync(() -> 50)
                .thenApplyAsync(e -> e * 2)
                .thenAcceptAsync(System.out::println);

        //TODO custom pool
        ForkJoinPool pool1 = new ForkJoinPool(20);
        ForkJoinPool pool2 = new ForkJoinPool(20);
        create()
                .thenApplyAsync(e -> e * 2, pool2)// this will run in pool2
                .thenAcceptAsync(System.out::println, pool1);// this will run in pool1
        //TODO usage of accept
        create()
                .thenAccept(System.out::println)// used to send emails,logging etc

                .thenRun(() -> System.out.println("Successfully completed"));//telling above operations are successful


        //TODO Lazy
        CompletableFuture<Integer> cf3 = new CompletableFuture<Integer>();
        cf3.thenApply(data -> data * 2)
                .thenApply(data -> data + 1)
                .thenAccept(e -> System.out.println("CF3:" + e));
        System.out.println("pipeline is prepared but nothing is flowing through it");
        cf3.complete(10);//push the value to pipeline, only after this operation CF will be evaluated
        ThreadUtils.sleep(1000);
        cf3.complete(20);//not working TODO?

        ThreadUtils.sleep(1000);
    }

    static CompletableFuture<Integer> create() {

        return CompletableFuture.supplyAsync(() -> {
            ThreadUtils.sleep(200);
            System.out.println(Thread.currentThread().getName() + "created");
            return 10;
        });
    }
}

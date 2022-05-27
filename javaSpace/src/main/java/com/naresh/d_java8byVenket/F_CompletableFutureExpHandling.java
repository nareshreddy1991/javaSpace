package com.naresh.d_java8byVenket;

import com.naresh.m_concurrencyDefogTech.ThreadUtils;

import java.util.concurrent.CompletableFuture;

public class F_CompletableFutureExpHandling {
    public static void main(String[] args) throws Exception {
        //TODO exceptionally
        create()//exception is thrown
                .thenApply(e -> e * 2)// this is skipped
                .thenApply(e -> e + 1).exceptionally(throwable -> handler(throwable))//exception is catch here & recovered
                .thenAccept(e -> System.out.println("Recovered:" + e)); // proceed from recovery

        //TODO lazy exceptionally
        CompletableFuture<Integer> cf = new CompletableFuture<>();
        cf.thenApply(e -> {
            System.out.println("Running cf");
            return e * 2;
        }).thenApply(e -> {
            System.out.println("Running cf in next step");
            ThreadUtils.sleep(1000);
            return e + 1;
        }).exceptionally(throwable -> handler(throwable)).thenAccept(e -> System.out.println("Lazy Recovered:" + e));
        if (true) cf.completeExceptionally(new Throwable());//complete the cf with exception
        else
            cf.complete(10);//completes cf normally, if we call complete & completeExceptionally only one will be successful
        //TODO timeout? java9
        //CompletableFuture has three states: Pending(how long...), Resolved, Rejected, once CF reaches Resolve or Rejected we can't change it
        //Dont do anything without timeout, java8 doesn't have any methods
        //cf.completeOnTimeout(-1, 1, TimeUnit.SECONDS) // in java9 we have this method, if CF is not completed then return -1
        //cf.orTimeout(2, TimeUnit.SECONDS) //throws CompletionException

        //TODO handle
        CompletableFuture completableFuture = CompletableFuture.supplyAsync(() -> Integer.parseInt("s"))
                .handle((result, e) -> { //recovered from the exception conditionally
            if (e != null) {
                System.out.println(e.getMessage());
                return "Error!";
            } else {
                return result + 1;
            }
        }).thenAccept(System.out::println);
        ThreadUtils.sleep(2000);
        //TODO whencomplete
        System.out.println("Oncomplete");
        runTasks(2); // no exception occured, still whenComplete will be called, and then following methods
        ThreadUtils.sleep(2000);
        runTasks(0);// exception will be occured, following chained methods will not be called
        System.out.println("donedone");
        ThreadUtils.sleep(2000);
    }
/*
whenComplete - it is not for exception handling
handle() vs whenComplete()
The above methods, accept BiConsumer, whereas CompletionStage.handle(....) methods accept BiFunction. That means handle() methods are allowed to return
a result (in case of exception a recovering result) thus they can handle the exception. On the other hand, whenComplete() methods cannot return a results.
 So they are used as merely callbacks that do not interfere in the processing pipeline of CompletionStages.

If there's an unhandled exception coming from the stages before 'whenComplete' stage then that exception is passed through as it is.
In other words if the upstream CompletionStage completes exceptionally, the CompletionStage returned by whenComplete() also completes exceptionally.

 */
    private static void runTasks(int i) {
        System.out.printf("-- input: %s --%n", i);
        CompletableFuture
                .supplyAsync(() -> {
                    return 16 / i;
                })
                .whenComplete((input, exception) -> {//its a biconsumer
                    if (exception != null) {
                        System.out.println("exception occurs");
                        System.err.println(exception);
                    } else {
                        System.out.println("no exception, got result: " + input);
                    }
                })
             /*   .exceptionally(throwable -> // since whenComplete is passing the exception as it is the next stage can handle the exception
                {
                    System.out.println("recovering in exceptionally: " + throwable);
                    return 1;
                })*/
                .thenApply(input -> input * 3)
                .thenAccept(System.out::println);

    }

    private static Integer handler(Throwable throwable) {
        System.out.println("not a big deal we can recover");
        return 0;
    }

    private static Integer map(Integer e) throws RuntimeException {
        if (true) throw new RuntimeException();
        return e * 1;
    }

    public static CompletableFuture<Integer> create() throws Exception {
        return CompletableFuture.supplyAsync(() -> {
            if (true) throw new RuntimeException();
            return 10;
        });
    }
}

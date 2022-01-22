package com.java9;

import com.defogTech.concurrency.ThreadUtils;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

/*
Executor defaultExecutor()
CompletableFuture<U> newIncompleteFuture()
CompletableFuture<T> copy()
CompletionStage<T> minimalCompletionStage()
CompletableFuture<T> completeAsync(Supplier<? extends T> supplier, Executor executor)
CompletableFuture<T> completeAsync(Supplier<? extends T> supplier)
CompletableFuture<T> orTimeout(long timeout, TimeUnit unit)
CompletableFuture<T> completeOnTimeout(T value, long timeout, TimeUnit unit)
 */
public class B_J9_CompletableFuture {
    public static void main(String[] args) {
        B_J9_CompletableFuture obj = new B_J9_CompletableFuture();
//        obj.map(10); //TODO? we can call static methods by obj ref, but in method reference its giving error
        //TODO CompletableFuture
        Executor exe = CompletableFuture.delayedExecutor(2, TimeUnit.SECONDS);//note this is not ExecutorService
        exe.execute(() -> System.out.println("Runnable is running")); //This runs after 5secs

        ThreadUtils.sleep(3000);
        System.out.println("Executor Done");
/*
The orTimeout() method can be used to specify that if a given task doesn't complete within a certain period of time, the program stops execution and throws TimeoutException
 whereas completeOnTimeOut() method completes the CompletableFuture with the provided value.If not, it complete before the given timeout.
 */
        //TODO timeout? java9
        //CompletableFuture has three states: Pending(how long...), Resolved, Rejected, once CF reaches Resolve or Rejected we can't change it
        //Dont do anything without timeout, java8 doesn't have any methods
        //TODO timeout - Eager cf
        CompletableFuture cf = create()
                .thenApply(e -> {
                    System.out.println(Thread.currentThread().getName() + ":Value received1:" + e);
                    ThreadUtils.sleep(100);
                    return e * 2;
                })
                .thenApply(e -> {
                    ThreadUtils.sleep(1000);//flow will be aborted on timeout
                    System.out.println(Thread.currentThread().getName() + ":Value received2:" + e);
                    return e + 1;
                })
                .thenApply(B_J9_CompletableFuture::map)
                /*      .handle((v, e) -> {
                          e.printStackTrace();
                          return -2;
                      })*/
                .thenAccept(e -> System.out.println("Timeout Result:" + e));
        cf.completeOnTimeout(-1, 1, TimeUnit.SECONDS); // in java9 we have this method, if CF is not completed then return -1

/*        cf.orTimeout(1, TimeUnit.SECONDS); //throws TimeoutException, but this exception we cant catch in completableStage
        cf.join();// calling this method throws TimeoutException*/
        ThreadUtils.sleep(3000);
        /*
        TODO with lazy cf calling timeout behaves the same way, since no value is
         */
        CompletableFuture<Integer> cf2 = new CompletableFuture<Integer>();
        cf2.thenApply(e -> {
                    System.out.println(Thread.currentThread().getName() + ":Value received1:" + e);
                    ThreadUtils.sleep(100);
                    return e * 2;
                })
                .thenApply(e -> {
                    ThreadUtils.sleep(1000);//flow will be aborted on timeout
                    System.out.println(Thread.currentThread().getName() + ":Value received2:" + e);
                    return e + 1;
                })
                .thenApply(B_J9_CompletableFuture::map);
                /*      .handle((v, e) -> {
                          e.printStackTrace();
                          return -2;
                      })*/
        System.out.println("Lazy cf created");
//        cf2.complete(10);//TODO when called with complete, its completing normally , timeout is not happening?? either we should call complete or completeOntimeout
        cf2.completeOnTimeout(-1, 1, TimeUnit.SECONDS);
        cf2.join();
        ThreadUtils.sleep(3000);
        //TODO examples
        int a = 10;
        int b = 15;
        CompletableFuture.supplyAsync(() -> {
                    try {
                        TimeUnit.SECONDS.sleep(5);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return a + b;
                })
                .orTimeout(4, TimeUnit.SECONDS)
                .whenComplete((result, exception) -> {
                    System.out.println(result);
                    if (exception != null)
                        exception.printStackTrace();
                });
        //TODO
        CompletableFuture.supplyAsync(() -> {
                    try {
                        TimeUnit.SECONDS.sleep(5);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return a + b;
                })
                .completeOnTimeout(0, 4, TimeUnit.SECONDS)
                .thenAccept(result -> System.out.println(result));
    }

    private static Integer map(Integer val) {
        ThreadUtils.sleep(100);
        System.out.println("map is done:" + Thread.currentThread().getName() + "value:" + val);
        return val * 2;
    }

    private static CompletableFuture<Integer> create() {
        return CompletableFuture.supplyAsync(() -> {
            System.out.println("Async created by:" + Thread.currentThread().getName());
            return 5;
        });
    }
}

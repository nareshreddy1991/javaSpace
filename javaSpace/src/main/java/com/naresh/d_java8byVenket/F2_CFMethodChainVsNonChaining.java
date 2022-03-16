package com.naresh.d_java8byVenket;

import com.naresh.m_concurrencyDefogTech.ThreadUtils;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class F2_CFMethodChainVsNonChaining {
    public static void main(String[] args) {
        //TODO Eager CF
        //TODO CF chaining -- all the method will be run by common pool thread
        create().thenApply(e -> {
            System.out.println(Thread.currentThread().getName() + ":thenApply1");
            return e * 2;
        }).thenApply(e -> {
            System.out.println(Thread.currentThread().getName() + ":thenApply2");
            return e * 2;
        }).thenAccept(e -> {
            System.out.println(Thread.currentThread().getName() + ":thenAccept:" + e);
        });
        System.out.println("cf created");
        ThreadUtils.sleep(1000);
        System.out.println("Done1");

        //TODO non chaining -- create will be run by common pool thread, other methods run by main(if create is completed before running next statement)
        //TODO imp: all statements are executed in the same order given
        CompletableFuture<Integer> cf2 = create();
        cf2.thenApply(e -> {
            System.out.println(Thread.currentThread().getName() + ":thenApply1");
            ThreadUtils.sleep(50);//TODO order is maintained even if there is a delay
            return e * 2;
        });
        cf2.thenApply(e -> {
            System.out.println(Thread.currentThread().getName() + ":thenApply2");
            return e * 2;
        });
        cf2.thenApply(e -> {
            System.out.println(Thread.currentThread().getName() + ":thenApply3");
            return e * 2;
        });
        cf2.thenAccept(e -> {
            System.out.println(Thread.currentThread().getName() + ":thenAccept1:" + e);
        });
        cf2.thenAccept(e -> {
            System.out.println(Thread.currentThread().getName() + ":thenAccept2:" + e);
        });
        System.out.println("cf2 created");
        ThreadUtils.sleep(1000);
        System.out.println("Done2");

        //TODO lazy CF
        //TODO CF chaining -- running by main thread
        CompletableFuture<Integer> cf3 = new CompletableFuture<>();
        cf3.thenApply(e -> {
            System.out.println(Thread.currentThread().getName() + ":thenApply1");
            return e * 2;
        }).thenApply(e -> {
            System.out.println(Thread.currentThread().getName() + ":thenApply2");
            return e * 2;
        }).thenAccept(e -> {
            System.out.println(Thread.currentThread().getName() + ":thenAccept:" + e);
        });
        System.out.println("cf3 created");
        cf3.complete(100);
        ThreadUtils.sleep(1000);
        System.out.println("Done3");

        //TODO non chaining -- running by main (order of execution is **un know**)
        // & what ever the value we are passing that is directly passed to each statement output:100 only
        CompletableFuture<Integer> cf4 = new CompletableFuture<>();
        cf4.thenApply(e -> {
            System.out.println(Thread.currentThread().getName() + ":thenApply1");
            return e * 2;
        });
        cf4.thenApply(e -> {
            System.out.println(Thread.currentThread().getName() + ":thenApply2");
            return e * 2;
        });
        cf4.thenAccept(e -> {
            System.out.println(Thread.currentThread().getName() + ":thenAccept1:" + e);
        });
        System.out.println("cf4 created");
        cf4.complete(100);
        cf4.thenApply(e -> {
            System.out.println(Thread.currentThread().getName() + ":thenApply3");
            return e * 2;
        });
        cf4.thenAccept(e -> {
            System.out.println(Thread.currentThread().getName() + ":thenAccept2:" + e);
        });
        ThreadUtils.sleep(1000);
        System.out.println("Done4");

        //TODO lazy CF - calling complete multiple times
        CompletableFuture<Integer> cf5 = new CompletableFuture<>();
        cf5.thenApply(e -> {
            System.out.println(Thread.currentThread().getName() + ":thenApply1");
            return e * 2;
        }).thenApply(e -> {
            System.out.println(Thread.currentThread().getName() + ":thenApply2");
            return e * 2;
        }).thenAccept(e -> {
            System.out.println(Thread.currentThread().getName() + ":thenAccept:" + e);
        });
        System.out.println("cf5 created");
        cf5.complete(100);//This is running
        boolean complete = cf5.complete(200);//This has no effect because cf is already completed
        ThreadUtils.sleep(1000);
        System.out.println("Done5 complete status:" + complete); //false

        //TODO lazy CF - calling complete then completeExceptionally
        CompletableFuture<Integer> cf6 = new CompletableFuture<>();
        cf6.thenApply(e -> {
            System.out.println(Thread.currentThread().getName() + ":thenApply1");
            return e * 2;
        }).thenApply(e -> {
            System.out.println(Thread.currentThread().getName() + ":thenApply2");
            return e * 2;
        }).thenAccept(e -> {
            System.out.println(Thread.currentThread().getName() + ":thenAccept:" + e);
        });
        System.out.println("cf6 created");
        cf6.complete(100);//This is running
        boolean completeExp = cf6.completeExceptionally(new RuntimeException());//This has no effect because cf is already completed
        ThreadUtils.sleep(1000);
        System.out.println("Done6 completeExceptionally status:" + completeExp);

        //TODO lazy CF - calling completeExceptionally & complete
        CompletableFuture<Integer> cf7 = new CompletableFuture<>();
        cf7.thenApply(e -> {
            System.out.println(Thread.currentThread().getName() + ":thenApply1");
            return e * 2;
        }).thenApply(e -> {
            System.out.println(Thread.currentThread().getName() + ":thenApply2");
            return e * 2;
        }).thenAccept(e -> {
            System.out.println(Thread.currentThread().getName() + ":thenAccept:" + e);
        });
        System.out.println("cf7 created");
        completeExp = cf7.completeExceptionally(new RuntimeException());//Exception is thrown, we will get it when we call future.get()/join() only
        complete= cf7.complete(100);//not running
//        cf7.join();// we get Runtime exception (since its not a checked exception we no need to handle in the code)
        try {
            cf7.get(); // This also throw RuntimeException, but we need to handle checked exceptions
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        ThreadUtils.sleep(1000);
        System.out.println("Done7 completeExceptionally status:" + completeExp+" complete status:"+complete);
    }

    static CompletableFuture<Integer> create() {

        return CompletableFuture.supplyAsync(() -> {
//            ThreadUtils.sleep(1);
            System.out.println(Thread.currentThread().getName() + ":created");
            return 10;
        });
    }
}

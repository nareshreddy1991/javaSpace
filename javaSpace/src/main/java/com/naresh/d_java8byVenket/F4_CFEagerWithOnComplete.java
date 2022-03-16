package com.naresh.d_java8byVenket;

import com.naresh.m_concurrencyDefogTech.ThreadUtils;

/*
A Future that may be explicitly completed (setting its value and status), and may be used as a CompletionStage, supporting dependent functions and actions that trigger upon its completion.
When two or more threads attempt to complete, completeExceptionally, or cancel a CompletableFuture, only one of them succeeds.
 */
import java.util.concurrent.CompletableFuture;

public class F4_CFEagerWithOnComplete {
    public static void main(String[] args) {
        //TODO case1: eager cf started, in middle of executing & then onComplete is called - onComplete doesn't have any effect
        CompletableFuture<Integer> cf = create(0);//returning 5
        cf.thenApply(e -> {
            System.out.println(Thread.currentThread().getName() + ":thenApply1:"+e);
            ThreadUtils.sleep(100);
            return e * 2;
        }).thenApply(e -> {
            System.out.println(Thread.currentThread().getName() + ":thenApply2:"+e);
            ThreadUtils.sleep(100);
            return e * 2;
        }).thenApply(e -> {
            System.out.println(Thread.currentThread().getName() + ":thenApply3:"+e);
            return e * 2;
        }).thenAccept(e -> {
            System.out.println(Thread.currentThread().getName() + ":thenAccept:" + e);
        });
        System.out.println("cf created");
        ThreadUtils.sleep(200);
        boolean complete = cf.complete(10);
        System.out.println("is complete successful:"+complete);//return false
        ThreadUtils.sleep(1000);
        System.out.println("Done1");

        //TODO case2: eager cf not started,& then onComplete is called - onComplete is executing the cf
        CompletableFuture<Integer> cf2 = create(100);//returning 5
        cf2.thenApply(e -> {
            System.out.println(Thread.currentThread().getName() + ":thenApply1:"+e);
            return e * 2;
        }).thenApply(e -> {
            System.out.println(Thread.currentThread().getName() + ":thenApply2:"+e);
            return e * 2;
        }).thenApply(e -> {
            System.out.println(Thread.currentThread().getName() + ":thenApply3:"+e);
            return e * 2;
        }).thenAccept(e -> {
            System.out.println(Thread.currentThread().getName() + ":thenAccept:" + e);
        });
        System.out.println("cf created");
        complete = cf2.complete(10);
        System.out.println("is complete successful:"+complete);//return true
        ThreadUtils.sleep(1000);
        System.out.println("Done2");



    }

    private static CompletableFuture<Integer> create(long delay) {
        return CompletableFuture.supplyAsync(() -> {
            ThreadUtils.sleep(delay);
            System.out.println("Async created by:" + Thread.currentThread().getName());
            return 5;
        });
    }

    private static Integer map(Integer val) {
//        ThreadUtils.sleep(5000);
        System.out.println(Thread.currentThread().getName() + ":map is done:" + Thread.currentThread().getName() + "value:" + val);
        return val * 2;
    }
}

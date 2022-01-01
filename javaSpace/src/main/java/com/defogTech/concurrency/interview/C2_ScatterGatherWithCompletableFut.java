package com.defogTech.concurrency.interview;

import com.defogTech.concurrency.ThreadUtils;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.*;

/*
Scatter Gather: Problem
Get price information from multiple online shopping websites, return the results with in 3secs, if any website is not giving results
ignore them.

we can also solve this problem using
    - Callable & Future
we can span 3 threads and will get 3 futures
try{
    String r1=f1.get(3, TimeUnit.SECONDS);//it will only wait for 3 sec for the first Future
    String r2=f2.get(0, TimeUnit.SECONDS);//if results are ready we get it otherwise exception
    String r3=f3.get(0, TimeUnit.SECONDS);
    }
 */
public class C2_ScatterGatherWithCompletableFut {
    public static void main(String[] args) throws InterruptedException {
        Set<String> priceSet = Collections.synchronizedSet(new HashSet<>());
        System.out.println("Started...");
        CompletableFuture<Void> f1 = CompletableFuture.runAsync(new PriceFetch2(priceSet, null, null, 1));
        CompletableFuture<Void> f2 = CompletableFuture.runAsync(new PriceFetch2(priceSet, null, null, 4));
        CompletableFuture<Void> f3 = CompletableFuture.runAsync(new PriceFetch2(priceSet, null, null, 2));
        CompletableFuture<Void> allf = CompletableFuture.allOf(f1, f2, f2);
        try {
            allf.get(3, TimeUnit.SECONDS);// 3sec will be applicable for all tasks
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        //print results after 3 secs
        System.out.println("Results:" + priceSet);
    }
}

class PriceFetch2 implements Runnable {

    Set<String> priceSet;
    String url, productId;
    int timeout;

    public PriceFetch2(Set<String> priceSet, String url, String productId, int timeout) {
        this.priceSet = priceSet;
        this.url = url;
        this.productId = productId;
        this.timeout= timeout;
    }

    @Override
    public void run() {
        //code to get price info using url and productId
        // https calls
        ThreadUtils.sleep(timeout*1000);
        priceSet.add(Thread.currentThread().getName() + Math.random());
    }
}

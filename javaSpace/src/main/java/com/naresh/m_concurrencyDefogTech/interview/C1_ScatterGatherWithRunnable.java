package com.naresh.m_concurrencyDefogTech.interview;

import com.naresh.m_concurrencyDefogTech.ThreadUtils;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/*
Scatter Gather: Problem
Get price information from multiple online shopping websites, return the results with in 3secs, if any website is not giving results
ignore them.
 */
public class C1_ScatterGatherWithRunnable {
    public static void main(String[] args) throws InterruptedException {
        Set<String> priceSet = Collections.synchronizedSet(new HashSet<>());
        CountDownLatch latch = new CountDownLatch(3);//we can also use Phaser
        new Thread(new PriceFetch(latch, priceSet, null, null)).start();
        new Thread(new PriceFetch(latch, priceSet, null, null)).start();
        new Thread(new PriceFetch(latch, priceSet, null, null)).start();

//        ThreadUtils.sleep(3000);//problem with this approach is, if all threads finished in 1sec we still wait for 3secs
        latch.await(3, TimeUnit.SECONDS);
        System.out.println("Results:" + priceSet);
    }
}

class PriceFetch implements Runnable {

    CountDownLatch latch;
    Set<String> priceSet;
    String url, productId;

    public PriceFetch(CountDownLatch latch, Set<String> priceSet, String url, String productId) {
        this.latch = latch;
        this.priceSet = priceSet;
        this.url = url;
        this.productId = productId;
    }

    @Override
    public void run() {
        //code to get price info using url and productId
        // https calls
        ThreadUtils.sleep(2000);
        priceSet.add(Thread.currentThread().getName()+Math.random());
        latch.countDown();
    }
}

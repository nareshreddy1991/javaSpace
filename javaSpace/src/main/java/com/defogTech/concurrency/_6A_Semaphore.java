package com.defogTech.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.stream.IntStream;

/*
A semaphore controls access to a shared resource through the use of a counter.
If the counter is greater than zero, then access is allowed. If it is zero, then access is denied.
What the counter is counting are permits that allow access to the shared resource. Thus, to access the resource, a thread must be granted a permit from the semaphore.
 */
public class _6A_Semaphore {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(25);
        Semaphore semaphore = new Semaphore(3);// number of permits, faireness is allowed
        IntStream.rangeClosed(1, 200).forEach(i -> executorService.submit(new ComplexTask(semaphore)));
    }
}


class ComplexTask implements Runnable {
    Semaphore semaphore;

    public ComplexTask(Semaphore semaphore) {
        this.semaphore = semaphore;
    }

    @Override
    public void run() {
//        semaphore.acquire(); we need to catch interruptedException to avoid that we can use acquireInterruptibly
        semaphore.acquireUninterruptibly();// 4th thread will be blocked here untill some thread releases the lock
//        semaphore.acquireUninterruptibly(2);  we can also acquire more than one  permit at a time/ the same number of permits need to be released
        //semaphore.tryAcquire --if lock is available then it will get the lock otherwise it will return false, not a blocking operation
        System.out.println(Thread.currentThread().getName() + " Permit acquired");

        //code to limited access(may be a slow serving req)
        semaphore.release();
        System.out.println(Thread.currentThread().getName() + " Permit released");
    }
}
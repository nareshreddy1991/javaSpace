package com.naresh.m_concurrencyDefogTech;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.IntStream;

/*
ExecutorService - A pool of threads
Creating a thread is expensive process(takes lots of memory), so we shouldn't create many threads
1 java thread = 1 OS thread
We don't need 100 threads to handle 100 tasks.

In threads pools, it has fixed number of threads that will take care of all the tasks.
All this tasks will be stored in blocking queue because its thread safe.
Here every task is instance of Runnable or callable
Number of threads       Blocking queue
-------------      ---------------------------------
-   10      -       | task1 | task2 | .....|task100|
- Threads   -       --------------------------------
-------------
TODO what is ideal pool size?
For CPU intense task it should be same as number of cores in the system. (note the same core will be used by some other application as well)
for I/O(db , network calls, files) it should be more than that because these threads will be in waiting state most of the time
No of threads for IO depends on rate of task submitted & time taken to execute the tasks.

ExecutorService extends Executor interface
 */
public class _4A_ExecutorService {
    public static void main(String[] args) {
        int cores = Runtime.getRuntime().availableProcessors();
        ExecutorService executorService = Executors.newFixedThreadPool(cores);
        IntStream.rangeClosed(1, 10).forEach(f -> {
            Future<?> submit = executorService.submit(new PrintWriter());//we get future, we can pass Callable & Runnable
//            executorService.execute(new PrintWriter());//we can pass only Runnable, it will not return anything
        });
        executorService.shutdownNow(); //if we don't shut down program keeps running
//        executorService.submit(new PrintWriter()); //if we submit any task after pool shout then it will throw rejectedExecutionException
        System.out.println("Shut down initiated");
        while (!executorService.isTerminated())//
            System.out.println("few task are still running..");
        System.out.println("All tasks completed");
    }
}

class PrintWriter implements Runnable {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + ":Running..");
        ThreadUtils.sleep(1000);
    }
}

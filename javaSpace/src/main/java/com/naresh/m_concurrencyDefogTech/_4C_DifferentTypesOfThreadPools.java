package com.naresh.m_concurrencyDefogTech;

import java.util.concurrent.*;

public class _4C_DifferentTypesOfThreadPools {
    public static void main(String[] args) {
        /*Fixed thread pool
            - core pool size as passed as arg & max pool size is Integer.Maxval, keep alive is NA
            - Queue type is LinkedBlocking queue(its size can be increased dynamically)
         */
        ExecutorService executorService0 = Executors.newFixedThreadPool(10);
        /*Single thread pool
            - only one thread will be available all the time,
            - core pool & max pool size is one, keep alive is NA
            - useful for tasks when time is undefined
            - useful when tasks need to be run sequentially
            - Queue type is LinkedBlocking queue
         */
        ExecutorService executorService1 = Executors.newSingleThreadExecutor();
        /*
        CachedThreadPool
            - core pool size is 0, max pool size Interger.Maxvalue, keepalive time is 60sec
            - When a new task is submitted then it will create new Thread
            - from next time, it will check if any available thread is there, if not available its keep creating new threads
            - If thread is ideal for 60 sec then it will be removed from the pool, this will save memory
            - Queue type: SynchronousQueue( no storage at all -- direct handoff)
            - useful for running many shot living tasks.
         */

        /*For scheduling of tasks
             - core pool size is 0, max pool size Interger.Maxvalue, keepalive time is 60sec
             - it uses DelayedWorkQueue
         */
        ExecutorService executorService2 = Executors.newCachedThreadPool();

        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(10);
        //Task to run after 10 sec delay
        scheduledExecutorService.schedule(()->{},10, TimeUnit.MILLISECONDS);
        //Task to run repeatedly every 10 sec delay after some initial delay
        scheduledExecutorService.scheduleAtFixedRate(()->{},15, 10,TimeUnit.MILLISECONDS);
        //Task to run repeatedly 10 sec after previous task is completed
        scheduledExecutorService.scheduleWithFixedDelay(()->{},15, 10,TimeUnit.MILLISECONDS);

        /*
        Custom Thread Pool:
            --- it uses ArrayBlockingQueue(Limited size)
            core pool size -- Initial size of the threads
            current pool size -- current no of threads
            Max pool size -- max no of threads
            keepalive time -- threads will be killed if they are idle for that time
         */
        ThreadPoolExecutor customPool = new ThreadPoolExecutor(10, Integer.MAX_VALUE,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(),
                new RejectedExecutionHandlerExp());//if thread pool is full, new task will be rejected and return that task
    }
}
class RejectedExecutionHandlerExp implements RejectedExecutionHandler{

    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        // You can process the rejected tasks here by main thread or do any other action
    }
}

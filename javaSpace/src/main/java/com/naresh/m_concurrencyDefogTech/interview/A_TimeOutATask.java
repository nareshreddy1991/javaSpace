package com.naresh.m_concurrencyDefogTech.interview;

import com.naresh.m_concurrencyDefogTech.ThreadUtils;

import java.util.concurrent.*;
/* How to time out a task after sometime threshold?
There is no way to stop the thread immediately
but we can ask politely by interrupting/volatile to thread
 */
public class A_TimeOutATask {

    public static void main(String[] args) {
        DifferentWaysOfTimeOutATask timeOutATask = new DifferentWaysOfTimeOutATask();
        timeOutATask.threadInterrupt();
    }
}

class DifferentWaysOfTimeOutATask {
    private long timeOutInMS = 10000; //10 secs

    public void threadInterrupt(){
        Thread t= new Thread(()->{
            //some Processing
            while (!Thread.currentThread().isInterrupted()) {
                //do something
                System.out.println("Running...");
            }
            System.out.println("Task Complated");
        });
        t.start();
        try {
            Thread.sleep(timeOutInMS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t.interrupt();
    }

    public void usingExecutorShutdownNow() {// with Runnable
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.submit(() -> {
            //some Processing
            while (!Thread.currentThread().isInterrupted()) {
                //do something
                System.out.println("Running...");
            }
            System.out.println("Task Complated");
        });
        try {
            Thread.sleep(timeOutInMS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //We can use below approach to shutdown
/*        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.schedule(()->{
                executorService.shutdownNow();
        },timeOutInMS,TimeUnit.MILLISECONDS);
        scheduledExecutorService.shutdown();*/

        executorService.shutdownNow();
    }

    public void usingFutureGet() { //with Callable
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<Object> future = executorService.submit(() -> {
            //some Processing
            try {
                Thread.sleep(timeOutInMS * 2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        });

        try {
            Object result = future.get(timeOutInMS, TimeUnit.MILLISECONDS);//if we don't get result in 30 seconds throw Exception
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            System.out.println("Successfully Cancelled");
            e.printStackTrace();
            future.cancel(true);
        }
        executorService.shutdown();
    }

    public void usingFutureCancel() { //with Callable
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<Object> future = executorService.submit(() -> {
            //some Processing
            while (!Thread.currentThread().isInterrupted()) {
                System.out.println("Running....");
                //TODO if there is any IO calls like stored procedure/HTTP calls then we need to implement timeout for those calls using their API's
            }
            System.out.println("Task Completed");
            return null;
        });

        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.schedule(() -> {
            future.cancel(true);
        }, timeOutInMS, TimeUnit.MILLISECONDS);

        scheduledExecutorService.shutdown();
        executorService.shutdown();
    }

    public void usingVolatile() { //with Runnable

        class X implements Runnable {
            volatile boolean stopFlag;// or AtomicBoolean etc

            public X(boolean stopFlag) {
                this.stopFlag = stopFlag;
            }

            @Override
            public void run() {
                while (!stopFlag) {
                    System.out.println("Running....");
                    //TODO if there is any IO calls like stored procedure/HTTP calls then we need to implement timeout for those calls using their API's
                }
                System.out.println("Completed using volatile");
            }
        }
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        X task = new X(false);
        executorService.submit(task);

        ThreadUtils.sleep(timeOutInMS);
        task.stopFlag = true;
        executorService.shutdown();
    }
}


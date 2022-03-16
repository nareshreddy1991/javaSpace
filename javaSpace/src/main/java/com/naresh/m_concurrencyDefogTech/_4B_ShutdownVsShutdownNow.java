package com.naresh.m_concurrencyDefogTech;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class _4B_ShutdownVsShutdownNow {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(1);
        IntStream.rangeClosed(1, 3).forEach(f ->
                executor.submit(() -> {
                            while (true) {
                                if (Thread.currentThread().isInterrupted()) {
                                    System.out.println(Thread.currentThread().getName() + "interrupted");
                                    break;
                                }
                                System.out.println(Thread.currentThread().getName() + "Running");
                                ThreadUtils.sleep(100);
                            }
                        }
                ));
        /*
        Shutdown - after shutdown it will not accept any task but all the tasks that are running & waiting in the queue will be completed normally.
        ShutdownNow - after this action no new tasks will be accepted,
                    - it will cancel all the task that are waiting in the queue & return the task list
                    - it will interrupt all current running threads, so we need to check for isInterrupt flag exit gracefully
         awaitTermination - its a blocking operation so it may throw InterruptedException
                    - it will wait untill all the threads completed or till the time out
                    - it will return true if all the task completed, if timeout is happen then return false
                    - This method can be called even before shutdown but doesn't make sense
        isTerminated - Returns: true if all tasks have completed following shut down(Only after calling shutdown)
         */
        executor.shutdown();
//        List<Runnable> cancelledTaskList = executor.shutdownNow();
        if (!executor.awaitTermination(5000, TimeUnit.MILLISECONDS)) {
            System.out.println("Still waiting after 100ms: calling System.exit(0)...");
            System.exit(0);
        }
        System.out.println("Exiting normally...");
    }
}

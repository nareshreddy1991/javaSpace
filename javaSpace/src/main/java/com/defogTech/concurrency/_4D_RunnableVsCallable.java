package com.defogTech.concurrency;


import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class _4D_RunnableVsCallable {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<Integer> submit = executorService.submit(new CallX());
        /*
        If the task is not started then it will cancel & return true
        If the task is running then based on the mayInterruptIfRunning flag it will set the interrupt flag
        After calling this method subsequent calls to isDone() & isCancelled() will be true.(when this method return true)
        This operation will fail if the task is already completed or Cancelled.
         */
        submit.cancel(true);
    }
}

/*
    - We can't return any value
    - We can't throw Checked Exception(Subclasses of Exception & user defined exception)
    - we can still throw RuntimeException
 */
class RunX implements Runnable {

    @Override
    public void run() {
        if (true)
            throw new RuntimeException();
        else {
//            throw new Exception();
//            throw new DummyEx();
        }
    }

    class DummyEx extends Exception {

    }
}

/*
    -- We can return value in the form of Future<T>(its a place holder, value will be arrived in future)
    -- we can throw Checked exception
 */
class CallX implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        return null;
    }
}

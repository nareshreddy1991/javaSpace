package com.naresh.m_concurrencyDefogTech;

import java.util.concurrent.*;

/*
It provides tools to help speed up parallel processing by attempting to use all available processor cores – which is accomplished through a divide and conquer approach.
In practice, this means that the framework first “forks”, recursively breaking the task into smaller independent subtasks until they are simple enough to be executed asynchronously.
After that, the “join” part begins, in which results of all subtasks are recursively joined into a single result, or in the case of a task which returns void, the program simply waits until every subtask is executed.
ForkJoin
    -Fork means dividing the task to subtasks
    -Join , finally join all the subtasks to get the final results.
ForkJoinPool extends AbstractExecutorService implements ExecutorService extends Executors
ForkJoinPool class is an extension of the Executorservice
*******
Worker threads can execute only one task at a time, but the ForkJoinPool doesn’t create a separate thread for every single subtask. Instead, each thread in the pool has its own double-ended queue (or deque, pronounced deck) which stores tasks.
This architecture is vital for balancing the thread’s workload with the help of the work-stealing algorithm.
Work stealing Algorithm:
Simply put – free threads try to “steal” work from deques of busy threads.
By default, a worker thread gets tasks from the head of its own deque. When it is empty, the thread takes a task from the tail of the deque of another busy thread or from the global entry queue, since this is where the biggest pieces of work are likely to be located.
This approach minimizes the possibility that threads will compete for tasks. It also reduces the number of times the thread will have to go looking for work, as it works on the biggest available chunks of work first.

In forkjoinpool, main queue will be there, threads start picking the tasks from main queue, when thread creates a subtask from the main task those subtasks will be stored in the
Double ended queue of every thread, so that thread will executing its own tasks/subtaks from the queue, if any thread is done with its tasks then it will steal tasks from other
threads Deck from the tail.(This is called work stealing)
                                common Queue(for external submission)
        ------------------------------------
        |Task1|....................|Task n|
        ------------------------------------

        Thread1         Deck(Double ended Queue) - subtasks
        ------------------------
        |subtask1|.....|subtask2|
        ------------------------
        Thread2         Deck(Double ended Queue)
        ------------------------
        |subtask1|.....|subtask2|
        ------------------------
*****************
ForkJoinTask - is the base type for tasks executed inside ForkJoinPool. In practice, one of its two subclasses should be extended:
 - the RecursiveAction for void tasks and
 - the RecursiveTask<V> for tasks that return a value. They both have an abstract method compute() in which the task’s logic is defined.
Need to implement compute method, for Forkjoinpool to work effectively
    - Avoid Synchronization
    - No Shared variable
    - Dont perform blocking IO operations
    - Are pure functions
    - Are isolated
Few place where it can be used
    -Sorting
    - Matrix multiplication
    - Best move finder for a game
    - Tree Traversal

*************
ForkJoinPool is same as ExecutorService in below case
    -- submitting normal task & getting results from it as Future
ForkjoinPool is different from ExecutorService in these cases
    -- A task is internally creating subtasks(in this case ForkjoinPool work effectively) Ex: in recursions(fibonaci)
    --


 */
public class _9_ForkJoinPool {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();
        long start = System.currentTimeMillis();
        int n=40;
        ForkJoinTask<Integer> forkJoinTask = forkJoinPool.submit(new FindFibonacci(n));
        Integer integer = forkJoinTask.get();
        long end = System.currentTimeMillis();
        System.out.println("Result:" + integer + "Time Taken:" + (end - start));
        forkJoinPool.shutdown();
        System.out.println("Forkjoin is Done");

        ExecutorService executorService = Executors.newFixedThreadPool(1);
        start = System.currentTimeMillis();
        Future<Integer> future = executorService.submit(() -> {
            int fibonacci = new Fibonacci().fibonacci(n);
            return fibonacci;
        });
        integer = future.get();
        end = System.currentTimeMillis();
        System.out.println("Result:" + integer + "Time Taken:" + (end - start));
        System.out.println("Executor service is done");
        executorService.shutdown();
        /*
        TODO I noticed Forkjoin is taking more time to get the fib number???
         */
    }
}

class FindFibonacci extends RecursiveTask<Integer> {
    private int fib;

    public FindFibonacci(Integer fib) {
        this.fib = fib;
    }

    @Override
    protected Integer compute() {
        if (fib <= 1)
            return fib;
        else {
            FindFibonacci fibonacci1= new FindFibonacci(fib-1);
            fibonacci1.fork();//it will create subtask
//            fibonacci1.invoke();
            FindFibonacci fibonacci2= new FindFibonacci(fib-2);
            fibonacci2.fork();
            return fibonacci2.join()+fibonacci1.join(); //join will wait till the subtask complete & get the results
        }
    }
}
//below class can't give any forkjoin advantage, runs like normal code
class Fibonacci {

    public int fibonacci(int n) {
        if (n <= 1)
            return n;
        else {
            int n1 = fibonacci(n - 1);
            int n2 = fibonacci(n - 2);
            return n1 + n2;
        }
    }
}

package com.defogTech.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAccumulator;
import java.util.concurrent.atomic.LongAdder;

public class _7E_AdderAndAccumulator {
    public static void main(String[] args) {
        /*
        AtomicLong is thread safe but when lots of threads are modifying the value, there will be contention, throughput will be less.
        For every modification data need to synchronized in core main cache. it will be slow.
         */
        AtomicLong atomicLong=new AtomicLong(0);
        /*
        Introduced in java8
            - Performance is better than AtomicLong
            - Here every thread will have its own variable, increment/decrement will be done only to thread local variable,
            so there is no need to do synchronization
            - Finally we need to call adder.sum(), in this case  synchronization happens to sum up all the values
         */
        LongAdder longAdder= new LongAdder();
        /*
        Introduced in java8
            - its generic version of Adder
            - we can do all types of operations like +/-/* etc
            - it shouldn't have any side effects( shouldn't use global variables)
         */
        LongAccumulator longAccumulator= new LongAccumulator((x,y)->x+y,0);// here y=0

        ExecutorService executorService = Executors.newFixedThreadPool(10);
        executorService.submit(new Counter(atomicLong, longAdder, longAccumulator));
        executorService.submit(new Counter(atomicLong, longAdder, longAccumulator));
        executorService.submit(new Counter(atomicLong, longAdder, longAccumulator));

        executorService.shutdown();
        ThreadUtils.sleep(1000);
        System.out.println("AtomicLong:"+atomicLong.get());
        System.out.println("LongAdder:"+longAdder.sum());
        System.out.println("LongAccumulator:"+longAccumulator.get());
    }
}

class Counter implements Runnable{
    AtomicLong atomicLong;
    LongAdder longAdder;
    LongAccumulator longAccumulator;

    public Counter(AtomicLong atomicLong, LongAdder longAdder, LongAccumulator longAccumulator) {
        this.atomicLong = atomicLong;
        this.longAdder = longAdder;
        this.longAccumulator = longAccumulator;
    }

    @Override
    public void run() {
        atomicLong.incrementAndGet();
        longAdder.increment();
        longAccumulator.accumulate(1);//here x=1
    }
}

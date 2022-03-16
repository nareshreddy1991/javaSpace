package com.naresh.m_concurrencyDefogTech;
/*
When a variable of any object is being modified by multiple threads at a time then we get data inconsitancy, because when working
in multicore system, when multiple threads running in different cores, each has its own cache, changes done in one cache will not
reflect in other. To solve this issue we can use volatile keyword, this ensures changes done in one core will be pushed to main cache
first them the same changes will be pushed to other core's local cache.

    -------------------------
    |Core 1     | core 2    |
    -------------------------
    |local cache|local cache|
    -------------------------
    |    Main cache         |
    -------------------------
In moderan system's they are synching the cache automatically, however we cant relay on the hardware.
---volatile vs Synchronized keyword
    --> Synchronized keyword ensures two things
    1) Mutual exclusion( only one thread can execute the specified block)
    2) Visibility ( Changes done by one thread will be visible to other)
So we can't use synchronized keyword always, this is expensive & only one thread is allowed to access the sync block
To solve the visibility problem we can use *volatile* keyword(We don't care about Mutual exclusion)
Ex:Both of the below works as expected
private volatile long foo;
public synchronized void setFoo(long value) {
    this.foo = value;
}
private AtomicLong foo;

TODO imp
In some cases we may only desire visibility and not atomicity. Use of synchronized in such situation is an overkill and may cause scalability problems. Here volatile comes to the rescue. Volatile variables have the visibility features of synchronized but not the atomicity features. The values of volatile variable will never be cached and all writes and reads will be done to and from the main memory. However, use of volatile is limited to very restricted set of cases as most of the times atomicity is desired. For example a simple increment statement such as x = x + 1; or x++ seems to be a single operation but is really a compound read-modify-write sequence of operations that must execute atomically.
 volatile is used to solve the visibility problem not the atomicity (X=x+1 for this operation it will not work)

 IF we declare any Object as volatile then only its reference will be in volatile memory all its members be in heap only -- Not clear
 TODO imp -- volatile is used for flag, where direct initialization is happening to variables/ not sutable for increments/decrements & compound operations
 */
public class _3A_Volatile {
    static FlagCheck check = new FlagCheck();
    public static void main(String[] args) {

        Thread t1 = new Thread(check::check);
        Thread t2 = new Thread(check::update);
        t1.start();
        t2.start();

    }
}

class FlagCheck {
    volatile boolean flag = true; //volatile is not allowed for local variables

    public void check() {
//        ThreadUtils.sleep(2);
        while (true)
            if (flag)
                System.out.println("True");
            else
                System.out.println("False");
    }

    public void update() {
        ThreadUtils.sleep(2);
        flag = false;
    }
}


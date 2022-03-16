package com.naresh.m_concurrencyDefogTech;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*
Used to solve Producer and consumer problems
Scenario - Print odd number using one thread and even numbers using some other thread
numbers should be printed in sequence

ReentrantLock's condition are exactly same as wait/notify/nofityall
wait/notify & await/signal semantics are exactly same
obj.wait()            condition.await()
obj.notify()          condition.signal()
obj.notifyAll()       condition.signalAll()


TODO important:
If a waiting Thread is not notified by calling notify() or notifyAll() on the object the said thread is waiting on,
then any one of the following may happen:

the Thread keeps waiting in the object's wait pool
the Thread becomes runnable if a timeout was specified and the time elapses
the Thread gets interrupted and becomes runnable again
the Thread wakes up for no reason at all i.e. it was neither notified nor interrupted
The last case is known as a spurious wake-up and is one of the reasons why upon wake-up a Thread should always check whether the condition it was waiting for is true or not. If not, the Thread should call and go wait() again.

if(count ==0)//dont use it
condition.await()

while(count==0) // to avoid spurious wakeup we need to do this
condition.await()

 */
public class _5B_ThreadCommunicationCondition {
    public static void main(String[] args) throws InterruptedException {
        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();//we can create any number of conditions
        EventOddPrinter2 printer = new EventOddPrinter2(lock, condition);
        Thread odd = new Thread(() -> printer.printOdd(), "Odd1");
//        Thread odd2 = new Thread(() -> printer.printOdd(), "Odd2");
        Thread even = new Thread(() -> printer.printEven(), "Even");
        odd.start();
//        odd2.start();
        even.start();
        odd.join();
//        odd2.join();
        even.join();
        System.out.println("Äll threads are done");
    }
}

class EventOddPrinter2 {
    int initial = 1;
    int n = 100;
    Lock lock;
    Condition condition;

    public EventOddPrinter2(Lock lock, Condition condition) {
        this.lock = lock;
        this.condition = condition;
    }

    public void printEven() {
        lock.lock();
        while (initial < n) {
            if (initial % 2 == 0) {
                System.out.println(Thread.currentThread().getName() + ":" + initial);
                initial++;
                condition.signal();//since more than one odd thread is in wait state we need to notify all otherwise deadlock may occur
                    /* TODO important
                   Deadlock with wait/notify: Say thread 1 enters a synchronized block on method A and then waits. Thread 2 then attempts to enter the synchronized block on method A. Thread 1 is waiting for a notify, and thread 2 is waiting on the synchronized block. Everything is now waiting. Some other thread will have to notify the object on which thread 1 is waiting. This is just one scenario that can create a deadlock. There are all kinds of ways to do it.
                    Ex: odd1, odd2, even
                    odd1:1
                    even:2
                    odd1:3 (assume odd1 & odd2 are waiting/ and even is waiting to be notified then deadlock occures)
                     */
            } else {
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        lock.unlock();
    }

    public void printOdd() {
        lock.lock();
        while (initial < n) {
            if (initial % 2 != 0) {
                System.out.println(Thread.currentThread().getName() + ":" + initial);
                initial++;
                condition.signal();
            } else {
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        lock.unlock();
    }

}

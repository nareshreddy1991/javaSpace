package com.defogTech.concurrency;

import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;
/*
    - Reentrant lock are similar to synchronized keyword(this is implicit lock)
     but Reentrant lock are accessed in different methods(we can lock in one method & unlock in other method)
    - only one lock will be available for all the threads
    - We can acquire lock multiple times in the same method & we need to unlock the same number of times useful in recursion. (Hitcount tells how many times lock is acquired)
      it will not acquire the lock because its already has the lock just hitcount will be increased.
    - Lock fairness
            new ReentrantLock(true)
            When we pass fairness as true then thread will get the lock in FIFO bases(Longest waiting thread will get the lock next)
            When we pass fairness as false(default is false), then current thread is unlocking the resource & at the same time another thread is requesting for the lock,
                now current thread will hand over the lock to newly requested thread(This is to improve the performance because new thread will ont be inserted to queue)

            Thread1 ------------ has lock  <------------- Thread 5 also trying to get the lock(Thread 1 directly handover the lock to thread 5, thread 5 will not be pun in queue)
            Queue : Thread 2| Thread 3 | Thread 4|.....( This will be run after thread 5)  This may lead  to thread starvation & this is faster & more though put
     -tryLock() -- try to get the lock, return true when it get the lock else return false(we can do something else)
     -tryLock(time) - try to get the lock with in stipulated time else return false
        tryLock() doesn't honor the lock fairness( it can get the lock directly from unlocking thread)
        tryLock(0, TimeUnits.Millios) -- this will honor the fairness
     -- Few other methods
            isHeldByCurrentThread
            getQueueLength
            newCondition(see in the next programs)

    Difference between Synchronized vs ReentrantLock
1) Another significant difference between ReentrantLock and synchronized keyword is fairness. synchronized keyword doesn't support fairness. Any thread can acquire lock once released, no preference can be specified, on the other hand you can make ReentrantLock fair by specifying fairness property, while creating instance of ReentrantLock. Fairness property provides lock to longest waiting thread, in case of contention.
2) Second difference between synchronized and Reentrant lock is tryLock() method. ReentrantLock provides convenient tryLock() method, which acquires lock only if its available or not held by any other thread. This reduce blocking of thread waiting for lock in Java application.
3) One more worth noting difference between ReentrantLock and synchronized keyword in Java is, ability to interrupt Thread while waiting for Lock. In case of synchronized keyword, a thread can be blocked waiting for lock, for an indefinite period of time and there was no way to control that. ReentrantLock provides a method called lockInterruptibly(), which can be used to interrupt thread when it is waiting for lock. Similarly tryLock() with timeout can be used to timeout if lock is not available in certain time period.
4) ReentrantLock also provides convenient method to get List of all threads waiting for lock.

 */
public class _5A_ReentrantLock {
    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        BookMyShow bookMyShow = new BookMyShow();
        IntStream.rangeClosed(1, 20).forEach(i -> {
            new Thread(new BookMyTicket(lock, bookMyShow)).start();
        });
    }
}

class BookMyTicket implements Runnable {

    Lock lock;
    BookMyShow bookMyShow;

    public BookMyTicket(Lock lock, BookMyShow bookMyShow) {
        this.lock = lock;
        this.bookMyShow = bookMyShow;
    }

    @Override
    public void run() {
        try {
            lock.lock();
            ThreadUtils.sleep(2);
            Integer integer = bookMyShow.bookSeat();
            System.out.println(Thread.currentThread().getName() + "Ticket Number:" + integer);
        } catch (Exception e) {//always call unlock in finally block, otherwise if any exception lock will never be released
            e.printStackTrace();
        } finally {
            lock.unlock();
        }


    }
}

class BookMyShow {
    private int totalNoOfTickets = 10;

    public List<Integer> getAvailableTickets() {
        return null;
    }

    public Integer bookSeat() {
        if (totalNoOfTickets > 0)
            return totalNoOfTickets--;
        else
            return -1;
    }
}

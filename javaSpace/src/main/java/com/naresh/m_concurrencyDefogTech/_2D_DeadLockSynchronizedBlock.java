package com.naresh.m_concurrencyDefogTech;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.util.ArrayList;
import java.util.List;

/*
Deadlock can occur in a situation when a thread is waiting for an object lock, that is acquired by another thread and second thread is waiting for an object lock
that is acquired by first thread.Since, both threads are waiting for each other to release the lock, the condition is called deadlock.
How to avoid deadlock
    - avoid nested locks
    - avoid unwanted locks
    - acquire object lock in the same order in all threads
    - try to have timeouts while getting locks
        lock.tryLock(2, TimeUnits.SECONDS)// if thread is not able to get lock in 2sec then it will return false, you can proceed accordingly
//TODO deadlock can occur in the below code
class AccountExample{
    private void transfer(Account acc1, Account acc2, int amount){
    //below line can avoid deadlock
         Account acc1 = getLarger(from, to);
        Account acc2= getSmaller(from, to);
     //the above code can avoid the deadlock
        synchronized (acc1){
            synchronized (acc2){
                acc1.deduct(amount);
                acc2.add(amount);
            }
        }
    }
}
Two threads are trying to access same method by passing two account objs in different order, it can leads to deadlock
public void execute(){
    new Thread(this::transfer(john, marie, 100)).start();
    new Thread(this::transfer(marie, john, 100)).start();
}
**********************
Getting thread dump:
1) Using jstack (works in windows & linux)
    Step:1) run jps command to list all the java processes & take the pid
    step:2) run jstack pid > out.txt
 2)using jvisualvm also we see the thread dump(Tested in windows), it will come with jdk untill 1.8 for later version we need to download it separately.
3) we can find deadlock using MxBean class -- see at the end
 */
public class _2D_DeadLockSynchronizedBlock {
    static BookTicket3 bookTicket = new BookTicket3();//common resource for all the threads
    static BookPopcorn3 popcorn = new BookPopcorn3();

    public static void main(String[] args) throws InterruptedException {
        List<Thread> threadList = new ArrayList<>();
        Thread t1 = new Thread(new BookingAgent3(bookTicket, popcorn));
        Thread t2 = new Thread(new BookingAgent4(bookTicket, popcorn));
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("Available seats:" + bookTicket.count());
        System.out.println("Available popcorns:" + popcorn.count());
    }


}

class BookingAgent3 implements Runnable {

    BookTicket3 bookTicket;
    BookPopcorn3 popcorn;

    public BookingAgent3(BookTicket3 bookTicket, BookPopcorn3 popcorn) {
        this.bookTicket = bookTicket;
        this.popcorn = popcorn;
    }

    @Override
    public void run() {
        System.out.println("Agent3 started for" + Thread.currentThread().getName());
        synchronized (bookTicket) {
            ThreadUtils.sleep(2);
            synchronized (popcorn) {
                int seatNo = bookTicket.book();
                String popcornToken = this.popcorn.bookPopcorn(seatNo);
                System.out.println("Ticket is booked by " + Thread.currentThread().getName() + "Seat no:" + seatNo + "Popcorn token:" + popcornToken);
            }
        }
    }
}

class BookingAgent4 implements Runnable {

    BookTicket3 bookTicket;
    BookPopcorn3 popcorn;

    public BookingAgent4(BookTicket3 bookTicket, BookPopcorn3 popcorn) {
        this.bookTicket = bookTicket;
        this.popcorn = popcorn;
    }

    @Override
    public void run() {
        System.out.println("Agent4 started for" + Thread.currentThread().getName());
        synchronized (popcorn) {
            ThreadUtils.sleep(2);
            synchronized (bookTicket) {// change the order of acquiring the lock to solve the deadlock issue
                int seatNo = bookTicket.book();
                String popcornToken = this.popcorn.bookPopcorn(seatNo);
                System.out.println("Ticket is booked by " + Thread.currentThread().getName() + "Seat no:" + seatNo + "Popcorn token:" + popcornToken);
            }
        }
    }
}

class BookTicket3 {
    private static int totalTickets = 5;

    public int book() {
        if (totalTickets > 0) {
            ThreadUtils.sleep(5);
            return totalTickets--;
        }
        return -1;
    }

    public int count() {
        return totalTickets;
    }
}

class BookPopcorn3 {
    private static int popCornCount = 5;

    public String bookPopcorn(int seatNo) {
        if (seatNo < 0)
            return null;
        popCornCount--;
        return "POP" + seatNo;
    }

    public int count() {
        return popCornCount;
    }
}

//we can find deadlock using below classes
class DeadLockFinder {
    public DeadLockFinder() {
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();//TODO what is MXbeans?
        long[] threadIds = threadMXBean.findDeadlockedThreads();
        ThreadInfo[] threadInfo = threadMXBean.getThreadInfo(threadIds);
        for (ThreadInfo info : threadInfo) {
            //
        }
    }
}


package com.defogTech.concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/*
Deadlock can occur in a situation when a thread is waiting for an object lock, that is acquired by another thread and second thread is waiting for an object lock
that is acquired by first thread.Since, both threads are waiting for each other to release the lock, the condition is called deadlock.
How to avoid deadlock
    - avoid nested locks
    - avoid unwanted locks
    - acquire object lock in the same order in all threads
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
        t1.join();t2.join();
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
        System.out.println("Agent3 started for"+Thread.currentThread().getName());
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
        System.out.println("Agent4 started for"+Thread.currentThread().getName());
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


package com.defogTech.concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/*
synchronized blocks are better than Synchronized methods
its has less scope, we are locking the part of method, so performance is good
 */
public class _2B_SynchronizedBlock {
    static BookTicket1 bookTicket = new BookTicket1();//common resource for all the threads
    static BookTicket1 bookTicket2 = new BookTicket1();//common resource for all the threads
    static BookPopcorn popcorn = new BookPopcorn();

    public static void main(String[] args) throws InterruptedException {
        List<Thread> threadList = new ArrayList<>();
        IntStream.rangeClosed(1, 6).forEach(i -> {
            Thread thread = new Thread(new BookingAgent(bookTicket,popcorn));
            threadList.add(thread);
            thread.start();
        });
        threadList.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println("Available seats:" + bookTicket.count());
        System.out.println("Available popcorns:" + popcorn.count());
    }


}

class BookingAgent implements Runnable {

    final BookTicket1 bookTicket;
    final BookPopcorn popcorn;

    public BookingAgent(BookTicket1 bookTicket, BookPopcorn popcorn) {
        this.bookTicket = bookTicket;
        this.popcorn = popcorn;
    }

    @Override
    public void run() {
        synchronized (bookTicket) {//only one thread get the lock of this object
            synchronized (popcorn) {//once the thread get lock for bookTicket then acquired the lock for popcorn
//            synchronized (bookTicket2) {//any other thread still can get lock of bookTicket and update it - so it will not work
//            synchronized (this) {//this will not work, because lock will be acquired on bookingAgent, we have different instance of it
                int seatNo = bookTicket.book();
                String popcornToken = this.popcorn.bookPopcorn(seatNo);
                System.out.println("Ticket is booked by " + Thread.currentThread().getName() + "Seat no:" + seatNo + "Popcorn token:" + popcornToken);
            }
        }
    }
}

class BookTicket1 {
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

class BookPopcorn {
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


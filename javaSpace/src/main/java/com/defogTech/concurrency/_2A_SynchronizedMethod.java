package com.defogTech.concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;
/*
Object level locking, for one object only one thread can acquire the lock at a time.
If we have multiple instance of same object then two threads can execute the same methods on two different objects
 */
public class _2A_SynchronizedMethod {
    static BookTicket bookTicket = new BookTicket();//common resource for all the threads

    public static void main(String[] args)  {
        List<Thread> threadList = new ArrayList<>();
        IntStream.rangeClosed(1, 6).forEach(i -> {
            Thread thread = new Thread(new BookingAgent());
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
    }

    static class BookingAgent implements Runnable {

        @Override
        public synchronized void run() {//we cant synchronized run method, it has no effect
            int seatNo = bookTicket.book();
            System.out.println("Ticket is booked by " + Thread.currentThread().getName() + "Seat no:" + seatNo);
        }
    }


}

class BookTicket {
    private static int totalTickets = 5;

    public synchronized int book() {//object will be locked by current running thread
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




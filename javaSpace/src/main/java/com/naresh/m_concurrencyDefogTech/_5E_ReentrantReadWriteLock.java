package com.naresh.m_concurrencyDefogTech;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.stream.Collectors;

/*
    - ReentrantLock => one thread at a time
    - ReentrantReadWriteLock => multiple read threads(multiple read threads can acquire lock at a time) at a time
                                or one write lock at a time
                              => though we have two lock read lock & write lock only one will allowed at a time
    - Faire ness
        when true it will follow FIFO
        TODO when false, not sure??
 */
public class _5E_ReentrantReadWriteLock {
    public static void main(String[] args) {
        Bookings bookings= new Bookings();
        Thread t1= new Thread(()-> System.out.println("Available slots:"+bookings.getAvailableSlots()));
        Thread t2= new Thread(()-> System.out.println("Available slots:"+bookings.getAvailableSlots()));
        Thread t3= new Thread(()-> System.out.println("Available slots:"+bookings.getAvailableSlots()));
        Thread t4= new Thread(()-> System.out.println("Booked slot"+bookings.bookSlot()));
        Thread t5= new Thread(()-> System.out.println("Booked slot"+bookings.bookSlot()));

        t1.start();t2.start();t3.start();
        t5.start();t4.start();
    }
}

class Bookings {
    private List<String> slotsList = new ArrayList<>();
    ReentrantReadWriteLock readWriteLock;
    ReentrantReadWriteLock.ReadLock readLock;
    ReentrantReadWriteLock.WriteLock writeLock;

    Bookings() {
        readWriteLock = new ReentrantReadWriteLock(false);
        readLock = readWriteLock.readLock();
        writeLock = readWriteLock.writeLock();
        slotsList.addAll(Arrays.asList("S1", "S2", "S3", "S4"));
    }

    public String getAvailableSlots() {
        ThreadUtils.sleep(100);
        readLock.lock();
        System.out.println(Thread.currentThread().getName() + " ReadLock acquired");
        String collect = slotsList.stream().collect(Collectors.joining());
        System.out.println(Thread.currentThread().getName() + " ReadLock released");
        readLock.unlock();
        return collect;
    }

    public String bookSlot() {
        ThreadUtils.sleep(100);
        writeLock.lock();
        System.out.println(Thread.currentThread().getName() + " Writelock acquired");
        String remove = slotsList.remove(slotsList.size() - 1);
        System.out.println(Thread.currentThread().getName() + " Writelock released");
        writeLock.unlock();
        return remove;
    }

}

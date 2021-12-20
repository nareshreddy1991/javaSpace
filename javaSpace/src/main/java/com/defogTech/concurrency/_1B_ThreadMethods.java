package com.defogTech.concurrency;

public class _1B_ThreadMethods {
    public static void main(String[] args) {
        Thread t1= new Thread(()-> {
            ThreadUtils.sleep(500);
            System.out.println("Thread is running...");
        });
        t1.start();

        if (t1.isAlive()) {
            System.out.println("Thread is alive");
        }
        t1.setName("T1");
        t1.setPriority(6);//default is 5 , min is 1, max 10
        System.out.println("Count:"+t1.getThreadGroup().activeCount());
        if (t1.isDaemon()) {
            System.out.println("Deamon thread");
        }else{
            System.out.println("Not a Deamon thread");
        }
        System.out.println("Id"+t1.getId());

    }
}


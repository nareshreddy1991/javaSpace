package com.naresh.m_concurrencyDefogTech;

import java.text.SimpleDateFormat;
import java.util.stream.IntStream;

/*
This class provides thread local variable. These variables differ from their normal counterparts in that each thread that accesses one (via its get or set method) has its own, independently initialized copy of the variable.

Basically it is an another way to achieve thread safety apart from writing immutable classes.
Since Object is no more shared there is no requirement of Synchronization which can improve scalability and performance of application.
It extends class Object.
ThreadLocal provides thread restriction which is extension of local variable. ThreadLocal are visible only in single thread.
 No two thread can see each others thread local variable.
These variable are generally private static field in classes and maintain its state inside thread.

TODO Use Case 1:
Lets say there is a executor service with 10 threads & there are 1000 task to be executed, every task is having instance of SimpleDateFormat obj.
This design is not good, because too many objects of SDF is created, if we declare SDF as global variable then its leads to data intigrity issue because SDF is not thread safe.
So, we should eat up more memory & should not cause data intigrity issue, in this case we can use ThreadLocal, since we have 10 threads only 10 SDF objects will be created.
TODO User case 2:( per thread context)
WHen some thread is running in different service layers and it has to access some common data then ThreadLocal will be useful
Thread can insert some data in one layer and get it in some other layer

TODO make sure we clean the threadLocal when we are done otherwise it may leads to memory leak.

ex: spring is using this concept and provided many context classes like
LocaleContextHolder
RequestContextHolder
SecurityContextHolder
 */
public class _3C_ThreadLocal {
    public static void main(String[] args) {
        ThreadLocal<Integer> threadLocal = new ThreadLocal<>();
        Thread t1 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " initial value:" + threadLocal.get());
            threadLocal.set(100);
            ThreadUtils.sleep(5);
            System.out.println("From ThreadLocal:" + threadLocal.get());
        });
        Thread t2 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " initial value:" + threadLocal.get());
            threadLocal.set(200);
            ThreadUtils.sleep(5);
            System.out.println("From ThreadLocal:" + threadLocal.get());
        });
        t1.start();
        t2.start();
        ThreadUtils.sleep(10);
        //from java 8
        ThreadLocal<String> strThreadLocal = ThreadLocal.withInitial(() -> "Some initial Value");
        Thread t3 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " initial value:" + strThreadLocal.get());
            strThreadLocal.set("Naresh");
            ThreadUtils.sleep(5);
            System.out.println("From ThreadLocal:" + strThreadLocal.get());
        });
        Thread t4 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " initial value:" + strThreadLocal.get());
            strThreadLocal.set("Chareesh");
            ThreadUtils.sleep(5);
            System.out.println("From ThreadLocal:" + strThreadLocal.get());
        });
        t3.start();
        t4.start();

        ThreadLocal<SimpleDateFormat> simpleDateFormatThreadLocal = ThreadLocal.withInitial(() -> new SimpleDateFormat("dd-MMM-YYY"));
        //TODO When SDF pattern is same, even if you create multiple objects all will have the same memory - explore ?? SDF thread safe?
//        ThreadLocal<SimpleDateFormat> simpleDateFormatThreadLocal= new ThreadLocal(){
//
//            @Override
//            public SimpleDateFormat initialValue(){
//            SimpleDateFormat sdf= new SimpleDateFormat("dd-MMM-YYY");
//                System.out.println("From IntialValue method:"+sdf);
//                return sdf;
//            }
//        };

        IntStream.rangeClosed(1, 3).forEach(i -> {
            //TODO the idea is, every thread should get its own copy of SDF but thats not happening in SDF becuase when pattern is same its giving the same instance
            Thread t5 = new Thread(() -> {//Since we are creating the threads for every new thread one SDF will be created,
                // when we use executor service then SDF will be created per thread rather than per task
                System.out.println(Thread.currentThread().getName() + "From ThreadLocal:" + simpleDateFormatThreadLocal.get());
            });
            t5.start();
        });
        ThreadUtils.sleep(1000);
        ThreadLocal<Dummy> dummyThreadLocal = ThreadLocal.withInitial(() -> new Dummy());
        IntStream.rangeClosed(1, 3).forEach(i -> {
            Thread t = new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "From Dummy ThreadLocal:" + dummyThreadLocal.get());
                System.out.println(Thread.currentThread().getName() + "From Dummy ThreadLocal:" + dummyThreadLocal.get());
                //when we call get method for first time it will call the initialvalue method and get the new object
                //from second time the same value will be returned
            });
            t.start();
        });
    }

    //
}

class Dummy {
}

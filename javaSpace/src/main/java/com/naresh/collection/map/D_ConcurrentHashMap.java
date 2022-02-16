package com.naresh.collection.map;

import java.util.concurrent.ConcurrentHashMap;

/*
public class ConcurrentHashMap<K,V> extends AbstractMap<K,V>
    implements ConcurrentMap<K,V>, Serializable

    - Thread safe version of HashMap
    - no null are allowed
    - It internally use HashTable
    - At a time any number of threads can perform read operation with out locking the object
    - In ConcurrentHashMap, the Object is divided into a number of segments according to the concurrency level.
    - The default concurrency-level of ConcurrentHashMap is 16.
    - In ConcurrentHashMap, at a time any number of threads can perform retrieval operation but for updated in the object,
the thread must lock the particular segment in which the thread wants to operate. This type of locking mechanism is known
as Segment locking or bucket locking. Hence at a time, 16 update operations can be performed by threads.

Concurrency-Level: It is the number of threads concurrently updating the map. The implementation performs internal sizing to try to accommodate this many threads.

 */
public class D_ConcurrentHashMap {
    public static void main(String[] args) {
        ConcurrentHashMap<String, Integer> concurrentHashMap= new ConcurrentHashMap<>();
        ConcurrentHashMap<String, Integer> concurrentHashMap2= new ConcurrentHashMap<>(25);
        ConcurrentHashMap<String, Integer> concurrentHashMap3= new ConcurrentHashMap<>(25,0.75f);
        ConcurrentHashMap<String, Integer> concurrentHashMap4= new ConcurrentHashMap<>(25,0.75f,16);//concurrency level
    }
}

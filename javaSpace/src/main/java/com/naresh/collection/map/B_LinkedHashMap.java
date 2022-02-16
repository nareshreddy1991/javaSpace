package com.naresh.collection.map;

import java.util.LinkedHashMap;

/*
public class LinkedHashMap<K,V>
    extends HashMap<K,V>
    implements Map<K,V>

    - maintain insertion order
    - it internally user double linked linked list

 */
public class B_LinkedHashMap {
    public static void main(String[] args) {
        LinkedHashMap<String, Integer> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put("key1", 30);
        linkedHashMap.put("key2", 10);
        linkedHashMap.put("key3", 20);

        System.out.println(linkedHashMap);
    }
}

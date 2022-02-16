package com.naresh.collection.map;

import com.sun.security.jgss.GSSUtil;

import java.util.*;

/*
public class TreeMap<K,V>
    extends AbstractMap<K,V>
    implements NavigableMap<K,V>, Cloneable, java.io.Serializable

    --maintain sorted order based on keys, so classes should implement Comparable
 */
public class C_TreeMap {
    public static void main(String[] args) {
        SortedMap<String, Integer> treeMap = new TreeMap<>(Comparator.naturalOrder());
        treeMap.put("key2", 10);
        treeMap.put("key3", 20);
        treeMap.put("key1", 30);
        treeMap.put("key4", 40);

        System.out.println(treeMap);

        System.out.println(treeMap.firstKey());
        System.out.println(treeMap.lastKey());
        System.out.println(treeMap.headMap("key2")); //<key2
        System.out.println(treeMap.tailMap("key2")); //<=key2
        treeMap.subMap("key2", "key4");//key2 to key4
        System.out.println(treeMap);
        //
        System.out.println("navigable map");
        NavigableMap<String, Integer> navigableMap = (NavigableMap<String, Integer>) treeMap;
        System.out.println(navigableMap.ceilingKey("key4"));//>=key4
        System.out.println(navigableMap.floorKey("key1")); //<=key1
        System.out.println(navigableMap.higherKey("key1"));//>key1
        System.out.println(navigableMap.lowerKey("key1"));//<key1
        System.out.println(navigableMap.ceilingEntry("key1"));//it gives Map.Entry<K,V>, we four methods like this
        System.out.println(navigableMap);
        System.out.println(navigableMap.pollFirstEntry());//remove & return Map.Entry
        System.out.println(navigableMap.pollLastEntry());
        System.out.println(navigableMap);

        NavigableMap<String, Integer> navigableMap1 = navigableMap.descendingMap();
        System.out.println(navigableMap1);
        NavigableSet<String> strings = navigableMap.descendingKeySet();
        System.out.println(strings);

    }
}

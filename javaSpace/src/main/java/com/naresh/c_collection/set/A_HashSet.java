package com.naresh.c_collection.set;

import java.util.*;

/*
Set:
public interface Set<E> extends Collection<E>
    - no duplicate values
    - only one null value is allowed
    - unordered
HashSet:
public class HashSet<E>
    extends AbstractSet<E>
    implements Set<E>, Cloneable, java.io.Serializable


    - Internally data will be stored in HashMap in the form of keys(that internally use HashTable)
    - unorderd
    - not thread safe, use Collections.synchrnizedSet(set);
    -default initial capacity (16) and load factor (0.75)
    -iterating over HashSet requires time proportional to the sum of the HashSet instance’s size (the number of elements)
plus the “capacity” of the backing HashMap instance (the number of buckets). Thus, it’s very important not to set the initial
capacity too high (or the load factor too low) if iteration performance is important.

LinkedHashSet
    - It internally use LinkedHashMap(that internally use double linked list to maintain the order)
    - insertion order will be maintained
*******************
Initial Capacity: The initial capacity means the number of buckets when hashtable (HashSet internally uses hashtable data structure) is created.
The number of buckets will be automatically increased if the current size gets full.

Load Factor: The load factor is a measure of how full the HashSet is allowed to get before its capacity is automatically increased.
When the number of entries in the hash table exceeds the product of the load factor and the current capacity,
the hash table is rehashed (that is, internal data structures are rebuilt) so that the hash table has approximately twice the number of buckets.
                  Number of stored elements in the table
Load Factor = -----------------------------------------
                        Size of the hash table
Effect on performance:  Load factor and initial capacity are two main factors that affect the performance of HashSet operations.
A load factor of 0.75 provides very effective performance with respect to time and space complexity. If we increase the load factor value more than that then
 memory overhead will be reduced (because it will decrease internal rebuilding operation) but, it will affect the add and search operation in the hashtable.
  To reduce the rehashing operation we should choose initial capacity wisely. If the initial capacity is greater than the maximum number of entries divided
   by the load factor, no rehash operation will ever occur.

********************
THe classes mush override equals & hashcode methods
If two objects are same they have same hashcode
If two objects are different they might have same hashcode
**********************
Time Complexity of HashSet Operations: The underlying data structure for HashSet is hashtable. So amortize (average or usual case) time complexity fo
 add, remove and look-up (contains method) operation of HashSet takes O(1) time.
 */
public class A_HashSet {
    public static void main(String[] args) {
        Set<Integer> a = new HashSet<Integer>();

        // Adding all elements to List
        a.addAll(Arrays.asList(
                new Integer[]{1, 3, 2, 4, 8, 9, 0}));

        // Again declaring object of Set class
        // with reference to HashSet
        Set<Integer> b = new HashSet<Integer>();

        b.addAll(Arrays.asList(
                new Integer[]{1, 3, 7, 5, 4, 0, 7, 5}));


        // To find union
        Set<Integer> union = new HashSet<Integer>(a);
        union.addAll(b);
        System.out.print("Union of the two Set");
        System.out.println(union);

        // To find intersection - common in both the collection
        Set<Integer> intersection = new HashSet<Integer>(a);
        intersection.retainAll(b);
        System.out.print("Intersection of the two Set");
        System.out.println(intersection);

        // To find the symmetric difference
        Set<Integer> difference = new HashSet<Integer>(a);
        difference.removeAll(b);
        System.out.print("Difference of the two Set");
        System.out.println(difference);

        //enum set
        // THis is much faster than
        Set set1 = EnumSet.of(Gfg.QUIZ, Gfg.CONTRIBUTE,
                Gfg.LEARN, Gfg.CODE);
        set1.add(Gfg.CODE);
//        set1.add(ABC.ABC); //we cant add any other type enums


        //TODo
        LinkedHashSet<Integer> linkedSet= new LinkedHashSet<>();
        boolean add = linkedSet.add(10);//TRUE
        boolean add2 = linkedSet.add(10);//FALSE THIS VALUE WILL NOT BE INSERTED

        Iterator<Integer> iterator = linkedSet.iterator();

        //todo java8 methods
        linkedSet.removeIf(e->e<3);
    }
}
enum Gfg { CODE, LEARN, CONTRIBUTE, QUIZ, MCQ };
enum ABC{ABC;}
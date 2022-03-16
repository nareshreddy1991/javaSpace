package com.naresh.c_collection.set;

import java.util.Iterator;
import java.util.NavigableSet;
import java.util.TreeSet;

/*
NavigableSet:
public interface NavigableSet<E> extends SortedSet<E>
    - It behaves like a SortedSet with the exception that we have navigation methods available in addition to the sorting mechanisms of the SortedSet.
    - For example, the NavigableSet interface can navigate the set in reverse order compared to the order defined in SortedSet.
A NavigableSet may be accessed and traversed in either ascending or descending order.
 */

public class C_TreeSet {
    public static void main(String[] args) {
        NavigableSet<Integer> ns = new TreeSet<>();
        ns.add(1);
        ns.add(0);
        ns.add(3);
        ns.add(2);
        ns.add(5);
        ns.add(4);
        ns.add(6);

        // Get a reverse view of the navigable set
        NavigableSet<Integer> reverseNs = ns.descendingSet();

        // Print the normal and reverse views
        System.out.println("Normal order: " + ns);
        System.out.println("Reverse order: " + reverseNs);

        //iterating in the reverse order
        Iterator<Integer> integerIterator = ns.descendingIterator();
        while (integerIterator.hasNext())
            System.out.println(integerIterator.next());

        NavigableSet<Integer> threeOrMore = ns.tailSet(3, true);
        NavigableSet<Integer> headSet = ns.headSet(3, true);//overloaded methods available in this cass
        System.out.println("3 or  more:  " + threeOrMore);
        System.out.println("lower(3): " + ns.lower(3));
        System.out.println("floor(3): " + ns.floor(3));
        System.out.println("higher(3): " + ns.higher(3));
        System.out.println("ceiling(3): " + ns.ceiling(3));

        System.out.println("pollFirst(): " + ns.pollFirst());
        System.out.println("Navigable Set:  " + ns);

        System.out.println("pollLast(): " + ns.pollLast());
        System.out.println("Navigable Set:  " + ns);

        System.out.println("pollFirst(): " + ns.pollFirst());
        System.out.println("Navigable Set:  " + ns);

        System.out.println("pollFirst(): " + ns.pollFirst());
        System.out.println("Navigable Set:  " + ns);

        System.out.println("pollFirst(): " + ns.pollFirst());
        System.out.println("Navigable Set:  " + ns);

        System.out.println("pollFirst(): " + ns.pollFirst());
        System.out.println("pollLast(): " + ns.pollLast());


    }
}


package com.naresh.collection.list;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Spliterator;
import java.util.stream.Stream;

public class A_IteratorListIterator {
    public static void main(String[] args) {
        //TODO Iterator
        ArrayList<Integer> list0 = new ArrayList<>();
        list0.add(10);
        list0.add(20);
        Iterator<Integer> iterator0 = list0.iterator();
//        list0.add(30); //after getting the iterator if you add element it will throw ConcurrentMException
        while (iterator0.hasNext()) {
            System.out.print(iterator0.next());
//            list0.add(1); //throws ConcurrentModificationException
        }
        System.out.println("above results are");


        ArrayList<Integer> list = new ArrayList<>();
        Stream.iterate(0, i -> i < 25, i -> ++i)
                .forEach(i -> {
                    list.add(i);
                });

        Iterator<Integer> iterator = list.iterator();
        int k = 0;
        while (iterator.hasNext()) {
//            if(k==10) break; ++i;
            System.out.print(iterator.next());// after calling  next pointer moves to next element
//            iterator.remove();// remove should be called after next()
        }
        System.out.println("list:" + list);
        System.out.println("printing remaining elements from iterator");
        iterator.forEachRemaining(System.out::print);//print remaining elements thats need to be iterate
        //if iterator iterates all the elements above methods dont do anything
        //After this operation curser moved to end

        //TODO List Iterator
        System.out.println("List Iterator");
        ArrayList<Integer> list2 = new ArrayList<>();
        Stream.iterate(0, i -> i < 25, i -> ++i)
                .forEach(i -> {
                    list2.add(i);
                });

        ListIterator<Integer> listIterator = list2.listIterator();
        int p = 0;
        while (listIterator.hasNext()) {
            if (p == 10) break;
            ++p;
            System.out.print(listIterator.next());// after calling  next pointer moves to next element
            listIterator.remove();// remove should be called after next()
            listIterator.add(99);
//            listIterator.set(-1); //we can replace the element
//            listIterator.nextIndex();
        }
        System.out.println("list:" + list2);
        System.out.println("printing remaining elements from iterator");
//        listIterator.forEachRemaining(System.out::print);//print remaining elements thats need to be iterate
        //if iterator iterates all the elements above methods dont do anything

        System.out.println("Iterating in reverse order");
        int q = 0;
        while (listIterator.hasPrevious()) {
            if (q == 5) break;
            q++;
            listIterator.previous();
            listIterator.set(-2);
//            listIterator.previousIndex();
        }
        System.out.println("list:" + list2);
        listIterator.forEachRemaining(System.out::print);// from the current position print all the elements to right


    }
}

package com.naresh.c_collection;

import java.util.*;

public class CollectionsExample {
    public static void main(String[] args) throws Exception{
        List<Cake> cakeList = new ArrayList<>();
        Collections.sort(cakeList);// this will only classes implements Comparable not Comparator
        Collections.sort(cakeList, Comparator.naturalOrder());

//        List<Integer> list = List.of(2, 3, 5);
//        Collections.sort(list);//cant sort unmodifiable  list

        List<Integer> list2 = Arrays.asList(2, 5, 46, 8, 5, 42, 5, 48, 5);
        list2.sort(Comparator.naturalOrder());
        int i = Collections.binarySearch(list2, 5);
        System.out.println("binary:"+i);

        List<Integer> safeList = Collections.synchronizedList(list2);//return thread safe list
        if(safeList == list2)
            System.out.println("same as before");
        else
            System.out.println("Changed");
      /*  Collections.synchronizedCollection(null);
        Collections.synchronizedSet(null);
        Collections.synchronizedNavigableSet(null);
        Collections.synchronizedMap(null);
        Collections.synchronizedNavigableMap(null);*/

//        Collections.unmodifiableList(null); //multiple methods are there like above
        List<Integer> dest= new ArrayList<>();
        //source & dest can be same
//        List<Integer> clone = (List<Integer>)list2.clone(); //we cant clone becuase clone is protected access in Object
        // dest.size >= source.size
        Collections.copy(list2, list2);// desk, source - throw IndexOutOfBound because dest size is 0

        //fill all elements with 0, if dest size is 0 nothing happens
        Collections.fill(dest,0);
        System.out.println("fill"+dest);

        List<Object> l1 = List.of(5,5,7,6);
        List<Object> l2 = List.of(1,2,3,4);
        boolean disjoint = Collections.disjoint(l1, l2);
        System.out.println("disjoint:"+disjoint);//return true if no common elements

        List<Integer> list = List.of(5, 4, 8, 5, 6, 3, 25, 4, 7, 8,6, 3, 25, 4, 7, 8);
        List<Integer> list21 = List.of(6, 3, 25);
        int i1 = Collections.indexOfSubList(list, list21);
        System.out.println("sublist position"+i1);
        int i2 = Collections.lastIndexOfSubList(list, list21);
        System.out.println("sublist of position lastindex"+i2);

        List<Integer> list1 = Arrays.asList(5, 4, 8, 5, 6, 3, 25,5,5,5);
        Collections.swap(list1,0,1);
        System.out.println("after swap"+list1);

//        Collections.addAll(null,1,2,5,4,8,7,5); //we can add all these eleemnts to collection

        Collections.replaceAll(list1,5,55);
        System.out.println("After replace:"+list1);
    }
}

class Cake implements Comparable<Cake> {
    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cake cake = (Cake) o;
        return Objects.equals(name, cake.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public int compareTo(Cake o) {
        return this.getName().compareTo(o.getName());
    }
}

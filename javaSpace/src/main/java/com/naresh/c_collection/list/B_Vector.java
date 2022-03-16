package com.naresh.c_collection.list;

import java.util.Vector;
import java.util.stream.Stream;

/*
public class Vector<E>
    extends AbstractList<E>
    implements List<E>, RandomAccess, Cloneable, java.io.Serializable

Vector:
    - It internally store the data in Object[]
    - it is thread safe, all the methods in vector are synchronized
    - initial capacity is 10 (when object is created with empty constructor)
    - capacity will be increased by 100% when adding additional element
    - It is legacy class, however these are reengineered in 1.5 to support collection framework(these are not deprecated)
    - Since it is thread safe, its slow in the performance
    - We can iterate through Iterator & Enumurator
 */
public class B_Vector {
    public static void main(String[] args) {
        Vector<Integer> vector = new Vector<>();
        System.out.println("Capacity:" + vector.capacity());//ArrayList doesn't have this list
        Stream.iterate(0, i -> i < 11, i -> ++i) //i++ will leads to infinite loop / we can use i=i+1
                .forEach(i -> {
                    vector.add(i);
                });
        System.out.println("Capacity:" + vector.capacity());

        vector.addElement(10); //same as add(E)
        vector.insertElementAt(10, 11);
        vector.setElementAt(5, 10);
        vector.firstElement();
        vector.lastElement();
        //and all method of List
        vector.trimToSize();
        vector.get(0);
    }
}

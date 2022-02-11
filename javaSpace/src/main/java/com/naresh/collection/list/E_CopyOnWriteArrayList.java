package com.naresh.collection.list;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/*
public class CopyOnWriteArrayList<E>
    implements List<E>, RandomAccess, Cloneable, java.io.Serializable

CopyOnWriteArrayList
    - Similar to ArrayList
    - All modification operations(add, set, remove etc) are done after making the fresh copy of the array, so reading threads
will not be affected.TODO it will not leads to data consistency issue?
    - It is a thread safe version of ArrayList(only modification methods are thread safe)
    - The main important point about CopyOnWriteArrayList is the Iterator of CopyOnWriteArrayList can not perform remove operation
otherwise we get Run-time exception saying UnsupportedOperationException. add() and set() methods on CopyOnWriteArrayList iterator also throws UnsupportedOperationException.
Also Iterator of CopyOnWriteArrayList will never throw ConcurrentModificationException.

How this array is thread safe?
https://stackoverflow.com/questions/2950871/how-can-copyonwritearraylist-be-thread-safe

 */
public class E_CopyOnWriteArrayList {
    public static void main(String[] args) {
        CopyOnWriteArrayList<Integer> copyArray = new CopyOnWriteArrayList<>();
        copyArray.addIfAbsent(10);
//        copyArray.addAllAbsent(null);

        //TODO iterator works little differently
        Iterator<Integer> iterator = copyArray.iterator();
        copyArray.add(20);//it will not throw ConcurrentMException, but element will be ignored
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
//            iterator.remove(); //unsupported operation Exception will be thrown
//            iterator.add(5); //add set these methods doesn't exists
            copyArray.add(25);// it will not throw ConcurrentModificationException
        }
        System.out.println(copyArray);


    }
}

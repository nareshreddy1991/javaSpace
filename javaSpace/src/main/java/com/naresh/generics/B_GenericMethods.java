package com.naresh.generics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/*
Generics:
The Java Generics programming is introduced in J2SE 5 to deal with type-safe objects. It makes the code stable by detecting the bugs at compile time.
Before generics, we can store any type of objects in the collection, i.e., non-generic. Now generics force the java programmer to store a specific type of objects.
Advantages:
    - Type Safety
    - Type Casting not required
    - Compile time checking
****************************

 ***************************
 */

public class B_GenericMethods {

    public void withCollection() {
        List list = new ArrayList();
        list.add(12);
        list.add("Cherry");

        Iterator iterator = list.iterator();
        while (iterator.hasNext()) {
            Object next = iterator.next();//we only get object, every time we need to cast it to specific type, with generics this pain is gone
        }
    }

    /*
    TODO Generic Methods Syntax:
     public <T> List<T> methodName(T[] array){}
        <T> should be before return type
        Type parameters can be bounded (we explain bounds later in this article).
        Generic methods can have different type parameters separated by commas in the method signature.
        Method body for a generic method is just like a normal method.
     Oracle recommendation is to use an uppercase letter to represent a generic type and to choose a more descriptive letter to represent formal types.
     In Java Collections, we use T for type, K for key and V for value.
     */
    public <T> List<T> genericMethod(T[] a) {
        return Arrays.stream(a).collect(Collectors.toList());
    }

    public <T, G> List<G> genericWithMultipleTypes(T[] a, Function<T, G> transformFunction) {
        return Arrays.stream(a)
                .map(transformFunction)
                .collect(Collectors.toList());
    }

    /*
    Bounded Generics:
        upper bounded - T extend Number , T extends Runnable(interface), T extends B1 & B2 & B3 ..(Multi bounded)
        lower bounded - T super Integer ( integer and its supper classes are allowed ex: Integer, Number and object)
     */
    public <T extends Number> List<T> genericMethodBounded(T[] a) {// only Number Subclasses are allowed
        return Arrays.stream(a).collect(Collectors.toList());
    }

    public <T extends Runnable> List<T> genericMethodBounded2(T[] a) {// only Runnable subclasses are allowed
        return Arrays.stream(a).collect(Collectors.toList());
    }

    public <T extends Number & Comparable & Runnable> List<T> genericMethodBounded3(T[] a) {// only Number & Comparable Subclasses are allowed
        return Arrays.stream(a).collect(Collectors.toList());
    }

    public <T extends Comparable<T>> int compareGreaterThan(T[] array, T element) {
        int count = 0;
        for (T e : array) {
//            if( e > element) not allowed even if T extends Number because these are allowed only for primitives
            if (e.compareTo(element) > 0) { //if (new Integer(10) > new Integer(5)) this is allowed
                count++;
            }
        }
        return count;
    }

    public void test(){

//        compareGreaterThan(new int[]{5},5); //we cant pass int[], int to type T, only wrappers or classes are allowed
    }

}

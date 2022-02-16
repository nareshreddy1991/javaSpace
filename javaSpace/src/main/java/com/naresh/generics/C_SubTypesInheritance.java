package com.naresh.generics;

import java.util.ArrayList;
import java.util.List;

public class C_SubTypesInheritance {
    public static void main(String[] args) {
        Object o = new Integer(10);
        Object o2 = new ArrayList<Integer>();

//        List<Integer> l1= new ArrayList<Double>();// not allowed, both are different types, same in generics

        someMethod(new Integer(10));   // OK
        someMethod(new Double(10.1)); //ok
        //the same is true in generics
        Box<Integer> integerBox = new Box<>(10);
        Box<Double> doubleBox = new Box<>(100.5);

//        integerBox = doubleBox; not allowed, though both are boxes its types are different
        Object o3 = integerBox;
        Object o4 = doubleBox;

        //wildcard
        List<Integer> ilist = new ArrayList<>();
        List<Number> nlist = new ArrayList<>();
        List noTypeList= new ArrayList();
//        nlist= ilist;// both are different types
        List<?> w1List = ilist;
        List<?> w2List = nlist;
        List<?> noType= noTypeList;

    }

    public static void someMethod(Number n) {
    }

    static class Box<T extends Number> {
        private T value;

        public Box(T value) {
            this.value = value;
        }
    }
}

// we can inherite
//interface PayloadList<E, P> extends List<E> {// though E is not used in setPayload we need to define it
interface PayloadList<P> extends List {// if you need to remove E- remove in both places

    void setPayload(int index, P val);
}
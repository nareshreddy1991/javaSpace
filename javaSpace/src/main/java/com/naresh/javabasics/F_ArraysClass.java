package com.naresh.javabasics;

import java.util.Arrays;
import java.util.List;
import java.util.Spliterator;
import java.util.stream.IntStream;

public class F_ArraysClass {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3);//return fixe sized array, add/remove not allowed
        list.set(0, 5);//we can still replace tha values

        IntStream stream = Arrays.stream(new int[]{1, 2, 3});//we can create stream from int, long, double arrays

        int[] intArray = new int[]{10, 25, 35, 37, 68, 78, 88, 99};
        int position = Arrays.binarySearch(intArray, 99);//for binary search array should be in sorted order, we can do it on all types of data types except String
        System.out.println("found element at:" + position);


        int[] array1 = new int[]{10, 25, 35, 37};
        int[] array2 = new int[]{10, 25, 35, 37};

        int compare = Arrays.compare(array1, array2);//return 0 when both array has same elements in same order
        System.out.println("compare result:" + compare);

        int compare2 = Arrays.compareUnsigned(array1, array2);
        System.out.println("compare unassigned result:" + compare2);//TODO ??
        //TODO copy of - it will give new Array always
        int[] newArray = Arrays.copyOf(array1, 2);//first two elements of given array
        System.out.println("new array:");
        for (int i : newArray) {
            System.out.println(i);
        }
        int[] newArray2 = Arrays.copyOf(array2, 6);//existing array+ extra length, extra position have default values
        System.out.println("new array with more length:");
        for (int i : newArray2) {
            System.out.println(i);
        }

        //TODO??
//        System.arraycopy();

        boolean equals = Arrays.equals(array1, array2);//same size, same elements ,same order - return true
        System.out.println("equals:" + equals);

        int[] ints = new int[10];
        Arrays.fill(ints, 100);
        for (int i : ints)
            System.out.print(i);

        Arrays.sort(array1);

        Spliterator.OfInt spliterator = Arrays.spliterator(array1);


    }
}

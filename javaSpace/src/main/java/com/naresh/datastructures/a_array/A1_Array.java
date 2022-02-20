package com.naresh.datastructures.a_array;

import java.util.Arrays;
import java.util.Stack;

/*
Data Structures:
It is the process of arranging the data in memory, so that we can save time & memory

Linear data structures:
    -Array
    -Linked List
    -Stack(Abstract data structure - This can be implemented using Array or Linked List)
    -Queue(Abstract data structure- This can be implemented using Array)

Array:
    - Used to store homogeneous data(same type of data) at contiguous locations
    - Array store same kind of data because its easy to find the elements based on their address
        element present at = startingAddress + index * bitAllocation
Limitations:
    - Fixed size
    - Difficult to insert/delete elements because we need to shift the element
Advantages:
    - Random Access
    - Since all the elements are stored in contiguous performance will be fast
    - Modifying elements are faster


HOw to calculate complexity ?
 */
public class A1_Array {
    public static void main(String[] args) {
        //TODO rotate anti clock wise
        System.out.println("Rotate array by t times anti clock wise");
        int[] array = new int[]{5, 4, 8, 7, 3, 9};
        rotateAntiClock(array, 3);//time complexity O(n*d), there are some other ways we can solve it in O(n)
        System.out.println("Rotate array by t times using reversal algorithm");
        int t = 2; //shift two elements
        array = new int[]{5, 4, 8, 7, 3, 9};
        rotateAntiClock2(array, t);

        //TODO rotate clock wise
        System.out.println("Rotate array by t times clock wise");
        array = new int[]{5, 4, 8, 7, 3, 9};
        rotateClockWise(array, 1);
        array = new int[]{5, 4, 8, 7, 3, 9};
        rotateClockWiseCyclic(array, 1);

        //TODO rotate all zero to end of the array
        array = new int[]{5, 0, 4, 0, 8, 7, 3, 9};
        int zeroCount = 0;
        for (int i = 0; i < array.length - zeroCount; i++)
            if (array[i] == 0) {
                ++zeroCount;
                rotateAntiClock(array, 1, i);
            }

    }

    public static void rotateAntiClock(int[] array, int t) {
        int n = array.length;
        while (t > 0) {
            int first = array[0];
            for (int i = 0; i < n - 1; i++) {
                array[i] = array[i + 1];
            }
            array[n - 1] = first;
            t--;
        }
        Arrays.stream(array).forEach(System.out::print);
    }

    public static void rotateAntiClock(int[] array, int t, int start) {
        int n = array.length;
        while (t > 0) {
            int first = array[start];
            for (int i = start; i < n - 1; i++) {
                array[i] = array[i + 1];
            }
            array[n - 1] = first;
            t--;
        }
        printArray(array, "rotateAntiClock from:" + start + ":");
    }

    public static void rotateAntiClock2(int[] array, int t) {
        printArray(array, "input:");
        if (t == 0)
            return;
        if (array.length == t) {
            printArray(array, "Result");
            return;
        }
         /*else if (array.length < t)
            t = t - array.length; */   //or below one line is enough
        t = t % array.length;  // 1%5=1, 3%5=3, 5%5=0 6%7=1
        System.out.println("t" + t);
        reverse1(array, 0, t - 1);
        reverse1(array, t, array.length - 1);
        reverse1(array, 0, array.length - 1);
    }

    //my logic
    public static void reverse(int[] array, int start, int endInclusive) {//2,5 45 8739
        int n = endInclusive - start;
        for (int i = 0; i <= n / 2; i++) {
            int temp = array[start + i];
            array[start + i] = array[endInclusive - i];
            array[endInclusive - i] = temp;
        }
        printArray(array, "start" + start + "-" + endInclusive + ":");
    }

    public static void reverse1(int[] array, int start, int end) {//prefered logic
        while (start < end) {//0 1
            int temp = array[start];
            array[start] = array[end];
            array[end] = temp;
            start++;
            end--;
        }
        printArray(array, "start" + start + "-" + end + ":");
    }

    public static void rotateClockWise(int[] array, int t) {
        int end = array[array.length - 1];
//        for (int i = 0; i < array.length - 1; i++) {// 5 6 8 4 7
//            array[array.length - 1 - i] = array[array.length - 2 - i];
//        }
        for (int i = array.length - 1; i > 0; i--) {// 3 2 1
            array[i] = array[i - 1];
        }
        array[0] = end;
        printArray(array, "rotateClockWise:");
    }

    public static void rotateClockWiseCyclic(int[] array, int t) {
        /*
        input:548739
        948735
        958734
        954738
        954783(result)
         */
        int i = 0, j = array.length - 1;//j is fixed, i will move toward j
        while (i != j) {
            int temp = array[i];
            array[i] = array[j];
            array[j] = temp;
            i++;
        }
        printArray(array, "rotateClockWiseCyclic:");
    }

    private static void printArray(int[] array, String msg) {
        System.out.print(msg != null ? msg : "");
        Arrays.stream(array).forEach(System.out::print);
        System.out.println();
    }
}

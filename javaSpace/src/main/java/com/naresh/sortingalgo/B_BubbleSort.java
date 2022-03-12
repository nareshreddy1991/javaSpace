package com.naresh.sortingalgo;

import java.util.Arrays;

/*
Bubble sort:
Best Case Time Complexity: O(n). Best case occurs when array is already sorted.
Wost case: O(n^2)
 5, 8, 7, 4, 6   <-- input (compare 1st element with 2nd, 2nd with 3rd ..)
 unsorted      sorted
 5, 7, 4, 6 | (8)
 5, 4, 6 | (7), (8)
 4, 5 | (6), (7), (8)
 4 | (5), (6), (7), (8)

Stable: true
*/
public class B_BubbleSort {
    public static void main(String[] args) {
        System.out.println("\nBubble sort");
        int[] array = new int[]{5, 8, 7, 4, 6, 8, 5, 4, 2, 3, 1, 2, 5};
        bubbleSort(array);
        Arrays.stream(array).forEach(f -> System.out.print(f + " "));

        System.out.println("\nBubble sort recursion:");
        int[] array2 = new int[]{5, 8, 7, 4, 6, 8, 5, 4, 2, 3, 1, 2, 5};
        bubbleSortRecursion(array2, array2.length);
        Arrays.stream(array2).forEach(f -> System.out.print(f + " "));
    }

    public static void bubbleSort(int[] array) {
        int n = array.length;
        for (int i = 0; i < n - 1; i++) {//to sort n elements we need n-1 comparisons
            //n-1 is to avoid index out of bound because we are comparing j vs j+1
            //n-i is to avoid unwanted comparison with sorted elements
            for (int j = 0; j < n - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }

    /*
    Improved version:
    If array is already sorted then we can break the loop
     */
    public static void bubbleSort2(int[] array) {
        int n = array.length;
        for (int i = 0; i < n - 1; i++) {//to sort n elements we need n-1 comparisons
            //n-1 is to avoid index out of bound because we are comparing j vs j+1
            //n-i is to avoid unwanted comparison with sorted elements
            boolean isSwapped = true;
            for (int j = 0; j < n - i - 1; j++) {
                if (array[j] > array[j + 1]) {//if array is sorted then this will be false for all the elements
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    isSwapped = false;
                }
            }
            if (isSwapped) break;
        }
    }

    public static void bubbleSortRecursion(int[] array, int n) {
        if (n == 1) return;
        for (int i = 0; i < n - 1; i++) {//n-1 is required here otherwise index out of bound
            if (array[i] > array[i + 1]) {
                int temp = array[i + 1];
                array[i + 1] = array[i];
                array[i] = temp;
            }
        }
        bubbleSortRecursion(array, n - 1);//last element is sorted so reduced the size by 1
    }
}

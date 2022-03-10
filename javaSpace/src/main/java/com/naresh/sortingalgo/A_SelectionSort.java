package com.naresh.sortingalgo;

import java.util.Arrays;
/*
The good thing about selection sort is it never makes more than O(n) swaps and can be useful when memory write is a costly operation.
 */
public class A_SelectionSort {
    public static void main(String[] args) {
        System.out.println("Selection sort\n");
        int[] array = new int[]{5, 8, 7, 4, 6, 8, 5, 4, 2, 3, 1, 2, 5};
        selectionSort2(array);
        Arrays.stream(array).forEach(f -> System.out.print(f + " "));

    }

    /*
    Selection sort: complexity: O(n^2)
    5, 8, 7, 4, 6 <----input (compare 5 with all elements)
    (4), 8, 7, 5, 6
    (4), (5), 8, 7, 6
    (4), (5), (6), 8, 7
    (4), (5), (6), (7), 8 (5 elements were sorted in 4 iterations)

    4A 5 3 2 4B 1 <-- input
    1 5 4A 3 4B 2
    1 3 4B 4A 5 2  <--- 4B then 4A order is changed, so this selection sort is unstable

    If output is 1 2 3 4A 4B 5 then its stable sort
     */
    public static void selectionSort(int[] array) {
        int n = array.length;
        for (int i = 0; i < n - 1; i++) { //to sort n elements we need n-1 iterations
            //j=i is to ignored comparing sorted elements, j=i+1 is to avoid comparing the same elements a[i] a[j] are same when i=0;j=0;
            for (int j = i + 1; j < n; j++) {
                if (array[i] > array[j]) {
                    int temp = array[i];
                    array[i] = array[j];
                    array[j] = temp;
                }
            }
        }
    }

    public static void selectionSort2(int[] array) {
        int n = array.length;
        for (int i = 0; i < n - 1; i++) { //to sort n elements we need n-1 iterations
            //j=i is to ignored comparing sorted elements, j=i+1 is to avoid comparing the same elements a[i] a[j] are same when i=0;j=0;
            int minPosition = i;
            for (int j = i + 1; j < n; j++) {
                if (array[minPosition] > array[j]) {
                    minPosition = j;
                }
            }
            int temp = array[minPosition];//TODO felt difficult here
            while (minPosition > i) {
                array[minPosition] = array[minPosition - 1];
                minPosition--;
            }
            array[i] = temp;
        }
    }



}

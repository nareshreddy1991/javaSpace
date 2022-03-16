package com.naresh.k_sortingalgo;

import java.util.Arrays;
/*
1. Time Complexities
Worst Case Complexity [Big-O]: O(n2)
It occurs when the pivot element picked is either the greatest or the smallest element.

This condition leads to the case in which the pivot element lies in an extreme end of the sorted array. One sub-array is always empty and another sub-array contains n - 1 elements. Thus, quicksort is called only on this sub-array.

However, the quicksort algorithm has better performance for scattered pivots.
Best Case Complexity [Big-omega]: O(n*log n)
It occurs when the pivot element is always the middle element or near to the middle element.
Average Case Complexity [Big-theta]: O(n*log n)
It occurs when the above conditions do not occur.
2. Space Complexity
The space complexity for quicksort is O(log n).
 */
public class E_QuickSort {
    public static void main(String[] args) {
        System.out.println("Quick sort\n");
        int[] array = new int[]{5, 8, 7, 4, 6, 8, 5, 4, 2, 3, 1, 2, 5};
        quickSort(array, 0, array.length - 1);
        Arrays.stream(array).forEach(f -> System.out.print(f + " "));
    }

    public static void quickSort(int[] array, int l, int h) {
        if (l >= h)
            return;
        int p = pivot(array, l, h);
        quickSort(array, l, p - 1);
        quickSort(array, p + 1, h);
    }

    // (i=-1)|5 2 6 8 |6(p)
    public static int pivot(int[] array, int l, int h) {
        int p = array[h];//consider last element as pivot & arrange smaller element left & remaining right
        int i = l - 1;

        for (int j = l; j <h; j++) {//TODO j <= h - 1 or j<h
            if (array[j] <= p) {
                i++;
                int temp = array[j];
                array[j] = array[i];
                array[i] = temp;
            }
        }
        int temp = array[i + 1];
        array[i + 1] = array[h];
        array[h] = temp;
        return i + 1;
    }

    //First element as pivot
    //TODO not working
    public static int pivot2(int[] array, int l, int h) {
        int p = array[l];//consider last element as pivot & arrange smaller element left & remaining right
        int i = l;

        for (int j = l+1; j <=h; j++) {//TODO j <= h - 1 or j<h
            if (array[j] <= p) {
                i++;
                int temp = array[j];
                array[j] = array[i];
                array[i] = temp;
            }
        }
        int temp = array[i + 1];
        array[i + 1] = array[l];
        array[l] = temp;
        return i + 1;
    }
}

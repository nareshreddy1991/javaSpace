package com.naresh.sortingalgo;

import java.util.Arrays;

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

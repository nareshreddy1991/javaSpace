package com.naresh.searchingalgo;

import java.util.Arrays;

/*
ExponentialSearch //TODO not working
 start with 1 2 4 8 16 32 position....
 if range is found then do the  binary search
 */
public class D_ExponentialSearch {
    public static void main(String[] args) {
        int[] array = new int[]{2, 3, 4, 5, 6, 7, 8, 9, 10, 12, 13, 15, 19};
        D_ExponentialSearch search = new D_ExponentialSearch();
        System.out.println(search.exponentialSearch(array, 19));
        System.out.println(search.exponentialSearch(array, array.length, 19));
    }

    public int exponentialSearch(int[] array, int element) {
        if (array[0] == element) return 0;
        int start = 1;
        while (start < array.length && array[start] <= element) {
            start = start * 2;
        }
//        if (start <= array.length - 1)//this check is not required
        return Arrays.binarySearch(array, start / 2, Math.min(start, array.length - 1), element);
//        return -1;
    }

    int exponentialSearch(int arr[],
                          int n, int x) {
        // If x is present at first location itself
        if (arr[0] == x)
            return 0;

        // Find range for binary search by
        // repeated doubling
        int i = 1;
        while (i < n && arr[i] <= x)
            i = i * 2;

        // Call binary search for the found range.
        return Arrays.binarySearch(arr, i / 2,
                Math.min(i, n - 1), x);
    }
}

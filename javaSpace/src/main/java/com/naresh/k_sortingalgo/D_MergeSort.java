package com.naresh.k_sortingalgo;

import java.util.Arrays;

/*
Merge sort:
    - Divide and conquer

Time Complexity
Best Case Complexity: O(n*log n)
Worst Case Complexity: O(n*log n)
Average Case Complexity: O(n*log n)

Space Complexity
The space complexity of merge sort is O(n).
 */
public class D_MergeSort {
    public static void main(String[] args) {
        System.out.println("Merge sort\n");
        int[] array = new int[]{5, 8, 7, 4, 6, 8, 5, 4, 2, 3, 1, 2, 5};
//        mergeSort(array, 0, array.length - 1);
        mergesortIterative(array);
        Arrays.stream(array).forEach(e -> System.out.print(e + " "));
    }

    public static void mergeSort(int[] array, int l, int h) {
        if (l >= h)
            return;
        int m = l + (h - l) / 2;
        mergeSort(array, l, m);
        mergeSort(array, m + 1, h);
        merge(array, l, m, h);
    }

    public static void mergesortIterative(int[] array) {
        int n = array.length - 1;
        int curr_size, left;
        for (curr_size = 1; curr_size < n; curr_size = curr_size * 2) {
            for (left = 0; left < n; left = left + 2 * curr_size) {
                int mid = Math.min(left + curr_size - 1, n);
                int r = Math.min(left + 2 * curr_size - 1, n);
                merge(array, left, mid, r);
            }
        }

    }

    public static void merge(int[] array, int l, int m, int h) {
        int lSize = m - l + 1; //TODO 0 1 2, 3 4 | m=2 (LSize >> 2-1+1=2, RSize>> 4-2=2)
        int rSize = h - m;  //TODO 5 6 7, 8 9| m=7 (LSize >> 7-5+1 =3 , RSize >>9-7=2

        int[] L = new int[lSize];
        int[] R = new int[rSize];

        for (int i = 0; i < lSize; i++)
//            L[i] = array[l++]; //cant increment l because its been used later
            L[i] = array[l + i];
        for (int j = 0; j < rSize; j++)
//            R[j] = array[(m++)+1]; //or both works
            R[j] = array[m + 1 + j];

        int i = 0, j = 0, k = l;
        while (i < lSize && j < rSize) {
            if (L[i] < R[j]) {
                array[k] = L[i];
                i++;
            } else {
                array[k] = R[j];
                j++;
            }
            k++;
        }
        while (i < lSize) {
            array[k++] = L[i++];
        }
        while (j < rSize) {
            array[k++] = R[j++];
        }
    }
}

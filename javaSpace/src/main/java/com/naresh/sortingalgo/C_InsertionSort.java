package com.naresh.sortingalgo;

import java.util.Arrays;

/*
Time Complexity: O(n^2)
Auxiliary Space: O(1)
Boundary Cases: Insertion sort takes maximum time to sort if elements are sorted in reverse order. And it takes minimum time (Order of n) when elements are already sorted.
Algorithmic Paradigm: Incremental Approach
Sorting In Place: Yes
Stable: Yes
Online: Yes
Uses: Insertion sort is used when number of elements is small. It can also be useful when input array is almost sorted, only few elements are misplaced in complete big array.
*/
public class C_InsertionSort {
    public static void main(String[] args) {
        System.out.println("\nInsertion sort");
        int[] array = new int[]{5, 8, 7, 4, 6, 8, 5, 4, 2, 3, 1, 2, 5};
        insertionSort(array);
        Arrays.stream(array).forEach(f -> System.out.print(f + " "));
    }

    /*
 sorted     unsorted
    12 | 11, 13, 5, 6  (check 12 > 11, yes move 12 to j+1
    11 , 12 | 13, 5, 6 (check while(12 > 13) ,no)
    11 , 12 , 13 | 5, 6 (check while(13 , 12 ,11 > 5) move elements right by one position, insert 5 at that position)
    5, 11 , 12 , 13 | 6 (check while(13 , 12 ,11, 5 > 6) move elements right by one position, insert 6 after 5)

     */
    public static void insertionSort(int[] array) {
        int j;
        for (int i = 1; i < array.length; i++) {
            j = i - 1;  // j is just before the sorted array
            int key = array[i];
            while (j >= 0 && array[j] > key) {// j could go to -1 when all elements are bigger than key
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = key;
        }
    }
    /*
What is Binary Insertion Sort?
 We can use binary search to reduce the number of comparisons in normal insertion sort.
 Binary Insertion Sort uses binary search to find the proper location to insert the selected item at each iteration.
 In normal insertion, sorting takes O(i) (at ith iteration) in worst case. We can reduce it to O(logi) by using binary search.
 The algorithm, as a whole, still has a running worst case running time of O(n^2) because of the series of swaps required
 for each insertion. Refer this for implementation.
*/

}

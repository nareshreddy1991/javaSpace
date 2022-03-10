package com.naresh.searchingalgo;

/*
Binary Search:
   Data should be in sorted order

   Time complexity: O(log n)

Sequential Search: In this, the list or array is traversed sequentially and every element is checked. For example: Linear Search.
Interval Search: These algorithms are specifically designed for searching in sorted data-structures.
These type of searching algorithms are much more efficient than Linear Search as they repeatedly target the center of the search structure and divide the search space in half.
 For Example: Binary Search.
 */
public class B_BinarySearch {
    public static void main(String[] args) {
        int[] array = new int[]{2, 3, 4, 5, 6, 7, 8, 9, 10, 12, 13, 15, 19};
        B_BinarySearch binarySearch = new B_BinarySearch();
        System.out.println("Binary search1:" + binarySearch.binarySearch(array, 19));
        System.out.println("Binary search2:" + binarySearch.binarySearch2(array, 19, 0, array.length - 1));
    }

    public int binarySearch(int[] array, int element) {
        int start = 0, end = array.length - 1;
        //when we have start, end no need to have for loop
        while (start <= end) { //TODO <= is required, Ex: 2 ,3 search 3 >> (0+1)/2=0 >> while(1<2) return -1
//            int mid = (start + end) / 2; //This could cause issues when array is too big, sum of position can leads to out of range, may go to negative
//            int mid = end - (end - start) / 2; //or
            int mid = start + (end - start) / 2;
            if (array[mid] == element)
                return mid;
            else if (array[mid] < element)
                start = mid + 1;
            else
                end = mid - 1;
        }
        return -1;
    }

    public int binarySearch2(int[] array, int element, int start, int end) {
        int mid = start + (end - start) / 2;
        if (array[mid] == element)
            return mid;
        else if (start > end)//terminate if element not found
            return -1;
        if (array[mid] < element)
            return binarySearch2(array, element, mid + 1, end);//TODO method should return the value, otherwise we will not get result
        return binarySearch2(array, element, start, mid - 1);
//        return -1; //no use
    }
}

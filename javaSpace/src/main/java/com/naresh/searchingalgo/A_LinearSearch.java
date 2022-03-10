package com.naresh.searchingalgo;

/*
Linear search: data can be in unsorted order
Time complexity: O(n)
 */
public class A_LinearSearch {
    public static void main(String[] args) {
        int[] array = new int[]{5, 8, 7, 4, 6, 8, 5, 4, 2, 3, 1, 2, 5};
        A_LinearSearch linearSearch = new A_LinearSearch();
        System.out.println("linear search1:" + linearSearch.linearSearch(array, 2));
        System.out.println("linear search2:" + linearSearch.linearSearch2(array, 2));
        System.out.println("linear search3:" + linearSearch.linearSearch3(array, 2));
        System.out.println("linear search4:" + linearSearch.linearSearch4(array, 2, 0));
    }

    public int linearSearch(int[] array, int element) {
        int n = array.length;
        for (int i = 0; i < n; i++) { //i < n will be compared n+1 times
            if (array[i] == element) //this will be compared n times, total comparison 2n+1, TODO can do it better? Yes
                return i;
        }
        return -1;
    }

    //improved performance
    public int linearSearch2(int[] array, int element) {
        int n = array.length;
        int lastElement = array[n - 1];
        if (lastElement == element) {
            return n - 1;
        }
        array[n - 1] = element;// since last position having searching element

        for (int i = 0; ; i++) {
            if (array[i] == element) { // n comparison
                array[n - 1] = lastElement; //restore the last value
                if (i == n) {  //1 comparison, total n+1 comparison
                    return -1;
                }
                return i;
            }
        }
    }

    //Time complexity O(n/2)
    //This will give the element position in any order, above two search from left to right
    public int linearSearch3(int[] array, int element) {
        int start = 0;
        int end = array.length - 1;
        while (start < element) {
            if (array[start] == element) {
                return start;
            } else if (array[end] == element)
                return end;
            start++;
            end--;
        }
        return -1;
    }

    //linear search with recursion, no loops are needed
    public int linearSearch4(int[] array, int element, int position) {
        if (array[position] == element) {
            return position;
        }
        if (position < array.length - 1)
            return linearSearch4(array, element, ++position);//position++ will leads to stackOverflowError
        else
            return -1;
    }

}


package com.naresh.j_searchingalgo;

/*
JumpSearch:
find the size of intervel= Math.sqrt(array.length)
check 0 then internval.....
 */
public class C_JumpSearch {
    public static void main(String[] args) {
        int[] array = new int[]{2, 3, 4, 5, 6, 7, 8, 9, 10, 12, 13, 15, 19};
        C_JumpSearch jumpSearch = new C_JumpSearch();
        System.out.println("Binary search1:" + jumpSearch.jumpSearch(array, 19));
    }

    public int jumpSearch(int[] array, int element) {
        int jump = (int) Math.sqrt(array.length);
        int start = 0, end = array.length - 1;
        while (start < end) {//<= will leads to infinite loop
            if (array[start] <= element)
                start = Math.min(start + jump, end);//start should not go beyond length
            else {
                start = Math.max(start - jump, 0);// start should go negative
                break;
            }
        }
        while (start <= end) {//<= is required otherwise size is multiples of jump then last position cant be searched
            if (array[start] == element)
                return start;
            start++;
        }
        return -1;
    }
}

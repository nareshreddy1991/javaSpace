package com.naresh.h_datastructures.a_recursion;

import java.util.ArrayList;
import java.util.List;

/*
Coin exchange problem -- asked in Paypal
input - {2,3,4} target = 7
find different combination, sum will result to target 7
one candidate can be taken any number of times
Ex: 2 2 3
    3 4
 */
public class B6_CombinationSum1 {
    public static void main(String[] args) {
        //if any negative values will result in StackOverFlow //TODO how to fix it?
        //if we have duplicate numbers in input it will lead to duplicate combinations
        int[] input = new int[]{1,1, 2, 3, 4};
        combination(input, 0, input.length - 1, 7, new ArrayList<>());
    }

    //print results
    private static void combination(int[] a, int i, int n, int target, List<Integer> ds) {
        if (i > n || target <= 0) {
            if (target == 0) {
                for (Integer d : ds) {
                    System.out.print(d + " ");
                }
                System.out.println();
            }
            return;
        }
        if (a[i] == 0) {//if input has zero ignore it
            i++;
        }
        if (a[i] <= target) {
            ds.add(a[i]);
//            combination(a, i, n, target - a[i], ds);
            combination(a, i+1, n, target - a[i], ds); //TODO if one candidate has to be taken only one time
            ds.remove(ds.size() - 1);//only when added remove has to happen
        }
        combination(a, i + 1, n, target, ds);
    }

    //dont print just add to list
    //TC (2^n)* k, k is because we are adding it in another list
    private static void combination1(int[] a, int i, int n, int target, List<Integer> ds, List<List<Integer>> result) {
        if (i > n || target <= 0) {
            if (target == 0) {
               result.add(new ArrayList<>(ds));
            }
        }
        if (a[i] == 0) {//if input has zero ignore it
            i++;
        }
        if (a[i] <= target) {
            ds.add(a[i]);
            combination1(a, i, n, target - a[i], ds, result);
            ds.remove(ds.size() - 1);//only when added remove has to happen
        }
        combination1(a, i + 1, n, target, ds, result);
    }


}

package com.naresh.h_datastructures.a_recursion;

import java.util.ArrayList;
import java.util.List;

/*
input ={1,2,2}
Find only unique subsets
for n numbers we have 2^n subsets, we can store all subsets in set<List<Integers>> then convert to what ever type is needed
in this case the complexity will be  TC=2^n * logn

This is not good, so lets do it differently
 */
public class B8_SubSet2 {
    public static void main(String[] args) {
        int[] input = new int[]{1,1, 2, 2};
        List<List<Integer>> result = new ArrayList<>();
        findUniqueSubSets(input, 0, input.length - 1, new ArrayList<>(), result);
        for (List<Integer> list : result) {
            for (Integer val : list) {
                System.out.print(val);
            }
            System.out.println();
        }
    }

    public static void findUniqueSubSets(int[] a, int index, int n, List<Integer> list, List<List<Integer>> result) {
        list.forEach(System.out::print);
        System.out.println();
//        result.add(new ArrayList<>(list)); //or you can add to list
        for (int i = index; i <= n; i++) {
            if (i != index && a[i] == a[i - 1]) continue;
            list.add(a[i]);
            findUniqueSubSets(a, i + 1, n, list, result);
            list.remove(list.size() - 1);
//            findUniqueSubSets(a, i + 1, n, list,result); //TODO no need to form another recursion
        }

    }
}

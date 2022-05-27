package com.naresh.h_datastructures.a_recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class B5_MultipleRecursionSubSeq {
    public static void main(String[] args) {
        int[] input = new int[]{3, 1, 2, 4};
        System.out.println("Print Subsequences:");
        printAllSubSequence2(input, 0, input.length - 1, new ArrayList<>());
        System.out.println("sun seq count::" + countAllSubSequence(input, 0, input.length - 1, new ArrayList<>()));
        System.out.println("Print any one subsequence:");
        printAnyOneSubSequence(input, 0, input.length - 1, new ArrayList<>());
        System.out.println("Print subset/seq sum in increasing order:");
        input = new int[]{3, 1, 2};
        List<Integer> result = new ArrayList<>();
        printSumOfAllSubSeqInIncreasingOrder(input, 0, 0, result);
        Collections.sort(result);
        for (Integer integer : result) {
            System.out.print(integer + " ");
        }
    }

    private static int fib(int n) {
        if (n <= 1)
            return n;
        int left = fib(n - 1);//once this is done then move to next line
        int secondLeft = fib(n - 2);
        return left + secondLeft;
    }

    /*
    print all subsequences - all contigeous sub arrays +all non contigeous sub elements with same order
    Ex: 3 1 2 ( no of sub sequences = 2^n) ex: n=3, sub seq:8/n=4 sub seq=16
        {}
        3
        1
        2
        3 1
        3 2
        1 2
        3 1 2



f([3,1,2],0,2,list){  f([3,1,2],1,2,list){  f([3,1,2],2,2,list){  f([3,1,2],3,2,list){
-                   -                   -                   -        if(3>2){
{3}                 {3,1}                 {3,1,2}                       TODO 1) print {3, 1 2}; return;
-                   -                     -                   -         }
-                   list.r()             list.remove(lastelement)
- |                 f(input,2,2,{3}}     f(input,3,2,{3,1})
}|                  }    |              }             |                }
 |                       |                            |
 |                       |                            |
so on                    |               f(input,3,2,{3,1}){
                         |                  if(3>2)
                         |                  TODO 2) print {3,1}return
                         |               }
                         |
                   f(input,2,2,{3}){
                   {3,2}
                   f(input,3,2,{3,2))------------------> TODO 3) print {3,2}
                   {3}
                   f(input,3,2,{3})---------------------> TODO 4) print {4}
                   }

     */
    private static void printAllSubSequence(int[] input, int i, int n, List<Integer> list) {
        /*
        TODO remember this pattern - the same will be used for many problems
         */
        if (i > n) {
            list.stream().forEach(System.out::print);
            System.out.println();
            return;
        }
        list.add(input[i]);//take
        printAllSubSequence(input, i + 1, n, list);
        list.remove(list.size() - 1);//not take
        printAllSubSequence(input, i + 1, n, list);
        /* output:
312
31
32
3
12
1
2

         */
    }

    private static void printAllSubSequence2(int[] input, int i, int n, List<Integer> list) {
        if (i > n) {
            list.stream().forEach(System.out::print);
            System.out.println();
            return;
        }
        //not take
        printAllSubSequence2(input, i + 1, n, list); //TODO just brining this like up will reverse the output
        //take
        list.add(input[i]);
        printAllSubSequence2(input, i + 1, n, list);
        //not take
        list.remove(list.size() - 1);

        /* output:
2
1
12
3
32
31
312
         */
    }

    private static int countAllSubSequence(int[] input, int i, int n, List<Integer> list) {
        if (i > n) {
//            list.stream().forEach(System.out::print);
//            System.out.println();
            return 1;//TODO for counting all subsequence return one here
        }
        list.add(input[i]);//take
        int pickCount = countAllSubSequence(input, i + 1, n, list);
        list.remove(list.size() - 1);//not take
        int nonPickCount = countAllSubSequence(input, i + 1, n, list);
        return pickCount + nonPickCount;//TODO sum pick and non picks
    }

    private static boolean printAnyOneSubSequence(int[] input, int i, int n, List<Integer> list) {
        if (i > n) {
            list.stream().forEach(System.out::print);
            System.out.println();
            return true;//TODO return true to stop the flow
        }
        list.add(input[i]);//take
        if (printAnyOneSubSequence(input, i + 1, n, list)) {
            return true;
        }
        list.remove(list.size() - 1);//not take
        if (printAnyOneSubSequence(input, i + 1, n, list)) {
            return true;
        }
        return false;
    }

    // added the results to list and sort it, it will take log(2^n)
    // TC: 2^n * log(2^n)
    private static void printSumOfAllSubSeqInIncreasingOrder(int[] input, int i, int sum, List<Integer> ds) {
        if (i == input.length) {
            ds.add(sum);
            return;
        }
        printSumOfAllSubSeqInIncreasingOrder(input, i + 1, sum + input[i], ds);//take
        printSumOfAllSubSeqInIncreasingOrder(input, i + 1, sum, ds);//not take
    }

}

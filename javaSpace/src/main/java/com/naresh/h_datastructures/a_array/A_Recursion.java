package com.naresh.h_datastructures.a_array;

public class A_Recursion {
    public static void main(String[] args) {
        recursion1(2, 12);
    }

    public static void recursion(int n) {
        if (n == 0)
            return;
        System.out.println("recursion call:" + n);// 5 4 3 2 1
        recursion(n - 1);
        System.out.println("recursion end:" + n);//1 2 3 4 5
    }

    /*
recursion call1:2/x:12
recursion call1:1/x:12
    recursion call1:1/x:11
    recursion end1:1/x:11
recursion end1:1/x:12
recursion call1:2/x:11
recursion call1:1/x:11
recursion end1:1/x:11
recursion end1:2/x:11
recursion end1:2/x:12
     */
    public static void recursion1(int n, int x) {
        if (n == 0 || x == 10)
            return;
        System.out.println("recursion call1:" + n + "/x:" + x);// 5 4 3 2 1
        recursion1(n - 1, x);
//        System.out.println("recursion middle:" + n + "/x:" + x);
        recursion1(n, x - 1);
        System.out.println("recursion end1:" + n + "/x:" + x);//1 2 3 4 5
    }
/*
recursion call1:2/x:12
recursion call1:1/x:12
        recursion middle:1/x:12
            recursion call1:1/x:11
            recursion middle:1/x:11
        recursion end1:1/x:11
        recursion end1:1/x:12
    recursion middle:2/x:12
    recursion call1:2/x:11
    recursion call1:1/x:11
    recursion middle:1/x:11
    recursion end1:1/x:11
    recursion middle:2/x:11
    recursion end1:2/x:11
 */

    public static int recursion2(int n) {
        if (n == 0)
            return n;
        System.out.println("recursion2 call:" + n);//   5 4 3 2 1
        return recursion2(n - 1);
//        System.out.println("recursion end:" + n);//after we cant have any statements
    }
}

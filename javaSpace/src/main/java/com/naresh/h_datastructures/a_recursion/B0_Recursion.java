package com.naresh.h_datastructures.a_recursion;

/*
  public static void print(5) {             public static void print(4) {
        if (n == 0)                             if (n == 0)
            return;          ==>                    return; ====> and so on
        System.out.print(1);                System.out.print(1);
        print(5 - 1);                       print(4 - 1);
    }                                       }

 */
public class B0_Recursion {
    public static void main(String[] args) {
        print(5);//print only 5 times
        System.out.println();
        print2(0);//print till 5
        System.out.println();
        printEven(0);
        System.out.println();
        printNTimes(1,5);
    }

    public static void print(int n) {
        if (n == 0)// base condition used to stop the recursion
            return;
        System.out.print(1);
        print(n - 1);
    }

    public static void print2(int n) {
        if (n == 5)
            return;
        System.out.print(2);
        print2(n + 1);
    }

    public static void printEven(int n) {
        if (n == 10)
            return;
        if (n % 2 == 0)
            System.out.print(n + " ");
        printEven(n + 1);
    }

    //input 1 5 is 5
    // p(1,5)->p(2,5)->p(3,5)->p(4,5)->p(5,5)->p(6,5)-
    //                                     return<---|
    // TC:O(n) SC: O(n) - stack space
    public static void printNTimes(int i, int n) {
        if (i > n)
            return;
        System.out.print("JSR ");
//        System.out.print(i);// print i to n
        printNTimes(i + 1, n);
    }

    public static void printNToOne(int n){
        if(n<=0)
            return;
        System.out.println(n);
        printNToOne(n-1);
    }
}

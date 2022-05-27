package com.naresh.h_datastructures.a_recursion;

public class A1_RecursionExamples {
    public static void main(String[] args) {
        System.out.println("sum:" + findSum(5));
        System.out.println("factorial:" + findFactorial(5));
        System.out.println("No of paths:" + findNoOfPaths(3, 3));
        System.out.println("Fib:" + fib(6));
    }

    //f(n)=n+(n-1)+(n-2).....0
    //f(3)=3+2+1
    //f(3)=3+f(2)
    private static int findSum(int n) {
        if (n == 0)
            return 0;
        return n + findSum(n - 1);
    }

    //5*4*3*2*1
    private static int findFactorial(int n) {
        if (n == 1)
            return 1;
        return n * findFactorial(n - 1);
    }

    // no of paths  in 3X3= no of paths in 3x2 + no of paths in 2X3(refer black book for explanation)
    private static int findNoOfPaths(int m, int n) {
        if (m == 1 || n == 1)
            return 1;
        return findNoOfPaths(m, n - 1) + findNoOfPaths(m - 1, n);
    }

    //0 1 2 3 5 8
    private static int fib(int n) {
        if (n <= 1)
            return n;
        return fib(n - 1) + fib(n - 2);
    }
}

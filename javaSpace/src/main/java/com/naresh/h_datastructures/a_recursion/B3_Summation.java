package com.naresh.h_datastructures.a_recursion;

public class B3_Summation {
    public static void main(String[] args) {
        SumClass sumClass = new SumClass();
        sumTillGivenNumber(5, 0);
        System.out.println();
        sumTillGivenNumber1(5, sumClass);
        System.out.println("Sum:" + sumClass.sum);
        System.out.println("Sum with return:" + sumTillGivenNumber(5));
    }

    static class SumClass {
        public Integer sum = 0;

        SumClass setSum(int sum) {
            this.sum = sum;
            return this;
        }
    }
    /*
    f(5,0)-f(4,5)-f(3,9)-f(2,12)-f(1,14)-f(0,15)
     */
    private static void sumTillGivenNumber(int n, int sum) {
        if (n < 1) {
            System.out.println("SUm:" + sum);
            return;
        }
        sumTillGivenNumber(n - 1, n + sum);
    }

    //sum as param
    private static void sumTillGivenNumber1(int n, SumClass sum) {
        if (n < 1) return;
        sumTillGivenNumber1(n - 1, sum.setSum(sum.sum + n));
    }


    //sum as return value
    private static int sumTillGivenNumber(int n) {
        if (n == 0)
            return 0;
        return n + sumTillGivenNumber(n - 1);
    }
}

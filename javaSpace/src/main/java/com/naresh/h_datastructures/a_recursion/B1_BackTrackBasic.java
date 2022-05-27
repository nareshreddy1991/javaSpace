package com.naresh.h_datastructures.a_recursion;

public class B1_BackTrackBasic {
    public static void main(String[] args) {
        //print 1 to N
        printOneToN(5);
        System.out.println();
        //print 5 to 0
        printNtoOne(1, 5);
    }

    /*
       Don't use n+1
       p(5){        p(4){           p(3){           p(2){           p(1){           p(0){
        if(5<=0)     if(4<=0)        if(3<=0)        if(2<=0)        if(1<=0)          if(0<=0)
            return;      return;         return;         return;         return;         return; <--- True & return
        p(4);        p(3);           p(2);           p(1);           p(0);             p(4);
        sout(5)      sout(4)         sout(3)         sout(2)         sout(1)         sout(0)
       }            }               }               }               }             }

       so it will print as
       1 2 3 4 5
     */
    private static void printOneToN(int n) {
        if (n <= 0)//n==0
            return;
        printOneToN(n - 1);
        System.out.print(n + " ");//printing after recursion
    }

    private static void printNtoOne(int i, int n) {// input 0,5
        if (i > n)
            return;
        printNtoOne(i + 1, n);
        System.out.print(i + " ");
    }
}

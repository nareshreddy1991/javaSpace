package com.naresh.h_datastructures.j_matrix;

public class B6_RotateMatrixBy180D {
    public static void main(String[] args) {
//        int[][] a = new int[][]{
//                {1, 2, 3, 4, 5},
//                {7, 8, 9, 10, 11},
//                {13, 14, 15, 16, 17}};
        int[][] a = new int[][]{
                {1, 2, 3, 4},
                {7, 8, 9, 10},
                {13, 14, 15, 16},
                {17, 18, 19, 20}};
        printIn180D(a);
        System.out.println("input:");
        print(a);
        rotateBy180ClockWise(a);
        System.out.println("after rotation");
        print(a);
    }

    /*
    1 2 3       9 8 7
    4 5 6 ==>   6 5 4
    7 8 9       3 2 1(reverse data in first row copy to last row)

    input---> reverse data in every row ---> col reverse(complexity is high O2(m*n)

    Below approach:
    swap 1 & 9, 2 & 8, 3 & 7
    if no of row are even -- row iterate m/2 else m/2+1
     */
    private static void rotateBy180ClockWise(int[][] a) {
        int m = a.length;
        int n = a[0].length;
        int noOfRowIteration = m % 2 == 0 ? m / 2 : m / 2 + 1;
        System.out.println("iteration:" + noOfRowIteration);
        for (int i = 0; i < noOfRowIteration; i++) {
            //if rows are odd & if its a middle row then n/2 else n
            int noOfColIteration = m % 2 != 0 && i == m / 2 ? n / 2 : n;
            for (int j = 0; j < noOfColIteration; j++) {
                int temp = a[i][j];
                a[i][j] = a[m - 1 - i][n - 1 - j];
                a[m - 1 - i][n - 1 - j] = temp;
            }
        }
    }

    private static void printIn180D(int[][] a) {
        int m = a.length;
        int n = a[0].length;
        for (int i = m - 1; i >=0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                System.out.print(a[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static void print(int[][] a) {
        for (int i = 0; i < a.length; i++) {
            int[] row = a[i];
            int colLength = row.length;
            for (int j = 0; j < colLength; j++) {
                System.out.print(a[i][j] + " ");
            }
            System.out.println();
        }
    }
}

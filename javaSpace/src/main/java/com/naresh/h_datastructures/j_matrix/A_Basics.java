package com.naresh.h_datastructures.j_matrix;

public class A_Basics {
    public static void main(String[] args) {
        int[][] a = new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};//mxn 3x3
        int[][] b = new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        int[][] add = new int[3][3];
        int[][] sub = new int[3][3];
        int[][] multiplication = new int[3][3];

        for (int i = 0; i < a.length; i++) { //a.length gives rows
            int[] row = a[i];
            int colLength = row.length;//if array is multi dimenctional get col length for every row
            for (int j = 0; j < colLength; j++) {
                add[i][j] = a[i][j] + b[i][j];
                sub[i][j] = a[i][j] - b[i][j];
            }
        }

        //for multiplicaiton rows = columns 3*2 will not work
        //each element should be multiplied by entire column
        //a[0][0]= a's first row * b's first col
        //a[0][1]= a's first row * b's second col
        int r = 3, c = 3;
        for (int i = 0; i < r; i++) { //a.length gives rows
            for (int j = 0; j < c; j++) {
                for (int k = 0; k < r; k++) {
                    multiplication[i][j] += a[i][k] * b[k][j];
                }
            }
        }
        print(add);
        print(sub);
        System.out.println("multiplicaiton");
        print(multiplication);

        int sum = 0;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (i == j) {
                    sum += a[i][j];
                }
            }
        }
        System.out.println("diagonal sum:" + sum);
        //optimized way
        int i = 0, j = 0, sum2 = 0;
        while (i < r & j < c) {
            sum2 += a[i][j];
            i++;
            j++;
        }
        System.out.println("diagonal sum:" + sum2);

        printCol(a);

        a = new int[][]{{1, 2, 3,33}, {4, 5, 6,66}, {7, 8, 9,66},{17, 18, 99,16}};
        System.out.println("reverse col input:");
        print(a);
        System.out.println("after reverse");
        reverseCol(a);
        print(a);

        a = new int[][]{{1, 2, 3,33}, {4, 5, 6,66}, {7, 8, 9,66},{17, 18, 99,16}};
        System.out.println("reverse row input:");
        print(a);
        System.out.println("after row reverse");
        reverseRow(a);
        print(a);
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

    private static void printCol(int[][] a) {
        System.out.println("printing col wise:");
        int m = a.length;
        int n = a[0].length;
        for (int c = 0; c < n; c++) {
            for (int r = 0; r < m; r++) {
                System.out.print(a[r][c] + " ");
            }
            System.out.println();
        }
    }
    /*
    1 2 3   7
    4 5 6   4
    7 8 9   1
     */

    private static void reverseCol(int[][] a) {
        int m = a.length;
        int n = a[0].length;
        for (int i = 0; i < m / 2; i++) {//m/2 is enough
            for (int j = 0; j < n; j++) {
                int temp = a[i][j];
                a[i][j] = a[m - i - 1][j];
                a[m - i - 1][j] = temp;
            }
        }
    }

    /*
  1 2 3   3 2 1
  4 5 6
  7 8 9
   */
    private static void reverseRow(int[][] a) {
        int m = a.length;
        int n = a[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n/2; j++) {
                int temp = a[i][j];
                a[i][j] = a[i][n-j-1];
                a[i][n-j-1] = temp;
            }
        }
    }

}

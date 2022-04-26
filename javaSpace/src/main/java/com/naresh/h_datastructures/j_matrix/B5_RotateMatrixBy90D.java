package com.naresh.h_datastructures.j_matrix;

public class B5_RotateMatrixBy90D {
    public static void main(String[] args) {
        int[][] a = new int[][]{
                {1, 2, 3, 4},
                {7, 8, 9, 10},
                {13, 14, 15, 16},
                {17, 18, 19, 20}};
        print(a);
        rotateBy90ClockWise(a);
        System.out.println("after rotation");
        print(a);
    }

    /*
    input---> transpose ---> col reverse
    No extra space is allowed
     */
    private static void rotateBy90AntiClockWise(int[][] a) {
        transpose(a);
        reverseCols(a);
    }

    /*
    input --> reverse col  --> transpose
     */
    private static void rotateBy90ClockWise(int[][] a) {
        reverseCols(a);
        transpose(a);
    }

    /*
    1 2 3       1 4 7
    4 5 6  ==>  2 5 8
    7 8 9       3 6 9
    converting row to column is transpose
     */
    private static void transpose(int[][] a) {
        int N = a.length;
        for (int i = 0; i < N - 1; i++) {
            for (int j = i; j < N; j++) {
                int temp = a[i][j];
                a[i][j] = a[j][i];
                a[j][i] = temp;
            }
        }
    }

    /*
     1 2 3     7 8 9
     4 5 6 ==> 4 5 6
     7 8 9     1 2 3
     */
    private static void reverseCols(int[][] a) {
        int N = a.length;
        for (int i = 0; i < N / 2; i++) {
            for (int j = 0; j < N; j++) {
                int temp = a[i][j];
                a[i][j] = a[N - 1 - i][j];
                a[N - 1 - i][j] = temp;
            }
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

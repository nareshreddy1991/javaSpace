package com.naresh.h_datastructures.j_matrix;

import java.util.ArrayList;
import java.util.List;

/*
Input:  1    2   3   4
        5    6   7   8
        9   10  11  12
        13  14  15  16
Output: 1 2 3 4 8 12 16 15 14 13 9 5 6 7 11 10
Explanation: The output is matrix in spiral format.

Input:  1   2   3   4  5   6
        7   8   9  10  11  12
        13  14  15 16  17  18
Output: 1 2 3 4 5 6 12 18 17 16 15 14 13 7 8 9 10 11
Explanation :The output is matrix in spiral format.
 */
public class B1_PrintSpiral extends MatrixUtils{
    public static void main(String[] args) {
        int[][] a = new int[][]{
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}};
//        int[][] a = new int[][]{
//                {1, 2, 3, 4, 5, 6},
//                {7, 8, 9, 10, 11, 12},
//                {13, 14, 15, 16, 17, 18}};
        spiralOrderMoveElementsByOnePosition(a);
        System.out.println("after rotation");
        print(a);
    }

    // Time complexity O(m*n)
    //space O(1)
    //clock wise todo try anti clock wise
    public static void spiralOrder1(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        int r = 0, c = 0;
        while (r < m & c < n) {
            for (int i = c; i < n; i++) {
                System.out.print(matrix[r][i] + " ");
            }
            r++;
            for (int i = r; i < m; i++) {
                System.out.print(matrix[i][n - 1] + " ");
            }
            n--;
            if (r < m) {
                for (int i = n - 1; i >= c; i--) {
                    System.out.print(matrix[m - 1][i] + " ");
                }
                m--;
            }
            if (c < n) {
                for (int i = m - 1; i >= r; i--) {
                    System.out.print(matrix[i][c] + " ");
                }
                c++;
            }

        }
    }


    public static void spiralOrderMoveElementsByOnePosition(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        int r = 0, c = 0;
        int prev=matrix[r][c];
        while (r < m & c < n) {
            for (int i = c; i < n; i++) {//TODO we can't start i=c+1 because for inner squares it will affect
                System.out.print(matrix[r][i] + " ");
                /*
                TODO felt so difficult on this moving logic
                 For the first iteration this logic will not require but cant avoid this
                 */
                int temp=matrix[r][i];
                matrix[r][i]=prev;
                prev=temp;
            }
            r++;
            for (int i = r; i < m; i++) {
                System.out.print(matrix[i][n - 1] + " ");
                int temp=matrix[i][n - 1];
                matrix[i][n - 1]=prev;
                prev=temp;
            }
            n--;
            if (r < m) {
                for (int i = n - 1; i >= c; i--) {
                    System.out.print(matrix[m - 1][i] + " ");
                    int temp=matrix[m - 1][i];
                    matrix[m - 1][i]=prev;
                    prev=temp;
                }
                m--;
            }
            if (c < n) {
                for (int i = m - 1; i >= r; i--) {
                    System.out.print(matrix[i][c] + " ");
                    int temp=matrix[i][c];
                    matrix[i][c]=prev;
                    prev=temp;
                }
                c++;
            }
        }
        matrix[0][0]=prev;
    }

    //Time complexity O(m*n)
    //space O(m*n)
    public static void spiralOrder2(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        int c = 0, r = 0, di = 0;
        int[] rd = {0, 1, 0, -1};
        int[] cd = {1, 0, -1, 0};
        boolean[][] seen = new boolean[m][n];

        int i = 0;
        while (i < m * n) {
            System.out.print(matrix[r][c] + " ");
            seen[r][c] = true;
            int cr = r + rd[di];
            int cc = c + cd[di];
            if (0 <= cr && cr < m && 0 <= cc && cc < n && !seen[cr][cc]) {
                r = cr;
                c = cc;
            } else {
                di = (di + 1) % 4;
                c = c + cd[di];
                r = r + rd[di];
            }
            i++;
        }
    }


}


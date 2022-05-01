package com.naresh.h_datastructures.j_matrix;

/*
Given an M × N matrix, check if it is a Toeplitz matrix or not.
A Toeplitz matrix or diagonal-constant matrix is a matrix in which each descending diagonal from left to right is constant.

Any M × N matrix mat is a Toeplitz matrix if mat(i, j) = mat(i+1, j+1) = mat(i+2, j+2), and so on… Here, mat(i, j)
denotes the element mat[i][j] in the matrix.

Ex:
a b c d
e a b c
f e a b

 */
public class E2_CheckToeplitz {
    public static void main(String[] args) {
        int[][] a = new int[][]{
                {3, 7, 0, 9, 8},
                {5, 3, 7, 0, 9},
                {6, 5, 3, 7, 0},
                {4, 6, 5, 3, 7}
        };
        printToeplitz(a);
        System.out.println("Result:" + checkToeplitz2(a));
    }

    //a[i][j]==a[i+1][j+1] is called Toeplitz
    /*
    1 - X compare 00 with 11
    - 1 - compare 11 with 22
    X - 1 no need to travel this row
    and no need to travel last col
    X elements will never be visited
     */
    private static void printToeplitz(int[][] a) {
        int m = a.length;
        int n = a[0].length;
        for (int i = 0; i < n; i++) {
            int row = 0, col = i;
            while (row < m && col < n) {
                System.out.print(a[row][col] + " ");
                row++;
                col++;
            }
            System.out.println();
        }
        for (int i = 1; i < m; i++) {
            int row = i, col = 0;
            while (row < m && col < n) {
                System.out.print(a[row][col] + " ");
                row++;
                col++;
            }
            System.out.println();
        }
    }

    //TODO durty code
    private static boolean checkToeplitz(int[][] a) {
        int m = a.length;
        int n = a[0].length;
        boolean result = true;
        for (int i = 0; i < n & result; i++) {
            int row = 0, col = i;
            int temp = a[row][col];
            row++;
            col++;
            while (row < m && col < n) {
                if (temp != a[row][col]) {
                    result = false;
                    break;
                }
                row++;
                col++;
            }
        }
        for (int i = 1; i < m & result; i++) {
            int row = i, col = 0;
            int temp = a[row][col];
            row++;
            col++;
            while (row < m && col < n) {
                if (temp != a[row][col]) {
                    result = false;
                    break;
                }
                row++;
                col++;
            }
        }
        return result;
    }

    public static boolean checkToeplitz2(int[][] a) {
        for (int i = 0; i < a.length - 1; i++) {
            for (int j = i; j < a[0].length - 1; j++) {
                if (a[i][j] != a[i + 1][j + 1]) {
                    return false;
                }
            }
        }
        return true;
    }
}

package com.naresh.h_datastructures.a_recursion;

public class B10_NQueensProblem {
    public static void main(String[] args) {
        int N = 6;
        char[][] a = new char[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                a[i][j] = '-';
            }
        }
        findNQueens(a, 0, N);
    }

    public static void findNQueens(char[][] a, int colIndex, int N) {
        if (colIndex == N) {//TODO print when last column is filled and moved to next column
            System.out.println("Solution:");
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    System.out.print(a[i][j] + " ");
                }
                System.out.println();
            }
            return;//TODO you mush return otherwise indexout of bound
        }

        for (int row = 0; row < N; row++) {
            if (isSafe(a, row, colIndex)) {
                a[row][colIndex] = 'Q';
                findNQueens(a, colIndex + 1, N);
                a[row][colIndex] = '-';
            }
        }

    }

    private static boolean isSafe(char[][] a, int row, int col) {
        int tempR = row, tempC = col;
        while (tempC >= 0) {
            if (a[tempR][tempC] == 'Q')
                return false;
            tempC--;
        }
        tempR = row;
        tempC = col;
        while (tempR >= 0 && tempC >= 0) {
            if (a[tempR][tempC] == 'Q')
                return false;
            tempR--;
            tempC--;
        }
        tempR = row;
        tempC = col;
        while (tempC >= 0 && tempR < a.length) {
            if (a[tempR][tempC] == 'Q') {
                return false;
            }
            tempR++;
            tempC--;
        }
        return true;
    }
    /*
    1 2 3
    4 5 6
    7 8 9
    3%3=0
     */

}

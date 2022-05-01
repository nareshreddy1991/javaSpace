package com.naresh.h_datastructures.j_matrix;

import java.util.Arrays;

public class F_BackTrackingNQueue {
    // Function to check if two queens threaten each other or not
      /* TODO geeks for geeks
      A utility function to check if a queen can
       be placed on board[row][col]. Note that this
       function is called when "col" queens are already
       placeed in columns from 0 to col -1. So we need
       to check only left side for attacking queens */
    private static boolean isSafe(char[][] mat, int r, int c) {
        // return false if two queens share the same column
        for (int i = 0; i < r; i++) {
            if (mat[i][c] == 'Q') {
                return false;
            }
        }

        // return false if two queens share the same `` diagonal
        for (int i = r, j = c; i >= 0 && j >= 0; i--, j--) {
            if (mat[i][j] == 'Q') {
                return false;
            }
        }

        // return false if two queens share the same `/` diagonal
        for (int i = r, j = c; i >= 0 && j < mat.length; i--, j++) {
            if (mat[i][j] == 'Q') {
                return false;
            }
        }

        return true;
    }

    private static void printSolution(char[][] mat) {
        for (char[] chars : mat) {
            System.out.println(Arrays.toString(chars).replaceAll(",", ""));
        }
        System.out.println();
    }

    private static void nQueen(char[][] mat, int r) {
        // if `N` queens are placed successfully, print the solution
        if (r == mat.length) {
            printSolution(mat);
            return;
        }

        // place queen at every square in the current row `r`
        // and recur for each valid movement
        for (int i = 0; i < mat.length; i++) {
            // if no two queens threaten each other
            if (isSafe(mat, r, i)) {
                // place queen on the current square
                mat[r][i] = 'Q';
                System.out.println("Q is placed in " + r + "-" + i);
                // recur for the next row
                nQueen(mat, r + 1);//TODO stop the flow with single solution return here when we get a solution

                // backtrack and remove the queen from the current square
                System.out.println(mat[r][i] + " is replaced in " + r + "-" + i);

                mat[r][i] = '–';
            }else{
                System.out.println("Condition failed for "+r+" "+i);
            }
        }
    }

    public static void main(String[] args) {
        // `N × N` chessboard
        int N = 4;

        // `mat[][]` keeps track of the position of queens in
        // the current configuration
        char[][] mat = new char[N][N];

        // initialize `mat[][]` by `-`
        for (int i = 0; i < N; i++) {
            Arrays.fill(mat[i], '–');
        }

        nQueen(mat, 0);
    }
}

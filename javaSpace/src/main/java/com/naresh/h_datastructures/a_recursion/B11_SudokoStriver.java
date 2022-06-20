package com.naresh.h_datastructures.a_recursion;

public class B11_SudokoStriver {
    public static void main(String[] args) {
        int[][] input = {
                {3, 0, 6, 5, 0, 8, 4, 0, 0},
                {5, 2, 0, 0, 0, 0, 0, 0, 0},
                {0, 8, 7, 0, 0, 0, 0, 3, 1},
                {0, 0, 3, 0, 1, 0, 0, 8, 0},
                {9, 0, 0, 8, 6, 3, 0, 0, 5},
                {0, 5, 0, 0, 9, 0, 6, 0, 0},
                {1, 3, 0, 0, 0, 0, 2, 5, 0},
                {0, 0, 0, 0, 0, 0, 0, 7, 4},
                {0, 0, 5, 2, 0, 6, 3, 0, 0}};
        sudoko(input);
        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < input.length; j++) {
                System.out.print(input[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static boolean sudoko(int[][] input) {
        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < input.length; j++) {
                if (input[i][j] == 0) {
                    for (int k = 1; k <= 9; k++) {
                        if (isSafe(input, i, j, k)) {
                            input[i][j] = k;
                            if (sudoko(input)) {
                                return true;
                            }
                            input[i][j] = 0;
                        }
                    }
                    return false;//TODO
                }
            }
        }
        return true;
    }

    private static boolean isSafe(int[][] input, int row, int col, int k) {
        for (int i = 0; i < input.length; i++) {
            if (input[row][i] == k)
                return false;
            if (input[i][col] == k)
                return false;
            if (input[3 * (row / 3) + i / 3][3 * (col / 3) + i % 3] == k)
                return false;
        }
        return true;
    }
}

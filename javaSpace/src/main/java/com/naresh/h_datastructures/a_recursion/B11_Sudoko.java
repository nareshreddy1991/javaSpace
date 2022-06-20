package com.naresh.h_datastructures.a_recursion;

public class B11_Sudoko {
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
        sudoko(input, 0, 9);
    }

    //TODO my solution
    public static boolean sudoko(int[][] a, int row, int N) {
        if (row == N) {
            System.out.println("Solution:");
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    System.out.print(a[i][j]+" ");
                }
                System.out.println();
            }
            return true;
        }
//        boolean isRowCompleted = true;
        for (int col = 0; col < N; col++) {
            if (isEmpty(a, row, col)) {
//                isRowCompleted = false;
                for (int i = 1; i <= N; i++) {
                    if (isSafe(a, row, col, N, i)) {
                        a[row][col] = i;
                        if (sudoko(a, row, N)) {
                            return true;
                        }
                        a[row][col] = 0;
                    }
                }
                return false;//TODO very imp: - if nothing is safe then return false so it will remove the previous num and try another
            }
        }
//        if (isRowCompleted)
        //todo this will be called once the previous row is completed
            sudoko(a, row + 1, N);//if the row is filled move to next row
        return false;
    }

    private static boolean isSafe(int[][] a, int row, int col, int N, int val) {
        for (int i = 0; i < N; i++) {
            if (a[row][i] == val)
                return false;
        }
        for (int i = 0; i < N; i++) {
            if (a[i][col] == val)
                return false;
        }
        int i = 0, j = 0;
        if (row <= 2)
            i = 0;
        else if (row > 2 && row <= 5)
            i = 3;
        else
            i = 6;

        if (col <= 2)
            j = 0;
        else if (col > 2 && col <= 5)
            j = 3;
        else
            j = 6;
        int k = i + 3;
        int l = j + 3;
        while (i < k) {
            while (j < l) {
                if (a[i][j] == val)
                    return false;
                j++;
            }
            i++;
        }
        return true;
    }

    private static boolean isEmpty(int[][] a, int row, int col) {
        if (a[row][col] == 0)
            return true;
        return false;
    }
}

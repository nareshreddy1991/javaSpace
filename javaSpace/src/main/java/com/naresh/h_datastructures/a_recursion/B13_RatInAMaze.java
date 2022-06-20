package com.naresh.h_datastructures.a_recursion;

public class B13_RatInAMaze {
    public static void main(String[] args) {

        int[][] input = {
                {1, 0, 0, 0, 0},
                {1, 0, 0, 0, 0},
                {1, 0, 1, 1, 1},
                {1, 1, 1, 0, 1},
                {1, 1, 1, 0, 1}
        };
        int[][] result = new int[input.length][input.length];
//        findAllRoots(input, 0, 0, input.length, result);

        result[0][0] = 1;
        findAllRoots2(input, 0, 0, input.length, result);
    }

    /*
    when we are moving in right & down only then there is no way we visit the same node again
    if we travel all four direction then there is possibility of visiting the same node again, so based on result matrix we can avoid it
     */
    private static void findAllRoots(int[][] a, int i, int j, int N, int[][] result) {
        if (i == N - 1 && j == N - 1) {
            result[i][j] = 1;
            System.out.println("Solution:");
            for (int k = 0; k < N; k++) {
                for (int l = 0; l < N; l++) {
                    System.out.print(result[k][l] + " ");
                }
                System.out.println();
            }
            return;
        }
        result[i][j] = 1;
        //move right
        if (j < N - 1 && a[i][j + 1] == 1) {
            findAllRoots(a, i, j + 1, N, result);
        }
        //move down
        if (i < N - 1 && a[i + 1][j] == 1) {
            findAllRoots(a, i + 1, j, N, result);
        }
        result[i][j] = 0;
    }


    private static void findAllRoots2(int[][] a, int i, int j, int N, int[][] result) {
        if (i == N - 1 && j == N - 1) {
            result[i][j] = 1;
            System.out.println("Solution:");
            for (int k = 0; k < N; k++) {
                for (int l = 0; l < N; l++) {
                    System.out.print(result[k][l] + " ");
                }
                System.out.println();
            }
            return;
        }
        //move right
        if (j < N - 1 && a[i][j + 1] == 1 && result[i][j + 1] != 1) {
            result[i][j + 1] = 1;
            findAllRoots2(a, i, j + 1, N, result);
            result[i][j + 1] = 0;
        }
        //move down
        if (i < N - 1 && a[i + 1][j] == 1 && result[i + 1][j] != 1) {
            result[i + 1][j] = 1;
            findAllRoots2(a, i + 1, j, N, result);
            result[i + 1][j] = 0;
        }
//        //move left
        if (j > 0 && a[i][j - 1] == 1 && result[i][j - 1] != 1) {
            result[i][j - 1] = 1;
            findAllRoots2(a, i, j - 1, N, result);
            result[i][j - 1] = 0;
        }
//        //move up
        if (i > 0 && a[i - 1][j] == 1 && result[i - 1][j] != 1) {
            result[i - 1][j] = 1;
            findAllRoots2(a, i - 1, j, N, result);
            result[i - 1][j] = 0;
        }
    }
}

package com.naresh.h_datastructures.j_matrix;

public class F2_CountAllRootsInRatInMaze extends MatrixUtils {
    public static void main(String[] args) {
        int maze[][] = {
                {1, 1, 0, 0},
                {1, 1, 1, 1},
                {0, 1, 0, 1},
                {0, 1, 1, 1}};
        findAllPathsInRatInMaze(maze, 0, 0, 3, 3);
    }

    private static void findAllPathsInRatInMaze(int[][] maze, int rStart, int cStart, int rDest, int cDest) {
        int N = maze.length;
        int[][] output = new int[N][N];//by default all zeros
        output[rStart][cStart] = 1;
        int[] rMove = new int[]{0, 1, 0, -1};
        int[] yMove = new int[]{1, 0, -1, 0};
        int count = ratInMaze(maze, output, rStart, cStart, rMove, yMove, rDest, cDest);
        System.out.println("No of unique paths found:" + count);
    }

    private static int ratInMaze(int[][] input, int[][] output, int r, int c, int[] rMove, int[] cMove, int rDest, int cDest) {
        int N = input.length;
        int count = 0;
        if (r == rDest && c == cDest) {
            System.out.println("Solution:"+count);//TODO printing count here is wrong it will always give zero
            print(output);
            return 1;
        }
        for (int i = 0; i < N; i++) {
            int rNext = r + rMove[i];
            int cNext = c + cMove[i];
            if (isCorrectMove(rNext, cNext, input, output)) {
                output[rNext][cNext] = 1;
                count += ratInMaze(input, output, rNext, cNext, rMove, cMove, rDest, cDest);
                output[rNext][cNext] = 0;
            }
        }
        return count;
    }

    private static boolean isCorrectMove(int r, int c, int[][] input, int[][] output) {
        int N = input.length;
        if (r >= 0 && r < N && c >= 0 && c < N && input[r][c] == 1
                && output[r][c] != 1)//dont visit the same root again
            return true;
        else
            return false;
    }
}

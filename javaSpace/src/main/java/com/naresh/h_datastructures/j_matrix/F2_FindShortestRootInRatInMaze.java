package com.naresh.h_datastructures.j_matrix;

public class F2_FindShortestRootInRatInMaze extends MatrixUtils {
    public static void main(String[] args) {
        int maze[][] = {
                { 1, 1, 1, 1, 1, 0, 0, 1, 1, 1 },
                { 0, 1, 1, 1, 1, 1, 0, 1, 0, 1 },
                { 0, 0, 1, 0, 1, 1, 1, 0, 0, 1 },
                { 1, 0, 1, 1, 1, 0, 1, 1, 0, 1 },
                { 0, 0, 0, 1, 0, 0, 0, 1, 0, 1 },
                { 1, 0, 1, 1, 1, 0, 0, 1, 1, 0 },
                { 0, 0, 0, 0, 1, 0, 0, 1, 0, 1 },
                { 0, 1, 1, 1, 1, 1, 1, 1, 0, 0 },
                { 1, 1, 1, 1, 1, 0, 0, 1, 1, 1 },
                { 0, 0, 1, 0, 0, 1, 1, 0, 0, 1 }};
        findAllPathsInRatInMaze(maze, 0, 0, 7, 5);
    }

    private static void findAllPathsInRatInMaze(int[][] maze, int rStart, int cStart, int rDest, int cDest) {
        int N = maze.length;
        int[][] output = new int[N][N];//by default all zeros
        output[rStart][cStart] = 1;
        int[] rMove = new int[]{0, 1, 0, -1};
        int[] yMove = new int[]{1, 0, -1, 0};
        int minDist = ratInMaze(maze, output, rStart, cStart, rMove, yMove, rDest, cDest, Integer.MAX_VALUE, 0);
        if(minDist!=-1){
            System.out.println("Min Distance is:"+minDist);
        }else {
            System.out.println("No root found");
        }

    }

    private static int ratInMaze(int[][] input, int[][] output, int r, int c, int[] rMove, int[] cMove, int rDest, int cDest,
                                 int minDist, int currDist) {
        int N = input.length;
        if (r == rDest && c == cDest) {
            System.out.println("Solution:");
            print(output);
            return Integer.min(minDist, currDist);
        }
        for (int i = 0; i < 4; i++) {
            int rNext = r + rMove[i];
            int cNext = c + cMove[i];
            if (isCorrectMove(rNext, cNext, input, output)) {
                output[rNext][cNext] = 1;
                minDist = ratInMaze(input, output, rNext, cNext, rMove, cMove, rDest, cDest, minDist, currDist + 1);
                output[rNext][cNext] = 0;
            }
        }
        return minDist;
    }

    private static boolean isCorrectMove(int r, int c, int[][] input, int[][] output) {
        int N = input.length;
        if (r >= 0 && r < N && c >= 0 && c < N && input[r][c] == 1 &&
                output[r][c] != 1)//dont travel on the same root again
            return true;
        else
            return false;
    }
}

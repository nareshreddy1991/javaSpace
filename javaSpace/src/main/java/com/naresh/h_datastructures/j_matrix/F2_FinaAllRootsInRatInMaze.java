package com.naresh.h_datastructures.j_matrix;
/*
Move allowed
forward
backward
up
down

if diagonal?
 */
public class F2_FinaAllRootsInRatInMaze extends MatrixUtils {
    public static void main(String[] args) {
        int maze[][] = {
                {1, 1, 1, 1},
                {1, 1, 1, 1},
                {1, 1, 1, 1},
                {1, 1, 1, 1}};
        findAllPathsInRatInMaze(maze, 0, 0, 3, 3);
    }

    private static void findAllPathsInRatInMaze(int[][] maze, int rStart, int cStart, int rDest, int cDest) {
        int N = maze.length;
        int[][] output = new int[N][N];//by default all zeros
        output[rStart][cStart] = 1;
        //for forward, backward, up and down
//        int[] rMove = new int[]{0, 1, 0, -1};
//        int[] yMove = new int[]{1, 0, -1, 0};
        //for four diagonals , if both combine all
        int[] rMove = new int[]{1, -1, 1, -1};
        int[] yMove = new int[]{1, -1, -1, 1};
        if (ratInMaze(maze, output, rStart, cStart, rMove, yMove, rDest, cDest)) {
            print(output);
        } else {
            System.out.println("no more root found");
        }
    }

    private static boolean ratInMaze(int[][] input, int[][] output, int r, int c, int[] rMove, int[] cMove, int rDest, int cDest) {
        int N = input.length;
        if (r == rDest && c == cDest
                /*&& input[r][c] == 1*/) {
            System.out.println("Solution:");
            print(output);
            return true;
        }
        for (int i = 0; i < N; i++) {
            int rNext = r + rMove[i];
            int cNext = c + cMove[i];
            if (isCorrectMove(rNext, cNext, input, output)) {
//                if (output[rNext][cNext] == 1) //move this code inside isCorrectMove
//                    return false;
                output[rNext][cNext] = 1;
                if (ratInMaze(input, output, rNext, cNext, rMove, cMove,rDest,cDest)) {
//                    return true;//TODO uncomment this line to print only one of the solution
                }
                output[rNext][cNext] = 0;
            }
        }
        return false;
    }

    private static boolean isCorrectMove(int r, int c, int[][] input, int[][] output) {
        int N = input.length;
        if (r >= 0 && r < N && c >= 0 && c < N && input[r][c] == 1
                && output[r][c]!=1)//dont visit the same root again
            return true;
        else
            return false;
    }
}

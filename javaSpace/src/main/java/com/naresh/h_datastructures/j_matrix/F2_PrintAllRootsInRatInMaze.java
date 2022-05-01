package com.naresh.h_datastructures.j_matrix;

import java.util.Iterator;
import java.util.Stack;

/*
Move allowed
forward
backward
up
down

if diagonal?
//TODO incomplete
 */
public class F2_PrintAllRootsInRatInMaze extends MatrixUtils {
    public static void main(String[] args) {
        int maze[][] = {
                { 1, 2, 3 },
                { 4, 5, 6 },
                { 7, 8, 9 }};
        findAllPathsInRatInMaze(maze, 0, 0, 3, 3);
    }

    private static void findAllPathsInRatInMaze(int[][] maze, int rStart, int cStart, int rDest, int cDest) {
        int N = maze.length;
        Stack<Integer> stack=new Stack<>();
        stack.push(maze[rStart][cStart]);
        int[] rMove = new int[]{0, 1};
        int[] yMove = new int[]{1, 0};

        if (ratInMaze(maze, stack, rStart, cStart, rMove, yMove, rDest, cDest)) {
            print2(stack);
        } else {
            System.out.println("no more root found");
        }
    }

    private static boolean ratInMaze(int[][] input, Stack<Integer> output, int r, int c, int[] rMove, int[] cMove, int rDest, int cDest) {
        int N = input.length;
        if (r == rDest && c == cDest) {
            System.out.println("Solution:");
            print2(output);
            return true;
        }
        for (int i = 0; i < 2; i++) {
            int rNext = r + rMove[i];
            int cNext = c + cMove[i];
            if (isCorrectMove(rNext, cNext, input, output)) {
//                if (output[rNext][cNext] == 1) //move this code inside isCorrectMove
//                    return false;
                output.push(input[rNext][cNext]);
                if (ratInMaze(input, output, rNext, cNext, rMove, cMove,rDest,cDest)) {
//                    return true;//TODO uncomment this line to print only one of the solution
                }
                output.pop();
            }
        }
        return false;
    }

    private static boolean isCorrectMove(int r, int c, int[][] input,  Stack<Integer> output) {
        int N = input.length;
        if (r >= 0 && r < N && c >= 0 && c < N
                && input[r][c]!=output.peek())//dont visit the same root again
            return true;
        else
            return false;
    }
    private static void print2(Stack<Integer> stack){
        Iterator<Integer> iterator = stack.iterator();
        while (iterator.hasNext())
            System.out.print(iterator.next());
    }
}

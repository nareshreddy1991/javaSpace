package com.naresh.datastructures.c_stack;

/*
Stack:
    - LIFO
    - Linear data structure
    - basic operation - push, pop, peek
    - push, pop, peek, isEmpty, all take O(1) (no loops in any of these operations)
    - We need to maintain a pointer top at the top of the stack
Application of stack:
    Balancing of symbols
    Infix to Postfix /Prefix conversion, String reversals
    Redo-undo features at many places like editors, photoshop.
    Forward and backward feature in web browsers
    Used in many algorithms like Tower of Hanoi, tree traversals, stock span problem, histogram problem.
Implementations:
    - Using Array
    - Linked List

 */

/*
Stack with Array:
    - Fixed size


 */
public class A_Stack {
    public static void main(String[] args) {
        Stack stack = new Stack();
        System.out.println("is empty:" + stack.isEmpty());
        int pop = stack.pop();
        stack.push(10);
        stack.push(20);
        stack.push(30);
        stack.push(30);
        stack.push(30);
        stack.push(30);
        stack.print();
        System.out.println("size:" + stack.size());
        System.out.println(stack.peek());
        System.out.println(stack.peek());
        System.out.println("size:" + stack.size());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println("size:" + stack.size());
        System.out.println(stack.peek());
    }
}

class Stack {
    private static final int size = 5;
    int top;
    int[] array;

    Stack() {
        top = -1;
        array = new int[size];
    }

    public void push(int data) {
        if (size - 1 == top) { //size == array.length
            System.out.println("Stack is full");
            return;
        }
        array[++top] = data;
    }

    public int pop() {
        if (top == -1) {//top <0
            System.out.println("Stack is empty");
            return -1;
        }
        int element = array[top];
        array[top--] = 0;
        return element;
    }

    public int peek() {
        if (top == -1) {
            System.out.println("Stack is empty");
            return -1;
        }
        return array[top];
    }

    public boolean isEmpty() {
        return top == -1 ? true : false;
    }

    public int size() {
        return top != -1 ? top + 1 : 0;
    }

    public void print() {
/*        for (int i = 0; i < top + 1; i++)
            System.out.println(array[i]);*/
        System.out.println("printing");
        for (int i = top; i >= 0; i--)
            System.out.println(array[i]);
    }

}

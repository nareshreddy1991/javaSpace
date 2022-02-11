package com.naresh.collection.list;

import java.util.Stack;
import java.util.Vector;

/*
public
class Stack<E> extends Vector<E>

    -- Since stack extends Vector, data will be stored internally in Object[]
    -- Similar to Vector, its initial capacity is 10(with default constructor)
    -- Similar to Vector, its size also increased by 100%
    -- Thread safe, its internally use methods from Vector
    -- if we dont need thread safety we can use ArrayDeque
    -- Stack is not having any fixed size, so why StackOverflowError?
TODO? is below is true
in Memory, stack occupy the upper part of the memory and start filling towards down, ------------->
Heap occupy the lower part of the memory and start filling to up   <--------------- when both memory collide we get this error.
 in JVM memory architecture, if stack memory area is full then stackOverFlowError will comes

 */
public class C_Stack {
    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        System.out.println("Capacity:" + stack.capacity());
        for (int i = 0; i < 11; i++)
            stack.push(10);
        System.out.println("Capacity:" + stack.capacity());

        stack.push(20);
        Integer peek = stack.peek();//display the top element in the stack, throw EmptyStackException
        stack.pop(); //throws EmptyStackException
        int search = stack.search(20);//index of the element
        boolean empty = stack.empty();

        //add all the methods from Vector
        stack.add(2, 25);// TODO why is stack is allowing to add in between, LIFO??
    }
}

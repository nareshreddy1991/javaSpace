package com.naresh.h_datastructures.c_stack;

import java.util.Stack;

/*
Question: Design a Data Structure SpecialStack that supports all the stack operations like push(), pop(), isEmpty(), isFull()
and an additional operation getMin() which should return minimum element from the SpecialStack. All these operations of SpecialStack must be O(1).
To implement SpecialStack, you should only use standard Stack data structure and no other data structure like arrays, list, . etc.

When we insert 18, both stacks change to following.
Actual Stack
18 <--- top
Auxiliary Stack
18 <---- top

When 19 is inserted, both stacks change to following.
Actual Stack
19 <--- top
18
Auxiliary Stack
18 <---- top
18

When 29 is inserted, both stacks change to following.
Actual Stack
29 <--- top
19
18
Auxiliary Stack
18 <---- top
18
18

When 15 is inserted, both stacks change to following.
Actual Stack
15 <--- top
29
19
18
Auxiliary Stack
15 <---- top
18
18
18

When 16 is inserted, both stacks change to following.
Actual Stack
16 <--- top
15
29
19
18
Auxiliary Stack
15 <---- top
15
18
18
18
Complexity Analysis:
Time Complexity:
For insert operation: O(1) (As insertion ‘push’ in a stack takes constant time)
For delete operation: O(1) (As deletion ‘pop’ in a stack takes constant time)
For ‘Get Min’ operation: O(1) (As we have used an auxiliary stack which has it’s top as the minimum element)
Auxiliary Space: O(n).
Use of auxiliary stack for storing values.

We can improve space optimization - below commented code was before optimization,
after optimization time complexity is O(n) in the worst case in other case it will give better performance
*/
public class C_StackProblem1 {
    public static void main(String[] args) {
        System.out.println("First approach");
        CustomStack stack = new CustomStack();
        stack.push(10);
        stack.push(15);
        stack.push(12);
        stack.push(5);
        stack.push(5);
        System.out.println(stack.getMin());
        stack.pop();
        System.out.println(stack.getMin());
        stack.pop();
        System.out.println(stack.getMin());
        System.out.println("Second approach");
        CustomStack1 stack1 = new CustomStack1();
        System.out.println(stack1.push(21));
        System.out.println(stack1.push(19));
        System.out.println("push:" + stack1.push(23));
        System.out.println("min:" + stack1.getMin());
        System.out.println("pop:" + stack1.pop());
        System.out.println("min:" + stack1.getMin());
        System.out.println("pop:" + stack1.pop());
        System.out.println("min:" + stack1.getMin());
    }
}

//all operation time O(1),  time complexity O(n)
class CustomStack extends Stack<Integer> {
    Stack<Integer> tempStack = new Stack<>();

    @Override
    public Integer push(Integer data) {
        super.push(data);
        if (tempStack.isEmpty()) {
            tempStack.push(data);
            return data;
        }
        /*if (data < tempStack.peek())
            tempStack.push(data);
        else {
            tempStack.push(tempStack.peek());
        }*/
        if (data <= tempStack.peek())
            tempStack.push(data);
        return data;
    }

    @Override
    public Integer pop() {
        Integer pop = super.pop();
//        tempStack.pop();
        Integer peek = tempStack.peek();
        if (pop.equals(peek))
            tempStack.pop();
        return pop;
    }

    public Integer getMin() {
        return tempStack.peek();
    }
}

/*
Improved Approach: all operation O(1) & space complexity O(1)
while pushing - (value*dummy)+min
while pop - totalVal/dummy;
getMin - totalVal%dummy; (just reminder)

 */
class CustomStack1 extends Stack<Integer> {

    private static Integer DUMMY = 9999;
    private Integer min = null;

    @Override
    public Integer push(Integer data) {
        if (min == null || min > data)
            min = data;
        int val = (data * DUMMY) + min;
        super.push(val);
        return val;
    }

    @Override
    //TODO - if we have overriden peek, if it is having any calculation then it will not work
    //TODO rather than extending Stack, just create stack object
    public Integer pop() {//TODO pop value is wrongly because pop internally calls peek, so the logic inside peek executes the it return the value
        if (isEmpty())
            min = null;
        Integer pop = super.pop();
        System.out.println("poped value:" + pop);
        min = (super.peek() / DUMMY) % DUMMY;//next val min is current min
        return pop / DUMMY;
    }

  /*  @Override
    public Integer peek() {
        Integer pop = super.peek();
        System.out.println("peek value:"+pop);
        return pop / DUMMY;
    }*/

    public Integer getMin() {
        return super.peek() % DUMMY;
    }

}
package com.naresh.i_algorithms.a_basic;

import java.util.Arrays;
import java.util.regex.Pattern;
import java.util.stream.Stream;

/*
Jai sree rama
 */
public class A_BasicWarmup {
    public static void main(String[] args) {
        int val = 256;
        System.out.println(val / 10);//25 - if you want to get the all digits except last digit when divide by 10
        System.out.println(val % 10);//6 - if you want to get the last digit when divide by 10
        System.out.println(1 / 2);//0
        float lval = 1f / 2; //(1f/2f-0.5, 1f/2-0.5, 1/2f-0.5, 1/2-0.0)
        System.out.println(lval);
        System.out.println(0 / 2);//0
        System.out.println(4 % 5);//(1%2-1, 2%3-2, 4%5-4)
        System.out.println(0 % 2);//0

        System.out.println("even or odd");
        int number = 23;
        if (number % 2 == 0) System.out.println("Even");
        else if (number % 2 == 1) System.out.println("Odd");

        System.out.println("Given the number is prime or not - number divided by 1 & itself is prime");
        number = 10; //todo thsi is not the best way, find different ways
        boolean isPrime = true;
        for (int i = 2; i <= number / 2; i++) {
            if (number % i == 0) {
                System.out.println("Not a Prime number");
                isPrime = false;
                break;
            }
        }
        if (isPrime) System.out.println("Its a prime number");

        //reverse the given number
        System.out.println("Reverse given number");
        number = 254;
        int result = 0;
        while (number > 0) {
            result = (result * 10) + number % 10; //4-> (4*10)+5->45
            number = number / 10;
        }
        System.out.println(result);

        System.out.println("Given number is amstrong or not 153=1(3)+2(3)+3(3)");
        number = 153;
        int originalNumber = number;
        int total = 0;
        while (number > 0) {
            int temp = number % 10;
//            total += temp * temp * temp;
            total += Math.pow(temp, 3);
            number = number / 10;
        }
        if (originalNumber == total) System.out.println("Given number is amstrong");
        else System.out.println("Given number is not amstrong");

        System.out.println("Generate fibonaci series untill given number");
        number = 25; // 0 1 1 2 3 5..
        int f1 = 0, f2 = 1, f = 0;
        while (f < number) {
            f = f1 + f2;
            f1 = f2;
            f2 = f;
            System.out.print(f + " ");
        }

        System.out.println("Find the factorial of the given number");
        number = 5; //5*4*3*2*1
        result = 1;
        while (number > 0) {
            result *= number--;
        }
        System.out.println("factorial:" + result);

        System.out.println("Swap two number with out using another variable");
        int num1 = 20, num2 = 10;
        num1 = num1 + num2;//30
        num2 = num1 - num2;//10
        num1 = num1 - num2;//20
        System.out.println(num1 + "-" + num2);




    }
}

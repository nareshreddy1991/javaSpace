package com.naresh.a_javabasics;

import java.util.Arrays;
import java.util.Map;

public class I_SystemClass {
    public static void main(String[] args) {
        Map<String, String> getenv = System.getenv();//all system env variables
        String getenv1 = System.getenv("");
        long l = System.currentTimeMillis();//1000ms =1s  ,1s= 100 cr nanos
        long l1 = System.nanoTime(); // 1ms = 10L nanos

        //arraycopy
        int[] source = new int[]{5, 6, 8, 5, 4, 8, 62, 5, 8, 6};
        int[] target = new int[]{1, 1, 1, 1, 1, 2, 2, 2, 2};
        System.arraycopy(source, 0, target, 0, 5);//copy 5 elements from source array to target
        System.out.println("source:");
        Arrays.stream(source).forEach(System.out::println);
        System.out.println("Targets");
        Arrays.stream(target).forEach(System.out::println);

        //arraycopy usecase - remove 3rd position and shift all the elements left
        int[] source2 = new int[]{5, 6, 8, 5, 4, 8, 6, 5, 8, 6};
        System.arraycopy(source2, 3, source2, 2, 7);
        source2[source2.length-1]=0;//making last element as 0
        System.out.println("use case");
        Arrays.stream(source2).forEach(System.out::print);
        //array copy -- add element in 3rd position the array in 3rd position and shift right
        int[] source3 = new int[]{5, 6, 8, 5, 4, 8, 6, 5, 8, 6,0,0};
        System.arraycopy(source3,4,source3,5,source3.length-5);
        source3[3]=1; //new element
        System.out.println("user case shift right");
        Arrays.stream(source3).forEach(System.out::print);


    }
}

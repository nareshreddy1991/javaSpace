package com.java9to11;

import com.defogTech.concurrency.ThreadUtils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

/*
Jshell - interative programming is possible now in java
private Methods in interfaces - large default methods can be split into smaller peaces
java Module System -- moduling source code
Process API improvements


 */
public class A_J9_Features {
    public static void main(String[] args) throws FileNotFoundException {
        //TODO creating immutable collections
        List<Integer> immutableList = List.of(1, 2, 3);
        List<Object> immutableList2 = List.of();
        Set<Object> of = Set.of();
        Map<String, String> key = Map.of("key", "Value", "key2", "Value2");

        //in java8 we need to do like this
        List<String> u = new ArrayList<>();
        List<String> strings = Collections.unmodifiableList(u);
        u.add("10"); //allowed
//        strings.add("20");// not allowed
//        immutableList.add(10); UnsupportedOperationException

        //TODO java7 Try with resource improvements
        try {
            try (BufferedReader reader1 = new BufferedReader(new FileReader("journaldev.txt"))) {
                System.out.println(reader1.readLine());
            } catch (IOException e) {
            }
            //java 9

            BufferedReader reader1 = new BufferedReader(new FileReader("journaldev.txt"));
            try (reader1) {
                System.out.println(reader1.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
        }
        //make sure reader1 is closed properly



    }
}

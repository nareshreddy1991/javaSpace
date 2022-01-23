package com.naresh.java10;

import java.util.Optional;

public class C_Optional {
    public static void main(String[] args) {
        String value=null;
        Optional<String> optional = Optional.ofNullable(value);
//        String s = optional.get();
//        System.out.println(s);
        String s = optional.orElseThrow(); //same as get method
        String s1 = optional.orElseThrow(RuntimeException::new);

    }
}

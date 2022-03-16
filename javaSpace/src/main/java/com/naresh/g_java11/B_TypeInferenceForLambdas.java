package com.naresh.g_java11;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
/*

But why is this needed when we can just skip the type in the lambda?
If you need to apply an annotation just as @Nullable, you cannot do that without defining the type.

Limitation of this feature – You must specify the type var on all parameters or none.
Things like the following are not possible:

(var s1, s2) -> s1 + s2 //no skipping allowed
(var s1, String y) -> s1 + y //no mixing allowed

var s1 -> s1 //not allowed. Need parentheses if you use var in lambda.
 */
public class B_TypeInferenceForLambdas {
    public static void main(String[] args) {
        //JEP 323 allows var to be used to declare the formal parameters of an implicitly typed lambda expression.
        Function<String, Integer> function1 = (var v1) -> Integer.valueOf(v1);
        Function function = (var v1) -> v1;//v1 is treated as object
        BiFunction<String, Integer, String> function2 = (var v1, var v2) -> v1 + v2;

        var list = List.of(1, 2, 3);
        list.stream()
                .map((var v) -> v + v)
                .map(function)
                .forEach(System.out::println);


    }
}

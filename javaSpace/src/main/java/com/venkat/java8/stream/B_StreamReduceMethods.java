package com.venkat.java8.stream;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
The Key Concepts: Identity, Accumulator and Combiner
Before we look deeper into using the Stream.reduce() operation, let's break down the operation's participant elements into separate blocks. That way, we'll understand more easily the role that each one plays.

Identity – an element that is the initial value of the reduction operation and the default result if the stream is empty
Accumulator – a function that takes two parameters: a partial result of the reduction operation and the next element of the stream
Combiner – a function used to combine the partial result of the reduction operation when the reduction is parallelized or
 when there's a mismatch between the types of the accumulator arguments and the types of the accumulator implementation
 */
public class B_StreamReduceMethods {
    public static void main(String[] args) {
        List<String> strList = List.of("naresh", "Lalitha", "Chareesh", "Tinku", "naresh");
        List<String> intList = List.of("10", "20", "30", "40");
        List<Integer> integerList = List.of(10, 20, 30, 40, 50);
        List<Integer> integerList2 = List.of(10, 20, 30, 40, 50, 10, 20, 30, 40, 50, 10, 20, 30, 40, 50);

        //Collectors.reducing()
        Integer sum = integerList.stream()//Stream<Integer>, if stream is empty then return 0(identity value)
                .collect(Collectors.reducing(0, (subTotal, element) -> Integer.sum(subTotal, element)));//sum, max etc

        Optional<Integer> collect4 = integerList.stream()
                .collect(Collectors.reducing(Integer::sum));

        //reduce
        Integer sum2 = integerList.stream() //if strema is empty then identity value will be returned
                //sum of all the elements + identity
                .reduce(10, (c, v) -> Integer.sum(c, v));//params - identity,accumulator(BinaryOperator)
        System.out.println("sum2:" + sum2);

        Optional<Integer> sum3 = integerList.stream()// since there is identity value it return Optional value
                //sum of all the element in the list
                .reduce((a, b) -> Integer.sum(a, b));//params - BinaryOperator
        System.out.println("sum3:" + sum3);

        Integer reduce1 = integerList.stream()
                .reduce(0, (c, v) -> c + v, (a, b) -> null);//params - identify, accumulator(BiFunction), combiner(BinaryOperator)
        System.out.println("reduce1:" + reduce1); // result 150 TODO combiner did not play any role in single stream

        // combiner is used in parallel streams, once it split the stream to smaller chunks then combiner combines the results
        Integer total = integerList2.stream()
                .reduce(0, (subTotal, element) -> subTotal + element);
        System.out.println("Total:" + total); //450

        Integer total1 = integerList2.stream()
                .parallel()
                .reduce(0, (subTotal, element) -> subTotal + element);
        System.out.println("total1:" + total1);//450

        Integer total2 = integerList2.stream()
                .parallel()
                .reduce(0, (subTotal, element) -> subTotal + element, (a, b) -> a + b);
        System.out.println("total2:" + total2);//450 // TODO with & with out combiner we got same results in parallels stream but if we change the combiner then results are changing

        //Below code will not compile, identity type is different, expected str but it is int, we can fix it by adding combiner
        // here accumulator params types are different, so we need combiner to fix this issue
/*        intList.stream()//stream<String>
                .reduce(0, (sum1, value) -> sum1 + Integer.valueOf(value));*/

        intList.stream()//stream<String>
                .reduce(0, (sum1, value) -> sum1 + Integer.valueOf(value), Integer::sum);

        integerList.stream()
                .collect(Collectors.reducing("0",(identify, value)-> identify+value.toString())); //no combiner no error todo how? types are different

        //below code will not compile
        // we have a stream of User objects, and the types of the accumulator arguments are Integer and User. However, the accumulator implementation is a sum of Integers,
        // so the compiler just can't infer the type of the user parameter.
        /*List<User> userList = List.of(new User("a", 10));
        int result = userList.stream()
                .reduce(0, (partialAgeResult, user) -> partialAgeResult + user.getAge());*/
        //fix add combiner
        List<User> userList = List.of(new User("a", 10));
        int result = userList.stream()
                .reduce(0, (partialAgeResult, user) -> partialAgeResult + user.getAge(), Integer::sum);
        /*
TODO To put it simply, if we use sequential streams and the types of the accumulator arguments and the types of its implementation match, we don't need to use a combiner.
         */

        //handling exceptions
        int divider = 1;
        integerList.stream()
                .reduce(0, (identify, value) -> divide(identify, divider) + divide(value, divider));
/* custom types
Rating averageRating = users.stream()
  .reduce(new Rating(),
    (rating, user) -> Rating.average(rating, user.getRating()),
    Rating::average);
 */


        integerList.stream()
                .mapToInt(e -> e)
                .reduce(0, (i, v) -> i + v);

    }

    private static int divide(int value, int factor) {
        int result = 0;
        try {
            result = value / factor;
        } catch (ArithmeticException e) {
        }
        return result;
    }
}

class User {
    String name;
    int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}
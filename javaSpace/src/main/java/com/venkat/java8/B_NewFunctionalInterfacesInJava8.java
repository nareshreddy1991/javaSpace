package com.venkat.java8;

import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
New Functional interfaces introduced in java8
it has only abstract method & few default and static methods
annotated with @functionalinterface, these support lambda's
 */
public class B_NewFunctionalInterfacesInJava8 {
    public static void main(String[] args) {
        Map<String, String> m= new HashMap<>();
        m.put(null, null);
        m.containsKey(null);
        Function<String, Integer> dd= String::length;
        dd.apply(null);
        //TODO Function
        Function<String, Integer> function1 = s -> Integer.valueOf(s); //Function<T,R>
        Function<Double, String> function2 = s -> String.valueOf(s); //Function<T,R>
        Function<Float, Double> function3 = s -> Double.valueOf(s); //Function<T,R>
        Integer v1 = function1.apply("10");
        //compose: it will create composed Function, first function2 will be evaluated its output becomes input to the function1
        //Function<? super V, ? extends T> before
        Function<Double, Integer> compose1 = function1.compose(function2);// function2 > function1
        Function<Float, Integer> compose2 = function1.compose(function2).compose(function3); // function3 > function2 > function1
        //andThen : it will create composed function, function3 will be evaluated first, its output becomes input to function2
        Function<Float, String> andThen1 = function3.andThen(function2);
        Function<Float, Integer> andThen3 = function3.andThen(function2).andThen(function1);
        //Function.identity() - its input and return values are same, it's a static method
        Function<Object, Object> identity = Function.identity();
        Map<Integer, Integer> collect = Stream.of(10, 20)
                .collect(Collectors.toMap(e -> e, Function.identity()));
        List<Integer> collect1 = Stream.of(10, 20)
                .map(Function.identity()) //same as e->e
                .map(e -> e) // same as Function.identity()
                .collect(Collectors.toList());

        //TODO Supplier
        Supplier<String> supplier1 = () -> {
            return "";
        };
        String s = supplier1.get();

        //TODO Consumer
        Consumer<String> consumer1 = s1 -> System.out.println(s1);
        Consumer<String> consumer2 = s1 -> System.out.println(s1 + "Hello");
        consumer1.accept("");
        //it creates composed consumer, consumer 1 will be executed first then consumer2 , if any exception one consumer other will be stoped
        Consumer<String> consumer3 = consumer1.andThen(consumer2);
        consumer3.accept("naresh");
        //TODO Predicate
        Predicate<String> p = v -> v.equals("");
        Predicate<String> p2 = v -> v.equals("");
        boolean a = p.test("a");
        Predicate<String> negate = p.negate();
        Predicate<String> and = p.and(p2);
        Predicate<String> or = p.or(p2);
        BiPredicate<String, String> p3 = (v3, v2) -> v3.equals(v2);
        //TODO Unary/Binary
        //input type and output types are same
        //Represents an operation on a single operand that produces a result of the same type as its operand.
        // This is a specialization of Function for the case where the operand and result are of the same type.
        UnaryOperator<String> u1 = u -> { //Function <T, T>
            return u;
        };
        u1.apply("");

        BinaryOperator<String> binaryOperator = (val1, val2) -> { // similar to BiFunction<T,T,T>
            return val1 + val2;
        };
        String apply = binaryOperator.apply("hello", "naresh");// it will take same type of args and return the same type
        BinaryOperator<String> maxLengthString = BinaryOperator.maxBy(//minBy is also there
                Comparator.comparingInt(String::length));
        String sx = maxLengthString.apply("two", "three");

        //TODO few other interfaces introduced in java8
        /*                  Int Version            Long Version             Double Version
        BinaryOperator      IntBinaryOperator       LongBinaryOperator    DoubleBinaryOperator
        Consumer            IntConsumer             Long..                ..
        Function            IntFunction             ..                     ..
        Predicate           IntPredicate            ..
        Supplier            IntSupplier             ..                     ..
         */

        /*
        Int vs Double vs Long
            Double                      Int                     Long
        DoubleToIntFunction         IntToDoubleFunction     LongToDoubleFunction (it will convert Long to double)
        DoubleToLongFunction        IntToLongFunction       LongToIntFunction
        DoubleUnaryOperator         IntUnaryOperator        LongUnaryFunction
         */

        /*
        Bi*
        BiFunction
        BiConsumer
        BiPredicate
        BinaryOperator
         */

        /*
        ObjDoubleConsumer (it will accept Obj, double value and doesn't return anything
        ObjIntConsumer
        ObjLongConsumer
         */

        /*
        To*
        ToDoubleBiFunction  (it takes T, U return double)
        ToDoubleFunction
        ToIntBiFunction
        ToIntFunction
        ToLongBiFunction
        UnaryOperator
         */

        /*
        public interface Function<T, R> {

        default <V> Function<V, R> compose(Function<? super V, ? extends T> before) {
            Objects.requireNonNull(before);
            return (V v) -> apply(before.apply(v));
        }

         */

        Function<Number, String> f1 = n -> String.valueOf(n);
        Function<Object, Integer> f2 = n -> 10; // Function<? super V,

        Function<String , String> compose = f1.compose(f2);

    }
}

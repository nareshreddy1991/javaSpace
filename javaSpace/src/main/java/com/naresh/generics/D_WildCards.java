package com.naresh.generics;

import java.util.ArrayList;
import java.util.List;

/*
In generic code, the question mark (?), called the wildcard, represents an unknown type. The wildcard can be used in a variety of situations: as the type of a parameter,
 field, or local variable; sometimes as a return type (though it is better programming practice to be more specific). The wildcard is never used as a type argument
  for a generic method invocation, a generic class instance creation, or a supertype.

List<Number> is mor restrictive but List<? extends Number> allowed Integer, Double, Float etc
<? extends Number> meaning anything that extends Number( here extends used for both classes and interfaces)

Upper Bounded wild card : List<? extends Number> ( subclasses of Number ex: Integer, Flat, Double)
Unbounded wild card: List<?>
Lower Bounded wild card: List<? super Integer> (Super classes of Integer ex: Integer, Number , Object)
 */
public class D_WildCards {
    public static void main(String[] args) {
        D_WildCards.unbounded();
    }

    //TODO upper bound
    //without using wildcard
    public <T extends Number> double processSum1(List<T> list) {
        double sum = 0.0;
        for (T elem : list) {
            sum += elem.doubleValue();
        }
        return sum;
    }

    //using wild card
    public static double processSum(List<? extends Number> list) {
        double sum = 0.0;
        for (Number elem : list) {
            sum += elem.doubleValue();
        }
        return sum;
    }


    /*
    //TODO unbounded wild card, these are useful in below cases
    - If you are writing a method that can be implemented using functionality provided in the Object class.
    - When the code is using methods in the generic class that don't depend on the type parameter. For example, List.size or List.clear.
 In fact, Class<?> is so often used because most of the methods in Class<T> do not depend on T
     */

    public static void unbounded() {
        //It's important to note that List<Object> and List<?> are not the same. You can insert an Object, or any subtype of Object, into a List<Object>.
        // But you can only insert null into a List<?>.
        List<?> list = new ArrayList<>();
 /*       list.add(new Object());  TODO not allowed we can only insert null
        list.add(10);
        list.add("");*/
        list.add(null);
//        list.add(new Box<>(10));

        List<Integer> intLIst = new ArrayList<>();
        intLIst.add(10);//OK
        List<?> list2 = intLIst;
        for (Object o : list2) {
            System.out.println(o);
        }
    }

    //below method cannot print List<Integer>, List<String>, List<Double>
    public static void printList1(List<Object> list) {
        for (Object elem : list)
            System.out.println(elem + " ");
        System.out.println();
    }

    // this allows all types like List<Integer>, List<String>, List<Double>
    public static void printList2(List<?> list) {
        for (Object elem : list)
            System.out.println(elem + " ");
        System.out.println();
    }

    //TODO lower bounded
    //This method is allowed to add List<Integer> List<Number> and List<Object>
    public static void addNumbers(List<? super Integer> list) {
        for (int i = 1; i <= 10; i++) {
            list.add(i);
        }

        //
        List<? super Integer> intList1 = new ArrayList<Integer>();
        List<? super Integer> intList2 = new ArrayList<Number>();
        List<? super Integer> intList3 = new ArrayList<Object>();
//        List<? super Integer> intList4 = new ArrayList<Double>(); //not allowed
    }
}

class WildcardError {
    /*
    In some cases, the compiler infers the type of a wildcard. For example, a list may be defined as List<?> but, when evaluating an expression, the compiler
    infers a particular type from the code.This scenario is known as wildcard capture.
     */
    void foo(List<?> i) {
//        i.set(0, i.get(0));// compilation error
        fooHelper(i);
    }

    private <T> void fooHelper(List<T> list) {
        list.set(0, list.get(0));
    }
}

/*
TODO Type Erasure:
Generics were added to Java to ensure type safety. And to ensure that generics won't cause overhead at runtime, the compiler applies a process called type
 erasure on generics at compile time.

Type erasure removes all type parameters and replaces them with their bounds or with Object if the type parameter is unbounded.
 This way, the bytecode after compilation contains only normal classes, interfaces and methods, ensuring that no new types are produced.
 Proper casting is applied as well to the Object type at compile time.

 public <T> List<T> genericMethod(List<T> list) {
    return list.stream().collect(Collectors.toList());
}
After compilation
// for illustration
public List<Object> withErasure(List<Object> list) {
    return list.stream().collect(Collectors.toList());
}
for bounded
public <T extends Building> void genericMethod(T t) {
    ...
}
after compilation
public void genericMethod(Building t) {
    ...
}
 */

/*
TODO Generics and Primitive Data Types
One restriction of generics in Java is that the type parameter cannot be a primitive type.
For example, the following doesn't compile:
List<int> list = new ArrayList<>();
list.add(17);
To understand why primitive data types don't work, let's remember that generics are a compile-time feature, meaning the type parameter is erased and all generic
 types are implemented as type Object.

Let's look at the add method of a list:
List<Integer> list = new ArrayList<>();
list.add(17);
The signature of the add method is:
boolean add(E e);
and will be compiled to:
boolean add(Object e);
Therefore, type parameters must be convertible to Object. Since primitive types don't extend Object, we can't use them as type parameters.
However, Java provides boxed types for primitives, along with autoboxing and unboxing to unwrap them:
So, if we want to create a list that can hold integers, we can use this wrapper:

List<Integer> list = new ArrayList<>();
list.add(17);
int first = list.get(0);
The compiled code will be the equivalent of the following:

List list = new ArrayList<>();
list.add(Integer.valueOf(17));
int first = ((Integer) list.get(0)).intValue();
Future versions of Java might allow primitive data types for generics. Project Valhalla aims at improving the way generics are handled.
 The idea is to implement generics specialization as described in JEP 218.
 */






/*
    default <V> Function<V, R> compose(Function<? super V, ? extends T> before) { //TODO how??
        Objects.requireNonNull(before);
        return (V v) -> apply(before.apply(v));
    }
 */

/*
TODO
(T)super.invoke();
 */
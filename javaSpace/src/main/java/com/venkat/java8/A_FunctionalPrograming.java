package com.venkat.java8;

/*
*************************
>> What is functional Programming?
    Basically, functional programming is a style of writing computer programs that treat computations as evaluating mathematical functions.
    So, what is a function in mathematics? A function is an expression that relates an input set to an output set
>>Categorization of Programming Paradigms
    - imperative approach: It simply describes the control flow of computation Ex: C, c++, java
        1) Procedural program paradigm
        2) Object Orient program paradigm
        3) Parallel Processing approach
    - Declarative approach: programs specify what is to be done Ex: Haskell
        1) Logic Programming paradigm
        2) Functional program paradigm
        3) Data processing approach
>>Categorization of Programming Languages:
    - Pure Functional languages: Ex: haskell
    - Impure functional languages: its support functional & OOP Ex: java, Scala, kotlin
***************************
>>Fundamental Principles and Concepts:
        >First-Class and Higher-Order Functions
            - First Class Function: A programming language is said to have first-class functions if it treats functions as first-class citizens. Basically,
it means that functions are allowed to support all operations typically available to other entities.These include assigning functions to variables,
passing them as arguments to other functions, and returning them as values from other functions.
        > Higher order functions: Above property makes it possible to define higher-order functions in functional programming.
 Higher-order functions are capable of receiving function as arguments and returning a function as a result.
Ex: Traditionally it was only possible to pass functions in Java using constructs like functional interfaces or anonymous inner classes
Collections.sort(numbers, new Comparator<Integer>() {
    @Override
    public int compare(Integer n1, Integer n2) {
        return n1.compareTo(n2);
    }
});
in java8,
    Collections.sort(numbers, (n1, n2) -> n1.compareTo(n2));
Definitely, this is more concise and understandable. However, please note that while this may give us the impression of using functions as first-class citizens in Java, that's not the case.
Behind the syntactic sugar of lambda expressions, Java still wraps these into functional interfaces. Hence, Java treats a lambda expression as an Object,
which is, in fact, the true first-class citizen in Java.

>> Pure Functions:
    - Pure functions shouldn't have any side effects, that means it will not change any local or global state/variables, it will not update data base etc.
>> Immutability
    - Immutability is one of the core principles of functional programming, and it refers to the property that an entity can't be modified after being instantiated
>> Referential transperancy ??
***************************
>>Functional Programming Techniques
    > Function Composition: Function composition refers to composing complex functions by combining simpler functions.
Function interface also provides two default methods, compose and andThen, which will help us in function composition.
default methods and, or, and negate in the Predicate interface provides function composition
    >

****************************
Why Functional programming?
    - Mutability

>>Lambda's:
any function has below things, so Name & return types were removed in lambda
    - Name  - Can be anonymous
    - Parameters
    - Body
    - return type  - Can be inferred
Lambda can be passed to very old methods - backward compatibility (java doomed by backward compatibility), they are able to achieve this by functional interfaces(single  method interfaces)
Lambda is not a syntax sugar - it will not create any inner classes, if we have more anonymous class - it will take more memory, it will more time load and it will take more time garbage collect
TODO it internally  use invoke dynamics concepts??
*******************
>> Declarative vs Imperative
    Declarative
        for(int i=0;i<arr.size();i++)
            sop(arr[i]);
    Imperative
         arr.forEach(System.out::println);
dont write lot of code in lambda


TODO
new classes in java8


  */
public class A_FunctionalPrograming {
}

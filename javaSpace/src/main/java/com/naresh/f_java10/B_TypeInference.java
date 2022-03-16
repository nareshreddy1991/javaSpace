package com.naresh.f_java10;

import java.util.List;

public class B_TypeInference {
    public static void main(String[] args) {
        print();
    }

    //var can be used only in local variables
    //var is not a keyword, so we can declare variable name with "var"
    //type inference happens at compile time so no impact in the performance
    private static void print() {
//        var a; // declaration and initialization should be done at same time
        var var = 10;//TODO we got var in java 10
        int n2 = 10;
        if (var == n2) System.out.println("both are same");//both are same
        else System.out.println("both are not same");

        var name1 = new String("Naresh");
        var name2 = "Naresh";
        if (name1 == name2) System.out.println("names are same");
        else System.out.println("name is not same");
    }

    /*
    Usage allowed as :
    Local variables with initializers
    Indexes in the enhanced for-loop
    Locals declared in a traditional for-loop
     */
    private static void print2() {
        var blogName = "howtodoinjava.com";
        List<Integer> dataList = List.of(1, 2, 3, 4);
        for (var object : dataList) {
            System.out.println(object);
        }

        for (var i = 0; i < dataList.size(); i++) {
            System.out.println(dataList.get(i));
        }
    }

    /*
    Usage NOT allowed as :
Method parameters
Constructor parameters
Method return types
Class fields
Catch formals (or any other kind of variable declaration)
var does not impact performance":
Remember, in Java, the types are not inferred at runtime but at compile time. That means the resulting bytecode is the same as with explicit type declaration –
 it does include the information about the type. That means no extra processing at runtime.
     */

}

package com.naresh.javabasics;

import java.io.FileNotFoundException;

/*
Checked vs Uncheckeds
All subclasses of RuntimeException are unchecked exceptions.
all subclasses of Throwable, other than RuntimeException and Error, are checked exceptions. Throwable/Exception class is also checked Exception
 */
public class E_ExceptionHandling {
    public static void main(String[] args) {
        MathsCals m1 = new MathsCals();
        try {
            m1.calculate1();
            System.exit(0);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {//finally will be executed in all cases exception when System.exit(0) is called
            System.out.println("finally called");
        }
    }
}

class MathsCals {
    //we can declare exception in throws even though we are not throwing these exceptions
    public void calculate1() throws Exception {//caller  must catch this exception
    }

    public void calculate2() {
        try {
            System.out.println("");
//        } catch (ArithmeticException|Exception e) {// it shouldn't have any parent child relationship
        } catch (RuntimeException e) {// you can catch RuntimeException even its not been thrown because compile doesn't know at compile time
        }
    }

    public void calculate3() {
        try {
            System.out.println("");
//        } catch (UserDefinedException e) {//compiler error - because checked exception is never thrown in the try block
        } catch (Exception e) {//Exception/Throwable is checked Exception but compiler will not complaint because, it could handle checked & unchecked exception
        }
    }
}

class UserDefinedException extends Exception {


}
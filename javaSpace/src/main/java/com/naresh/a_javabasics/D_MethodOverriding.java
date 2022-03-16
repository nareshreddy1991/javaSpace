package com.naresh.a_javabasics;

import java.io.FileNotFoundException;

/*
RuntimeException can be narrowed/widen, any combination works fine
For Checked Exception only we can narrow the scope.(Parent throws Exception but child doesn't-- allowed, reverse is not allowed)

 TODO Dynamic (run time) polymorphism
  is the polymorphism existed at run-time. Here, Java compiler does not understand which method is called at compilation time.
  Dynamic binding/Run-Time binding/Late binding/Method overriding.(in different classes)
 Only JVM decides which method is called at run-time.
  Method overloading and method overriding using instance methods are the examples for dynamic polymorphism.

Static (compile time) polymorphism is the polymorphism exhibited at compile time. Here, Java compiler knows which method is called.
 Method overloading and method overriding using static methods; method overriding using private or final methods are examples for static polymorphism

    Static binding/Compile-Time binding/Early binding/Method overloading.(in same class)
 */
public class D_MethodOverriding {
    public static void main(String[] args) {
        //This is example of dynamic polymorphysim
        CarCompany carCompany = new BmwCarCompany();//First CarComparny constructor called & then BMW constructor
        BmwCarCompany bmw = new BmwCarCompany();// this will also call parent class constructor
        CarCompany audiCar = new AudiCarCompanry();//parent constructor will be called even if no overriden methods are present
        /*
        Why ? Parent Construtor will be called:

Because child class instance "is a" instance of the parent class, so parent class should be properly setup before child class.
Parent class need not to know about child class but child should know its parent very well. This also explains why we should call parent class constructor.
         */
        System.out.println(carCompany.name);//VolksVegan
        System.out.println(carCompany.getCarCompany());//BMW car company
        System.out.println(carCompany.isParent());

        BmwCarCompany carCompany1 = (BmwCarCompany) carCompany;
        System.out.println(carCompany1.getLocation());

        System.out.println(carCompany.getCarCompany());

        try {
            System.out.println(carCompany.getAnnualIncome());
        } catch (Exception e) {// need to handle parent method exception
            e.printStackTrace();
        }

        System.out.println(carCompany1.getAnnualIncome()); // since child method is not throwing any exception no need to handle exception

        try {
            System.out.println(carCompany.getMonthlyIncome(25));
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            System.out.println(carCompany1.getMonthlyIncome(25));
        } catch (FileNotFoundException e) {// parent is throwing Exception but child is throwing FileNotFound
            e.printStackTrace();
        }

//        System.out.println(carCompany.getNextMonthIncome());//runtimeexception no need to handle
        System.out.println("**********************static binding*************88");
        carCompany.companyInfo3();
        carCompany.companyInfo1();//static method calling with ref
    }
}

class CarCompany {
    protected String name = "VolksVegan";

    CarCompany() {
        System.out.println("CarCompany constructor");
    }

    CarCompany(String name) {
        System.out.println("CarCompany constructor overloaded");
        this.name = name;
    }

    public String getCarCompany() {
        return name + " car company";
    }

    public Number getAnnualIncome() throws Exception {
        return 1000;
    }

    public Number getMonthlyIncome(Number rate) throws Exception {
        return 250;
    }

    public Integer getNextMonthIncome() throws ArithmeticException {
        return 2500;
    }

    public void test(){

    }
    public boolean isParent() {
        return true;
    }

    //static binding/polymorphysim  --- parent class methods will be called
    public static void companyInfo1() {
        System.out.println("some parent company info1");
    }

    private void companyInfo2() {
        System.out.println("some parent company info2");
    }

    public final void companyInfo3() {
        System.out.println("some parent company info3");
    }
}

class BmwCarCompany extends CarCompany {
    protected String name = "BMW";

    public BmwCarCompany() {// by default it called the parent class default constructor
//        super(); TODO optional
        System.out.println("BMW Constructor");
    }

    public BmwCarCompany(String name) {// by default it called the parent class default constructor
        super(name);//we can call parameterized constructor // TODO super should at first line
        System.out.println("BMW Constructor overloaded");
        this.name = name;
    }

    @Override
    public String getCarCompany() throws RuntimeException {// overridden method can throw RuntimeException even if parent method is not throwing
        // but checked exception are not allowed
//        return super.name; //allowed
        return "BMW car company";
    }

    @Override
    public Integer getAnnualIncome() {  //covariance - only method return type can be narrowed down not mehod params
        // Parent method is throwing exception but child is not throwing anything, child might have handled
        return 500;
    }

    @Override
    public Number getMonthlyIncome(Number rate) throws FileNotFoundException {// parent Exception can be narrowed down in overridden methods
        return 150;
    }

    @Override
    public Integer getNextMonthIncome() throws RuntimeException {// RuntimeException scope can be widen(parent thrwoing ArithmaticException)
        int a = 5 / 0;
        return 250;
    }

    @Override
    public void test() /*throws Exception*/{ // parent is not throwing Exception, so child cant throw Checked Exception

    }

    public String getLocation() {
        return "Chennai";
    }

    //static binding/polymorphysim
    public static void companyInfo1() {
        System.out.println("some child company info1");
    }

    private void companyInfo2() {
        System.out.println("some child company info2");
    }
}

class AudiCarCompanry extends CarCompany {
    public AudiCarCompanry()  {
        System.out.println("Audi Car Company");
    }

}


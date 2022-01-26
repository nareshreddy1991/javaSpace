package com.designpatterns.a_creational;

import java.util.Calendar;

/*
Factory:
The factory design pattern is used when we have a superclass with multiple sub-classes and based on input, we need to return one of the sub-class.
This pattern takes out the responsibility of the instantiation of a class from the client program to the factory class.
We can keep Factory class Singleton or we can keep the method that returns the subclass as static
Advantages:
    1. Factory design pattern provides approach to code for interface rather than implementation.
    2. Factory pattern removes the instantiation of actual implementation classes from client code. Factory pattern makes our code more robust, less coupled and easy to extend.
For example, we can easily change PC class implementation because client program is unaware of this.
    3. Factory pattern provides abstraction between implementation and client classes through inheritance.

 */
public class A_FactoryDesignPattern {
    public static void main(String[] args) {
        Computer pc = ComputerFactory.getComputer("PC", 100, 8);
        Computer laptop = ComputerFactory.getComputer("Laptop", 500, 12);

        ComputerFactory factory = new ComputerFactory();
        Computer pc1 = factory.getComputer("PC", 100, 8);//calling static method using obj

        //built in factory methods
        Calendar instance = Calendar.getInstance();
        String s = String.valueOf(10);
    }
}

class ComputerFactory {
    public static Computer getComputer(String type, Integer hdd, Integer ram) {
        if (type.equals("PC")) return new PC(hdd, ram);
        else if (type.equals("Laptop")) return new Laptop(hdd, ram);
        return null;
    }
}

interface Computer {
    Integer getHDD();

    Integer getRAM();
}

class PC implements Computer {

    private Integer hdd;
    private Integer ram;

    public PC(Integer hdd, Integer ram) {
        this.hdd = hdd;
        this.ram = ram;
    }

    @Override
    public Integer getHDD() {
        return hdd;
    }

    @Override
    public Integer getRAM() {
        return ram;
    }
}

class Laptop implements Computer {

    private Integer hdd;
    private Integer ram;

    public Laptop(Integer hdd, Integer ram) {
        this.hdd = hdd;
        this.ram = ram;
    }

    @Override
    public Integer getHDD() {
        return hdd;
    }

    @Override
    public Integer getRAM() {
        return ram;
    }
}

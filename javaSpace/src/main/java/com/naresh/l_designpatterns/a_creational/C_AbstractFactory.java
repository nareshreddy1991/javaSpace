package com.naresh.l_designpatterns.a_creational;

import javax.xml.parsers.DocumentBuilderFactory;

/*
Abstract Factory is factory of factory. A factory class that generates other factory class.
            Animal                                      Color
               |                                          |
       |-----------------|                      |-----------------|
       Dog              Cat                    Brown            Red

         AbstractFactory
                |
        |-----------------|
       AnimalFactor    ColorFactory

       FactoryProvider creates factories

When to Use Abstract Factory Pattern:
The client is independent of how we create and compose the objects in the system
The system consists of multiple families of objects, and these families are designed to be used together
We need a run-time value to construct a particular dependency
 */
public class C_AbstractFactory {
    public static void main(String[] args) {
        AbstractFactory animalFactory = FactoryProvider.getFactory("Animal");
        Object dog = animalFactory.create("Dog");

        //example class in jdk
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
    }
}

interface Animal {
    String getAnimal();

    String makeSound();
}

class Duck implements Animal {

    @Override
    public String getAnimal() {
        return "Duck";
    }

    @Override
    public String makeSound() {
        return "Squeks";
    }
}

class Dog implements Animal {

    @Override
    public String getAnimal() {
        return "Dog";
    }

    @Override
    public String makeSound() {
        return "Bark";
    }
}

interface AbstractFactory<T> {
    T create(String animalType);
}

class AnimalFactory implements AbstractFactory<Animal> {

    @Override
    public Animal create(String animalType) {
        if ("Dog".equalsIgnoreCase(animalType)) {
            return new Dog();
        } else if ("Duck".equalsIgnoreCase(animalType)) {
            return new Duck();
        }

        return null;
    }

}

class FactoryProvider {
    public static AbstractFactory getFactory(String choice) {

        if ("Animal".equalsIgnoreCase(choice)) {
            return new AnimalFactory();
        } else if ("Color".equalsIgnoreCase(choice)) {
//            return new ColorFactory(); //same as AnimalFactory
        }

        return null;
    }
}
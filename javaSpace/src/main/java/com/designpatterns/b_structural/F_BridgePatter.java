package com.designpatterns.b_structural;

/*
Bridge:
When we have interface hierarchies in both interfaces as well as implementations, then bridge design pattern is used to decouple the interfaces from implementation and hiding the implementation details from the client programs.
According to GoF bridge design pattern is:
Decouple an abstraction from its implementation so that the two can vary independently
The implementation of bridge design pattern follows the notion to prefer Composition(has a relation) over inheritance(is a relation).

                    Shape
                   /        \
                  /          \
            Triangle            Pentogon
          /     \                   /   \
         /       \                  /    \
 RedColor    GreenColor       RedColor      GreenColor  (All these colors are implementing Color interface)

After Bridge Pattern it will become like this:
                    Shape  ------------has--------------> Color
                   /        \                              /    \
                  /          \                            /       \
            Triangle            Pentogon           RedColor         GreenColor
 */
public class F_BridgePatter {
    public static void main(String[] args) {
        Shape1 tri = new Triangle(new RedColor());
        tri.applyColor();

        Shape1 pent = new Pentagon(new GreenColor());
        pent.applyColor();
    }
}

interface Color {

    public void applyColor();
}

abstract class Shape1 {
    //Composition - implementor
    protected Color color;

    //constructor with implementor as input argument
    public Shape1(Color c) {
        this.color = c;
    }

    abstract public void applyColor();
}

class Triangle extends Shape1 {

    public Triangle(Color c) {
        super(c);
    }

    @Override
    public void applyColor() {
        System.out.print("Triangle filled with color ");
        color.applyColor();
    }

}

class Pentagon extends Shape1 {

    public Pentagon(Color c) {
        super(c);
    }

    @Override
    public void applyColor() {
        System.out.print("Pentagon filled with color ");
        color.applyColor();
    }

}

class RedColor implements Color {

    public void applyColor() {
        System.out.println("red.");
    }
}

class GreenColor implements Color {

    public void applyColor() {
        System.out.println("green.");
    }
}
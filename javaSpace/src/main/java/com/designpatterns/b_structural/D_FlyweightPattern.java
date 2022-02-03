package com.designpatterns.b_structural;
/*
Flyweight: -- caching objects
This pattern is used to reduce the memory footprint. It can also improve performance in applications where object instantiation is expensive.
Simply put, the flyweight pattern is based on a factory which recycles created objects by storing them after creation. Each time an object is requested,
 the factory looks up the object in order to check if it's already been created. If it has, the existing object is returned – otherwise, a new one is created,
 stored and then returned.
The flyweight object's state is made up of an invariant component shared with other similar objects (intrinsic) and a variant component which can be manipulated by the client code (extrinsic).
TODO It's very important that the flyweight objects are immutable: any operation on the state must be performed by the factory.

UseCase:
Data Compression
Data Caching
 */

import java.awt.*;
import java.util.HashMap;

public class D_FlyweightPattern {
    public static void main(String[] args) {

    }
}

interface Shape {

    //these externsick properties
    public void draw(Graphics g, int x, int y, int width, int height, Color color);
}

class Line implements Shape {

    public Line() {
        System.out.println("Creating Line object");
        //adding time delay
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void draw(Graphics line, int x1, int y1, int x2, int y2, Color color) {
        line.setColor(color);
        line.drawLine(x1, y1, x2, y2);
    }

}

class Oval implements Shape {

    //intrinsic property
    private boolean fill;

    public Oval(boolean f) {
        this.fill = f;
        System.out.println("Creating Oval object with fill=" + f);
        //adding time delay
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void draw(Graphics circle, int x, int y, int width, int height, Color color) {
        circle.setColor(color);
        circle.drawOval(x, y, width, height);
        if (fill) {
            circle.fillOval(x, y, width, height);
        }
    }

}

class ShapeFactory {

    //caching the objects - this will reduce the load on the system
    private static final HashMap<ShapeType, Shape> shapes = new HashMap<ShapeType, Shape>();

    public static Shape getShape(ShapeType type) {
        Shape shapeImpl = shapes.get(type);

        if (shapeImpl == null) {
            if (type.equals(ShapeType.OVAL_FILL)) {
                shapeImpl = new Oval(true);
            } else if (type.equals(ShapeType.OVAL_NOFILL)) {
                shapeImpl = new Oval(false);
            } else if (type.equals(ShapeType.LINE)) {
                shapeImpl = new Line();
            }
            shapes.put(type, shapeImpl);
        }
        return shapeImpl;
    }

    public static enum ShapeType {
        OVAL_FILL, OVAL_NOFILL, LINE;
    }
}

package com.naresh.generics;

/*
Naming convention:
E - Element (used extensively by the Java Collections Framework)
K - Key
N - Number
T - Type
V - Value
S,U,V etc. - 2nd, 3rd, 4th types
 */
public class A_GenericClasses {
    public static void main(String[] args) {
        Box box= new Box(10);//Raw Type
        Box<Integer> b2= box;// we get warning msg: unchecked assignment
        b2.setObject(123);

        Box box2 = new Box("naresh");
        Box<Integer> b3= box2;// we get warning msg: unchecked assignment
        Integer object = b3.getObject();// classeast exception

        Box<Integer> b1= new Box<>(10); //Generic type

    }
}

/*class Box {
    private Object object;

    public void set(Object object) { this.object = object; }
    public Object get() { return object; }
}*/
//The above class doesn't provide type safety & below generic class provides it.
class Box<T> {
    private T object;

    public Box(T object) {
        this.object = object;
    }

    public T getObject() {
        return object;
    }

    public void setObject(T object) {
        this.object = object;
    }
}

//Multiple types
interface Pair<K, V> {
    public K getKey();

    public V getValue();
}
interface Pair1{}

//class OrderedPair<K, V> implements Pair { -- this is allowed
//class OrderedPair<K, V> implements Pair1 { -- THis is allowed
//class OrderedPair<K, V> implements Pair1<K,V> { -- THis is not allowed, Pair1 doent have k V
class OrderedPair<K, V> implements Pair<K, V> {

    private K key;
    private V value;

    public OrderedPair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }
}
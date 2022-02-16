package com.naresh.javabasics;

/*
Non Static inner classes
static inner classes
Method local innser classes
annonamous inner classes
 */
public class C_NestedClasses {
    public static void main(String[] args) {
        ParentClass.ChildClass parentClass = new ParentClass().new ChildClass();//non static class
        ParentClass.ChildClass2 childClass2 = new ParentClass.ChildClass2();//static class

    }
}

class ParentClass {
    private String name;
    public static String namex;

    private String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void accessChild() {
        ChildClass childClass = new ChildClass();//out class cant access child private methods
        childClass.getChildName();//it can access members only by creating child class object
    }

    class ChildClass {//it supports all access modifiers
        private String childName;
//        private static String val="Naresh"; //static variable declaration is not allowed todo why?

        private String getChildName() {
            String name = getName();//TODo parent private methods can be accessed
            String namex = ParentClass.namex;//can access parent static methods
            return childName;
        }

        public void setChildName(String childName) {
            this.childName = childName;
        }
    }

    static class ChildClass2 {//static class can access only static members of parent class
        private String childName;
        private static String X; // in static class static variables are allowed

        public String getChildName() {
            String namex = ParentClass.namex;//parent static members we can access directly
            ParentClass p = new ParentClass();
            p.getName();// non static members we can get by object
            return childName;
        }

        public void setChildName(String childName) {
            this.childName = childName;
        }
    }

    public void innerClassMethod() {
        class MethodInnerClass {// no access specifiers are supported / static is not allowed
            private String inner;
            //            private static String X; // not allowed
            ChildClass childClass = new ChildClass();// we can access other classes

            MethodInnerClass() {
                String name = getName();// we can access parent members directly
                String namex = ParentClass.namex;//accessing static

                childClass.getChildName();//accessing child class members
            }
        }
        MethodInnerClass a = new MethodInnerClass();// this statement should be after class declaration
        //TODO useful to create enums for any manupullations
    }

    public void test() {
//        MethodInnerClass m= new MethodInnerClass(); //cant access method local inner classes
    }
}

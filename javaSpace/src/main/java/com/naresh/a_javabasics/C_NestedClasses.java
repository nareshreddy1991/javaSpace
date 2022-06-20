package com.naresh.a_javabasics;

/*
Why inner classes?
Inner classes are a security mechanism in Java. We know a class cannot be associated with the access modifier private,
but if we have the class as a member of other class, then the inner class can be made private. And this is also used to access the private members of a class.
it also provides encapsulation for inner classes. By using access modifiers you can control the access.

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
//        private static String val="Naresh"; //TODO static variable declaration is not allowed in non static inner classes

//        private static void test(){}//TODO static methods also not allowed, so we can't have main() method

        private String getChildName() {
            String name = getName();//TODo parent private methods can be accessed
            String namex = ParentClass.namex;//TODO can access parent static methods and variable
            return childName;
        }

        public void setChildName(String childName) {
            this.childName = childName;
        }
    }
    /*
    TODO Why static inner classes? a normal inner classes can access static and non static members of outerclass
    if you want to restruct inner class from accessing outer class non static members we can use static inner classes
     */
    static class ChildClass2 {//static class can access only static members of parent class
        private String childName;
        private static String X; //TODO in static class static variables & methods are allowed,
        // we can declare a main() method and invoke it from commandline

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

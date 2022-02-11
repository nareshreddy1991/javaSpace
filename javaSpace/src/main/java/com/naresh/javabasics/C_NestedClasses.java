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
    }

    class ChildClass {//it supports all access modifiers
        private String childName;
//        private static String val="Naresh"; not allowed

        private String getChildName() {
            String name = getName();//TODo parent private methods can be accessed
            return childName;
        }

        public void setChildName(String childName) {
            this.childName = childName;
        }
    }

    static class ChildClass2 {//static class can access only static members of parent class
        private String childName;

        public String getChildName() {
            String namex = ParentClass.namex;
            return childName;
        }

        public void setChildName(String childName) {
            this.childName = childName;
        }
    }

    public void innerClassMethod() {
        class MethodInnerClass {// on access specifiers are supported / static is not allowed
            private String inner;
        }
        MethodInnerClass a = new MethodInnerClass();// this statement should be after class declaration
        //TODO useful to create enums for any manupullations
    }
}

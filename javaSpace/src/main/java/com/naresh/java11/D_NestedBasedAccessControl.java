package com.naresh.java11;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/*TODO I didn't understand
Java 11 nested access control addresses this concern in reflection.
java.lang.Class introduces three methods in the reflection API: getNestHost(), getNestMembers(), and isNestmateOf()
 */
public class D_NestedBasedAccessControl {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Main ob = new Main();
        Method method = ob.getClass().getDeclaredMethod("myPrivate");
//        method.setAccessible(true);
        Object invoke = method.invoke(ob);
        System.out.println(invoke);
    }
}

class Main {

    public void myPublic() {
    }

    private String myPrivate() {
        return "invoked";
    }

    class Nested {

        public void nestedPublic() {
//            myPrivate();
            /*
            private method of the main class is accessible from the above-nested class in the above manner.
But if we use Java Reflection, it will give an IllegalStateException.
             */
        }
    }
}

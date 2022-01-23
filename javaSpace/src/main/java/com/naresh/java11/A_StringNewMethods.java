package com.naresh.java11;

import java.util.stream.Stream;

/*
Java11 is LTS relase after java 8.
Java is not free anymore, we need to get licence to use it.
1)Running Java File with single command
One major change is that you don’t need to compile the java source file with javac tool first.
 You can directly run the file with java command and it implicitly compiles.
 */
public class A_StringNewMethods {
    public static void main(String[] args) {
        //TODO isBlank
        System.out.println("".isBlank());//true
        System.out.println("    ".isBlank());//true
        System.out.println("    ".isEmpty());//false - existing method
        //TODO lines
        String value1 = "Naresh";
        Stream<String> lines = value1.lines();
        lines.forEach(System.out::println);//Naresh
        value1 = "Naresh\nChareesh\nLalitha\tLatha";
        lines = value1.lines();//split the string based on \n only
        lines.forEach(System.out::println);//all names one in every line
        //TODO strip() methods -- removed whitespaces at starting and ending
        /*
        strip() is “Unicode-aware” evolution of trim().
        When trim() was introduced, Unicode wasn’t evolved. Now, the new strip() removes all kinds of whitespaces leading and
        trailing(check the method Character.isWhitespace(c) to know if a unicode is whitespace or not)

        strip() vs trim()
        strip() is Unicode whitespace aware, whereas the existing method trim() removes any space which is less than or equal to (\u0020).
         */
        System.out.println("start" + (" Nari ".trim()) + "end");//trim both the ends
        System.out.println(Character.isWhitespace(' ')); //TODO unicode??

        System.out.println("start" + (" Nari ".strip()) + "end");//trim both the ends
        System.out.println("start" + (" Nari ".stripLeading()) + "end");//trims at starting
        System.out.println("start" + (" Nari ".stripTrailing()) + "end");//trims at ending
        //TODO repeat(n)
        System.out.println("JSR".repeat(5)); //repeat JSR five times as single string

    }
}

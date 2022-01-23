package com.naresh.java11;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
/*
Java 11 strives to make reading and writing of String convenient.
It has introduced the following methods for reading and writing to/from the files:

readString()
writeString()
 */
public class C_Files {
    public static void main(String[] args) throws IOException {
        Path path = Files.writeString(Files.createTempFile("test", ".txt"), "This was posted on JD");
        System.out.println(path);
        String s = Files.readString(path);
        System.out.println(s); //This was posted on JD
    }
}

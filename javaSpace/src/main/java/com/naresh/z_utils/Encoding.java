package com.naresh.z_utils;

import java.util.Base64;

public class Encoding {
    static String base62 = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static void main(String[] args) {
//        System.out.println(base62Encode(999999999));//for this only 6 digits code will be generated, only 6 iterations
//        System.out.println(base62Encode(123456701));

//        base64Encoding("naresh");//for same input always same output will come
//        base64Encoding("naresh");
//        base64Encoding("naresh1");
//        base64Encoding("lalitha");

        strToBinary("naresh");
    }

    private static void base64Encoding(String input) {
        Base64.Encoder encoder = Base64.getEncoder();
        String str = encoder.encodeToString(input.getBytes());
        System.out.println("Encoded string: " + str);

        Base64.Decoder decoder = Base64.getDecoder();
        String dStr = new String(decoder.decode(str));
        System.out.println("Decoded string: " + dStr);
    }

    private static void base62Encode(long value) {
        StringBuilder sb = new StringBuilder();
        while (value != 0) {
            sb.append(base62.charAt((int) (value % 62)));
            value /= 62;
        }
        while (sb.length() < 6) {
            sb.append(0);
        }
        System.out.println(sb.reverse().toString());
    }

    static void strToBinary(String s) {
        int n = s.length();

        for (int i = 0; i < n; i++) {
            // convert each char to ASCII value
            int val = Integer.valueOf(s.charAt(i));

            // Convert ASCII value to binary
            StringBuffer bin = new StringBuffer();
            while (val > 0) {
                if (val % 2 == 1) {
                    bin.append('1');
                } else
                    bin.append('0');
                val /= 2;
            }
            bin = bin.reverse();
            System.out.print(bin + " ");
        }
    }
}

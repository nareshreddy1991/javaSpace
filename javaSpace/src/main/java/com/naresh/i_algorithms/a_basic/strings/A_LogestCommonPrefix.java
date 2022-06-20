package com.naresh.i_algorithms.a_basic.strings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class A_LogestCommonPrefix {

    /*
    Input:
N = 4
arr[] = {geeksforgeeks, geeks, geek,
         geezer}
Output: gee
Explanation: "gee" is the longest common
prefix in all the given strings.
     */
    String longestCommonPrefix(String arr[], int n) {
        String first = arr[0];
        String longPrefix = "";
        for (int i = 0; i < first.length(); i++) {
            boolean isAllMatched = true;
            for (int j = 1; j < arr.length; j++) {
                if (arr[j].length() - 1 < i ||
                        first.charAt(i) != arr[j].charAt(i)) {
                    isAllMatched = false;
                    break;
                }
            }
            if (isAllMatched) {
                longPrefix += first.charAt(i);
            } else {
                break;
            }
        }
        return longPrefix.length() > 0 ? longPrefix : "-1";
    }

    /*
    Input:
    S = {"geeks", "for", "geeks", "contribute",
         "practice"}
    word1 = "geeks"
    word2 = "practice"
    Output: 2
    Explanation: Minimum distance between the
    words "geeks" and "practice" is 2
     */
    int shortestDistance(ArrayList<String> s, String word1, String word2) {

        int work1Index = -1;
        int work2Index = -1;
        int minCount = Integer.MAX_VALUE;
        for (int i = 0; i < s.size(); i++) {
            if (word1.equals(s.get(i))) {
                work1Index = i;
            }
            if (word2.equals(s.get(i))) {
                work2Index = i;
            }
            if (work1Index >= 0 && work2Index >= 0)
                minCount = Math.min(minCount, Math.abs(work1Index - work2Index));
        }
        return minCount;
    }

    public void test(){
        String str="";


        //find hte first non repeating char
        Optional<Character> first = str.chars()
                .mapToObj(e -> Character.toLowerCase(Character.valueOf((char) e)))
                .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()))
                .entrySet()
                .stream()
                .filter(e -> e.getValue() == 1)
                .map(e -> e.getKey())
                .findFirst();



        String[] array = {"a", "b", "c", "d", "e"};
        char[] chars = str.toCharArray();

        //Arrays.stream
        Stream<String> stream1 = Arrays.stream(array);
//        Stream<Character> s=Arrays.stream(chars); //primitives are not allowed
        Stream<char[]> stream = Stream.of(str.toCharArray());

    }
}

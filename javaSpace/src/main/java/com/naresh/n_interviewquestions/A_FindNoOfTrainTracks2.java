package com.naresh.n_interviewquestions;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

class pair {
    int first;
    char second;

    pair(int key1, char key2) {
        this.first = key1;
        this.second = key2;
    }

    public int getFirst() {
        return first;
    }

    public char getSecond() {
        return second;
    }
}

class A_FindNoOfTrainTracks2 {

    public static int findPlatform(int arr[], int dep[],
                                   int n) {

        // Insert all the times (arr. and dep.)
        // in the ArrayList of pairs.
        ArrayList<pair> order = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            order.add(new pair(arr[i], 'a'));
            order.add(new pair(dep[i], 'd'));
        }

        // Custom sort order ArrayList, first
        // by time than by arrival or departure
        Collections.sort(order, new Comparator<pair>() {
            public int compare(pair p1, pair p2) {
                if (p1.first == p2.first)
                    return new Character(p1.second)
                            .compareTo(
                                    new Character(p2.second));

                return p1.first - p2.first;
            }
        });
//        Collections.sort(order, Comparator.comparing(pair::getFirst).thenComparing(pair::getSecond));

        int result = 1;
        int plat_needed = 0;

        for (int i = 0; i < order.size(); i++) {
            pair p = order.get(i);
            System.out.println(p.first+"-"+p.second);
            // If its 'a' then add 1 to plat_needed
            // else minus 1 from plat_needed.
            if (p.second == 'a')
                plat_needed++;
            else
                plat_needed--;

            if (plat_needed > result)
                result = plat_needed;
        }
        return result;
    }

    // Driver Code
    public static void main(String[] args) {
        int arr[] = {900, 940, 950, 1100, 1500, 1800};
        int dep[] = {910, 1200, 1120, 1130, 1900, 2000};

//        int[] arr = new int[]{100, 110, 120, 955, 900, 900, 101, 1055, 113, 114, 900};
//        int[] dep = new int[]{105, 115, 125, 100, 1010, 101, 102, 111, 114, 1110, 125};
        int n = arr.length;

        System.out.println("Minimum Number of " +
                "Platforms Required = " +
                findPlatform(arr, dep, n));
    }
}

// This code is contributed by RohitOberoi


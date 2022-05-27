package com.naresh.n_interviewquestions;


import java.util.*;

public class A_FindNoOfTrainTracksRequired {
    public static void main(String[] args) {
        double[] arrival = new double[]{10.0, 11.0, 12.0, 9.55, 9.0, 9.0, 10.1, 10.55, 11.3, 11.4, 9};
        double[] departure = new double[]{10.5, 11.5, 12.5, 10.0, 10.10, 10.1, 10.2, 11.1, 11.4, 11.10, 12.5};

//        double arrival[] = {900, 940, 950, 1100, 1500, 1800};
//        double departure[] = {910, 1200, 1120, 1130, 1900, 2000};

        int count = findNoOfPlatForms1(arrival, departure);
        System.out.println("No of plot forms required is:" + count);
        count = findNoOfPlatForms2(arrival, departure);
        System.out.println("No of plot forms required is:" + count);
        count = findNoOfPlatForms3(arrival, departure);
        System.out.println("No of plot forms required is:" + count);
    }

    /*
    My Approach -- partially correct
            |--------------| i
                |---|
        |------|    |-----------|
      |-----------------------------|
      TC: O(n^2)
      SC: O(1)  -- extra space solution did not work
     */
    private static int findNoOfPlatForms1(double[] arrival, double[] departure) {
        int count = 1;
//        boolean[] allocated = new boolean[arrival.length];
        for (int i = 0; i < arrival.length; i++) {
            int noOfPlatForms = 1;
            for (int j = i + 1; j < arrival.length; j++) {
                if (((arrival[i] <= departure[j] && departure[j] <= departure[i])
                        || (arrival[i] <= arrival[j] && arrival[j] <= departure[i])
                        || (arrival[i] > arrival[j] && departure[j] > departure[i]))) {
//                        && !allocated[j]) {
                    noOfPlatForms++;
//                    count++;
//                    allocated[j] = true;
                }
                count = Math.max(noOfPlatForms, count);
            }
        }
        return count;
    }

    /*
         int arr[] = { 900, 940, 950, 1100, 1500, 1800 };
            int dep[] = { 910, 1200, 1120, 1130, 1900, 2000 };

            Math.max(900,940)<= Math.min(910,1200) ---false--no overlap
            Math.max(900,950)<= Math.min(910,1120) ---true-- overlap

      TODO Not So best
      TC: O(n^2)
      SC: O(1)
     */
    private static int findNoOfPlatForms2(double[] arrival, double[] departure) {
        int count = 1;
        for (int i = 0; i < arrival.length; i++) {
            int noOfPlatForms = 1;
            for (int j = i + 1; j < arrival.length; j++) {
                if (Math.max(arrival[i], arrival[j]) <=
                        Math.min(departure[i], departure[j])) {
                    noOfPlatForms++;
                }
                count = Math.max(count, noOfPlatForms);
            }
        }
        return count;
    }

    /*
Time      Event Type     Total Platforms Needed
                               at this Time
 9:00       Arrival                  1
 9:10       Departure                0
 9:40       Arrival                  1
 9:50       Arrival                  2
 11:00      Arrival                  3
 11:20      Departure                2
 11:30      Departure                1
 12:00      Departure                0
 15:00      Arrival                  1
 18:00      Arrival                  2
 19:00      Departure                1
 20:00      Departure                0

Minimum Platforms needed on railway station
= Maximum platforms needed at any time
= 3

TC: O(N logN)
SC: O(N)

TODO count is not matching-- recheck
     */
    private static int findNoOfPlatForms3(double[] arrival, double[] departure) {
        List<Pair> list = new ArrayList<>();
        for (int i = 0; i < arrival.length; i++) {
            list.add(new Pair(arrival[i], "A"));
            list.add(new Pair(departure[i], "D"));
        }
        list.sort(Comparator.comparing(Pair::getKey).thenComparing(Pair::getValue));
        int maxCount = 1, tempCount = 0;
        for (Pair pair : list) {
            System.out.println(pair.key+"-"+pair.value);
            if (pair.value.equals("A")) {
                tempCount++;
            } else if (pair.value.equals("D")) {
                tempCount--;
            }
            maxCount = Math.max(maxCount, tempCount);
        }
        return maxCount;
    }

    static class Pair {
        double key;
        String value;

        public Pair(double key, String value) {
            this.key = key;
            this.value = value;
        }

        public double getKey() {
            return key;
        }

        public String getValue() {
            return value;
        }
    }
}

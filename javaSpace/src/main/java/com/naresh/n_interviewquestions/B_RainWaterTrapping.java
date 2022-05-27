package com.naresh.n_interviewquestions;

/*
int[]  arr={2,0,2}

        X w2 X
        X w1 X
    --------------------

    Ans: we can accomodate two drops
 */
public class B_RainWaterTrapping {
    public static void main(String[] args) {
        int[] arr = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        System.out.println(findHowMuchWater(arr));
        System.out.println(findHowMuchWater2(arr));
        System.out.println(findHowMuchWater3(arr));
    }

    //O(n^2) O(1)
    private static int findHowMuchWater(int[] array) {
        int res = 0;
        for (int i = 1; i < array.length - 1; i++) {
            //find max in left
            int leftMax = array[i];
            for (int j = 0; j < i; j++) {
                leftMax = Math.max(leftMax, array[j]);
            }

            //find right max
            int rightMax = array[i];
            for (int j = i + 1; j < array.length; j++) {
                rightMax = Math.max(rightMax, array[j]);
            }
            int currentCount = Math.min(leftMax, rightMax) - array[i];
            res += currentCount;
            System.out.println("for i:" + i + " left Max:" + leftMax + " right max:" + rightMax + " curr count:" + currentCount + "res:" + res);
        }
        return res;
    }

    // 2 5 6 8 4  | l 5 3
    // both O(n)
    //find left max and right max and keep it in another array to reduce the complexity
    private static int findHowMuchWater2(int[] array) {
        int n = array.length;
        int[] leftMax = new int[n];
        int[] rightMax = new int[n];

        leftMax[0] = array[0];
        for (int i = 1; i < n; i++) {
            leftMax[i] = Math.max(leftMax[i - 1], array[i]);
        }
        rightMax[n - 1] = array[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            rightMax[i] = Math.max(array[i], rightMax[i + 1]);
        }

        int count = 0;
        for (int i = 0; i < n; i++) {
            count += Math.min(leftMax[i], rightMax[i]) - array[i];
        }
        return count;
    }

    /*
    Debug:
    low:0 hi:11 leftmax:0rightmax:0temp:0 result:0
    low:2 hi:10 leftmax:1rightmax:1temp:1 result:1
    low:3 hi:9 leftmax:1rightmax:2temp:1 result:2
    low:3 hi:8 leftmax:1rightmax:2temp:0 result:2
    low:4 hi:7 leftmax:2rightmax:2temp:1 result:3
    low:5 hi:7 leftmax:2rightmax:2temp:2 result:5
    low:6 hi:7 leftmax:2rightmax:2temp:1 result:6
     */
    static int findHowMuchWater3(int arr[]) {
        int n = arr.length;
        // initialize output
        int result = 0;

        // maximum element on left and right
        int left_max = 0, right_max = 0;

        // indices to traverse the array
        int lo = 0, hi = n - 1;
        while (lo <= hi) {
            if (arr[lo] < arr[hi]) {
                if (arr[lo] > left_max)
                    // update max in left
                    left_max = arr[lo];
                else {
                    // water on curr element =
                    // max - curr
                    int temp = (left_max - arr[lo]);
                    result += temp;
                    System.out.println("low:" + lo + " hi:" + hi + " leftmax:" + left_max + "rightmax:" + right_max + "temp:" + temp + " result:" + result);
                }
                lo++;
            } else {
                if (arr[hi] > right_max)
                    // update right maximum
                    right_max = arr[hi];
                else {
                    int temp = right_max - arr[hi];
                    result += temp;
                    System.out.println("low:" + lo + " hi:" + hi + " leftmax:" + left_max + "rightmax:" + right_max + "temp:" + temp + " result:" + result);

                }
                hi--;
            }
        }
        return result;
    }

}

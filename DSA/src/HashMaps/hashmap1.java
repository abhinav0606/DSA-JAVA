package HashMaps;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class hashmap1 {
    public static void main(String[] args) {
        System.out.println(twoSumBruteForce(new int[]{2, 7, 11, 15}));
        System.out.println(twoSum(new int[]{2, 7, 11, 15}));
        System.out.println(longestSubArray(new int[] {15,-2,2,-8,1,7,10,23}));
        System.out.println(distinctElementInEveryWindow(new int[] {1, 2, 1, 3, 4, 2, 3}));
    }

    private static List<List<Integer>> twoSumBruteForce(int[] arr) {
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] + arr[j] == 9) {
                    result.add(List.of(arr[i], arr[j]));
                }
            }
        }
        return result;
    }

    private static List<List<Integer>> twoSum(int[] arr) {
        List<List<Integer>> result = new ArrayList<>();
        HashMap<Integer, Integer> hm = new HashMap<>();
        // we will use a hashmap to conduct these things
        // Create a map of key -> which will be like 2 and value as target - 2
        // then check if the compliment is present in the hashmap if yes then add the result otherwise continue
        for (int i : arr) {
            int compliment = 9 - i;
            if (hm.containsKey(compliment)) {
                result.add(List.of(i, compliment));
            }
            hm.put(i, compliment);
        }
        return result;
    }

    private static int longestSubArray(int[] arr) {
        int i = 0;
        int cws = 0;
        int maxLength = Integer.MIN_VALUE;
        HashMap<Integer, Integer> hm = new HashMap<>();
        while (i < arr.length) {
            // setting summing
            cws = cws + arr[i];
            //checking if the sum is equal to zero
            if (cws == 0) {
                maxLength = i + 1;
            }
            // We will keep saving the sum that we have calculated till now in a hashmap
            // Once we got that the cws was already there that means in bw these two same cws some 0 was there thats why we got
            // the same cws there.
            if (!hm.containsKey(cws)) {
                hm.put(cws,i);
            } else {
                maxLength = Math.max(maxLength,i-hm.get(cws));
            }
            i++;
        }
        return maxLength;
    }
    private static List<Integer> distinctElementInEveryWindow(int[] arr) {
        HashMap<Integer,Integer> hm = new HashMap<>();
        List<Integer> result = new ArrayList<>();
        for (int i = 0;i<4;i++) {
            hm.put(arr[i],hm.getOrDefault(arr[i],0) + 1);
        }
        result.add(hm.keySet().size());
        for (int i=4;i<arr.length;i++) {
            int outgoing = arr[i-4];
            hm.put(outgoing,hm.get(outgoing)-1);
            if (hm.get(outgoing) == 0) {
                hm.remove(outgoing);
            }
            int incoming = arr[i];
            hm.put(arr[i],hm.getOrDefault(arr[i],0)+1);
            result.add(hm.keySet().size());
        }
        return result;
    }
}

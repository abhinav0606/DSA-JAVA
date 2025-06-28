package Striver;


import java.util.*;
import java.util.Arrays;

public class imp4 {
    public static void main(String[] args) {
        minWindow("ADOBECODEBANC","ABC");
        System.out.println(Arrays.toString(maxSlidingWindow(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 3)));
        palindromePairs(new String[] {"a","abc","aba",""});
    }
    public int trappingRainWater(int[] arr) {
        int mlh = 0;
        int mrh = 0;
        int lp = 0;
        int rp = arr.length-1;
        int totalWater = 0;
        while (lp<rp) {
            if (arr[lp] < arr[rp]) {
                if (mlh <= arr[lp]) {
                    mlh = arr[lp];
                } else {
                    totalWater = totalWater + mlh - arr[lp];
                }
                lp++;
            } else {
                if (mrh <= arr[rp]) {
                    mrh = arr[rp];
                } else {
                    totalWater = totalWater + mrh - arr[rp];
                }
                rp--;
            }
        }
        return totalWater;
    }

    //Need to revise more on this....
//    MinWindow Question
    public static String minWindow(String s, String t) {
        Map<Character,Integer> tMap = new HashMap<>();
        Map<Character,Integer> requirementMap = new HashMap<>();
        if (t.isEmpty() || s.isEmpty()) return "";
        for (char c : t.toCharArray()) {
            tMap.put(c,tMap.getOrDefault(c,0)+1);
        }
        int counter = tMap.size();
        int i = 0;
        int j = 0;
        String minSubstring  = "";
        int minLength = Integer.MAX_VALUE;
        int head = 0;
        char[] sArray = s.toCharArray();
        while (j<sArray.length) {
            if (tMap.containsKey(sArray[j])) {
                tMap.put(sArray[j],tMap.get(sArray[j])-1);
                if (tMap.get(sArray[j]) == 0) counter--;
            }
            j++;
            while (counter == 0) {
                if (tMap.containsKey(sArray[i])) {
                    tMap.put(sArray[i],tMap.get(sArray[i]) + 1);
                    if (tMap.get(sArray[i]) > 0) counter++;
                }
                if (j-i < minLength) {
                    minLength = j-i;
                    head = i;
                }
                i++;
            }
        }
        return minLength == Integer.MAX_VALUE ? "" : s.substring(head,head + minLength);
    }
    // Sliding Window Maximum - one approach
    public static int[] maxSlidingWindow(int[] nums, int k) {
        int i=0;
        int j=k-1;
        int[] arr = new int[nums.length];
        int counter = 0;
        while (j<nums.length) {
            int max = findMax(java.util.Arrays.copyOfRange(nums,i,j+1));
            arr[counter] = max;
            counter ++;
            i++;
            j++;
        }
        return Arrays.copyOf(arr,counter);
    }
    private static int findMax(int[] arr) {
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        return max;
    }

    public static int[] maxSlidingWindowDeque(int[] nums,int k) {
        List<Integer> res = new ArrayList<>();
        Deque<Integer> dequeue = new LinkedList<>();
        for (int i=0;i<nums.length;i++) {
            // We will check if queue is not empty and the current element is greater than the last added element
            while (!dequeue.isEmpty() && dequeue.getLast() < nums[i]) {
                dequeue.pollLast();
            }
            //Adding the element
            dequeue.add(nums[i]);
            // We will check if the window is out of range so we have to pop the first one
            // i = 3 and k = 3 and q[0] == nums[i-k] because if the first element itself is the largest in the window
            if (i >= k && nums[i-k] == dequeue.getFirst()) {
                dequeue.pollFirst();
            }
            // we have to add it into resolution list so if i=2 and k=3 i mean the window is reached so get the first element because it is the largest one
            if (i >= k-1) {
                res.add(dequeue.getFirst());
            }
        }
        return res.stream().mapToInt(i->i).toArray();
    }
    // Palindrome pairs - in progress   Hard and keep it for tries
    public static void palindromePairs(String[] words) {
        Map<String,String> map = new LinkedHashMap<>();
        for (String s : words) {
            map.put(s,new StringBuilder(s).reverse().toString());
        }
        List<String> keySet = map.keySet().stream().toList();
        List<String> valueSet = map.values().stream().toList();
        List<List<Integer>> l = new ArrayList<>();
        for (int i=0;i<keySet.size();i++) {
            for (int j=0;j<valueSet.size();j++) {
                if (i!=j && (valueSet.get(j).isEmpty() || valueSet.get(j).startsWith(keySet.get(i)))) {
                    l.add(List.of(i,j));
                }
            }
        }
        System.out.println(keySet);
        System.out.println(valueSet);
        System.out.println(l);
    }
}

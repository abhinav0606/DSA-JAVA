package Striver;

import java.util.*;

public class imp3 {
    public static void main(String[] args) {
        System.out.println(reverseWords("  hello world  "));
    }

    public int findKthLargest(int[] nums, int k) {
        //sort the array and apply the indexing
        Arrays.sort(nums);
        return nums[nums.length - k];
    }

    // Longest common prefix for string pattern
    // sort the array first and take the biggest and smallest word i mean first and last
    // start iterating on it
    public String longestCommonPrefix(String[] strs) {
        Arrays.sort(strs);
        String firstString = strs[0];
        String lastString = strs[strs.length - 1];
        int i = 0;
        int j = 0;
        String commonPrefix = "";
        while (i < firstString.length() && j < lastString.length()) {
            if (firstString.charAt(i) == lastString.charAt(j)) {
                commonPrefix = commonPrefix + firstString.charAt(i);
            } else {
                break;
            }
            i++;
            j++;
        }
        return commonPrefix;
    }

    // Reverse Words in string
    public static String reverseWords(String s) {
        String[] newString = s.split(" ");
        String result = newString[newString.length - 1];
        for (int i = newString.length - 2; i >= 0; i--) {
            if (!newString[i].isEmpty() && !newString[i].equals(" ")) result = result + " ";
            if (!newString[i].isEmpty() && !newString[i].equals(" ")) {
                result = result + newString[i];
            }
        }
        return result;
    }

    // Rotate arrays
    // Reverse last k elements
    // Reverse first k elements
    // Reverse whole array
    // but there is a trick because there can be cases that length is smaller but k is bigger
    // so you have to do a modular which will basically create a good equation factor
    public void rotate(int[] nums, int k) {
        k = k%nums.length;
        reverseArray(nums, nums.length - k, nums.length - 1);
        reverseArray(nums, 0, nums.length - k - 1);
        reverseArray(nums, 0, nums.length - 1);
    }

    private void reverseArray(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }
    public int findMin(int[] nums) {
        // Normal Approach
//        Arrays.sort(nums);
//        return nums[0];
        // Optimized approach
        int left = 0;
        int right = nums.length-1;
        while (left < right) {
            int mid = (left+right)/2;
            if (nums[mid] > nums[right]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return nums[left];
    }

    // Group anagrams question
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String,List<String>> map = new HashMap<>();
        for (String s:strs) {
            char[] charArray = s.toCharArray();
            Arrays.sort(charArray);
            String sortedString = new String(charArray);
            map.computeIfAbsent(sortedString,k -> new ArrayList<>()).add(s);
        }
        return map.values().stream().toList();
    }
}

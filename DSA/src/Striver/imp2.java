package Striver;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class imp2 {
    public static void main(String[] args) {
        System.out.println(subarraySum(new int[]{1,2,3},3));
        System.out.println(productExceptSelf(new int[]{1,2,3,4}));
        System.out.println(maxArea(new int[] {1,8,6,2,5,4,8,3,7}));
        System.out.println(longestPalindrome("babad"));
    }
    public static int subarraySum(int[] nums, int k) {
        // here we will use the prefix sum and we will use the concept of hashmap
        // we will create a map which will contain sum as the key and value as the counter
        // if for a particular sum if sum-k is present already then add the counter
        int count = 0;
        int sum = 0;
        Map<Integer,Integer> map = new HashMap<>();
        // to initialize the single single element thing
        map.put(0,1);
        for (int i = 0;i<nums.length;i++) {
            sum = sum + nums[i];
            if (map.containsKey(sum-k)) {
                count = count + map.get(sum-k);
            }
            map.put(sum,map.getOrDefault(sum,0)+1);
        }
        return count;
    }

    public static int[] productExceptSelf(int[] nums) {
        int[] prefixProduct = new int[nums.length];
        int[] suffixProduct = new int[nums.length];
        prefixProduct[0] = 1;
        suffixProduct[nums.length-1] = 1;
        for (int i = 1;i<nums.length;i++) {
            prefixProduct[i] = prefixProduct[i-1] * nums[i-1];
        }
        for (int i = nums.length-2;i>=0;i--) {
            suffixProduct[i] = suffixProduct[i+1] * nums[i+1];
        }
        for (int i = 0;i<nums.length;i++) {
            nums[i] = prefixProduct[i] * suffixProduct[i];
        }
        System.out.println(Arrays.toString(nums));
        return nums;
    }
    // valid parenthesis question - easy
    public boolean isValid(String s) {
        Stack<Character> characters = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c == '{' || c == '(' || c == '[') {
                characters.push(c);
            } else if (c == ')' && !characters.isEmpty() && characters.peek() == '(') {
                characters.pop();
            } else if (c == '}' && !characters.isEmpty() && characters.peek() == '{') {
                characters.pop();
            }else if (c == ']' && !characters.isEmpty() && characters.peek() == ']') {
                characters.pop();
            } else {
                return false;
            }
        }
        return true;
    }
    public static int maxArea(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int maxArea  = 0;
        while (left < right) {
            int length = right - left;
            int width = Math.min(height[left],height[right]);
            maxArea = Math.max(length*width,maxArea);
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }
        return maxArea;
    }

    private static boolean palindromeCheck(String s) {
        int i = 0;
        int j = s.length() - 1;
        while (i < j) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }
    public static String longestPalindrome(String s) {
        int i = 0;
        int j = 1;
        String result = "";
        int maxLength = 0;
        while (i<s.length()) {
            boolean palindromeCheck = palindromeCheck(s.substring(i,j));
            if (palindromeCheck) {
                if (maxLength < j-i) {
                    maxLength = j-i;
                    result = s.substring(i,j);
                }
            }
            if (j == s.length()) {
                j = i + 1;
                i++;
            }
            else {j++;}
        }
        return result;
    }
}

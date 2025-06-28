package Striver.BitManipulation;

import java.awt.geom.RectangularShape;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class BitMan {
    public static void main(String[] args) {

    }

    public int minBitsFlip(int start, int goal) {
        int ans = start ^ goal;
        int count = 0;
        for (int i=0;i<32;i++) {
            if ((ans & (1 << i)) != 0 ) {
                count++;
            }
        }
        return count;
    }

    public int singleNumber(int[] nums) {
        int ans = nums[0];
        for (int i=1;i<nums.length;i++) {
            ans = ans ^ nums[i];
        }
        return ans;
    }

    public int singleNumber2(int[] nums) {
        int ans = 0;
        for (int bit=0;bit<32;bit++) {
            int count = 0;
            for (int i=0;i<nums.length;i++) {
                if ((nums[i] & (1 << bit)) != 0) count++;
            }
            if (count % 3  == 1) ans = ans | (1 >> bit);
        }
        return ans;
    }

    public int singleNumber2Better(int[] nums) {
        Arrays.sort(nums);
        for (int i=1;i<nums.length;i=i+3) {
            if (nums[i] != nums[i-1]) return nums[i];
        }
        return nums[nums.length-1];
    }

    public int[] singleNumber3(int[] nums) {
        int xor = 0;
        for (int num : nums) {
            xor = xor ^ num;
        }
        int rightMost = (xor & (xor-1)) ^ xor;
        int b1 = 0;
        int b2 = 0;
        for (int i=0;i<nums.length;i++) {
            if ((rightMost & nums[i]) != 0) {
                b1 = b1 ^ nums[i];
            } else {
                b2 = b2 ^ nums[i];
            }
        }
        return new int[] {b1,b2};
    }

    public int divide(int dividend, int divisor) {
        if (dividend == divisor) return 1;
        boolean isPositive = true;
        if (dividend >=0 && divisor < 0) isPositive = false;
        else if (dividend < 0 && divisor > 0) isPositive = false;
        int n = Math.abs(dividend);
        int d = Math.abs(divisor);
        int ans = 0;
        while (n >= d) {
            int count = 0;
            while (n >= d* (1 << count+1)) count++;
            ans = ans + (1 << count);
            n = n - d * (1 << count);
        }

        if (ans > Integer.MAX_VALUE && isPositive) return Integer.MAX_VALUE;
        else if (ans > Integer.MAX_VALUE && !isPositive) return Integer.MIN_VALUE;

        return isPositive ? ans : -1*ans;
    }

    public List<List<Integer>> powerSet(int[] nums) {
        int n = nums.length;
        List<List<Integer>> result = new ArrayList<>();
        int countOfSubset = 1 << n;
        for (int i=0;i<countOfSubset;i++) {
            List<Integer> subSet = new ArrayList<>();
            for (int j = 0;j<n;j++) {
                if ((i & (1 << j)) != 0) {
                    subSet.add(nums[j]);
                }
            }
            result.add(subSet);
        }
        return result;
    }
    public int findRangeXOR(int l, int r) {
        return XORTilln(r) ^ XORTilln(l-1);
    }
    private int XORTilln(int n) {
        if (n%4 == 1) return 1;
        else if (n%4 == 2)  return n+1;
        else if (n%4 == 3) return 0;
        else return n;
    }

}

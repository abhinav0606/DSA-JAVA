package Striver.Arrays;

import java.util.*;

public class ArraysFAQ {
    public static void main(String[] args) {
        System.out.println(leaders(new int[] {198,393,946,655,978,781,472,239,639,738,705,8,355,457,69,152,37,74,390,659,
                238,132,122,739,687,992,699,258,684,903,556,903,330,321,413,600,197,696,512,671,429,644,908,
                952,714,608,178,346,182,77,134,956,313,582,160,217,291,907,924,151,541,850,271,533,705,258,716,835,307,426}));
        System.out.println(Arrays.toString(rearrangeArray(new int[]{2, 4, 5, -1, -3, -4})));
        System.out.println(pascalTriangle(5));
        int[][] arr = {{1,2,3},{4,5,6},{7,8,9}};
        System.out.println(spiralOrder(arr));
    }
    // Leaders in an array
    // return the elements in the same order and only those which are having all the elements to the right less than
    public static ArrayList<Integer> leaders(int[] nums) {
        ArrayList<Integer> result = new ArrayList<>();
        result.add(nums[nums.length-1]);
        int max = nums[nums.length-1];
        for (int i=nums.length-2;i>=0;i--) {
            max = Math.max(nums[i+1],max);
            if (nums[i] > max) result.add(nums[i]);
        }
        Collections.reverse(result);
        return result;
    }

    // Given Array consisting of negative and positive numbers
    // we have to arrange it in a way such that there is a pair
    // Idea is we will move all the negative to the end - but it will not work
    // Idea will be lets take pos index as 0 and negative index as 1
    public static int[] rearrangeArray(int[] nums) {
        int posIndex = 0;
        int negIndex = 1;
        int[] ans = new int[nums.length];
        for (int i=0;i<nums.length;i++) {
            if (nums[i]>=0) {
                ans[posIndex] = nums[i];
                posIndex = posIndex+2;
            } else {
                ans[negIndex] = nums[i];
                negIndex = negIndex+2;
            }
        }
        return ans;
    }

    // Kadane's Algorithms
    // this algorithm helps to identify the maximum subarray sum
    // here when we will move we will check if the element taken is the correct candidate or not
    public int maxSubArray(int[] nums) {
        int currentSum = nums[0];
        int maxSum = nums[0];
        for (int i =1;i<nums.length;i++) {
            currentSum = Math.max(nums[i],currentSum+nums[i]);
            maxSum = Math.max(maxSum,currentSum);
        }
        return maxSum;
    }
    // This is the ultimate problem known as Dutch national Flag
    // Which will sort 0 1 and 2
    public void sortZeroOneTwo(int[] nums) {
        // take low for 0 and mid for 1 and high for 2
        // We are going to take mid as the iterator
        int low = 0;
        int mid = 0;
        int high = nums.length-1;
        while (mid <=high) {
            if (nums[mid] == 0) {
                int temp = nums[low];
                nums[low] = nums[mid];
                nums[mid] = temp;
                low++;
                mid++;
            } else if (nums[mid] == 1) mid++;
            else {
                int temp = nums[high];
                nums[high] = nums[mid];
                nums[mid] = temp;
                high--;
            }
        }
    }

    // pascal triangle
    public static List<List<Integer>> pascalTriangle(int numRows) {
        List<List<Integer>> result = new ArrayList<>();
        for (int i=0;i<numRows;i++) {
            if (i==0) result.add(List.of(1));
            else if (i==1) result.add(List.of(1,1));
            else {
                List<Integer> subResult = new ArrayList<>();
                subResult.add(1);
                for (int j=0;j<i-1;j++) {
                    subResult.add(result.getLast().get(j) + result.getLast().get(j+1));
                }
                subResult.add(1);
                result.add(subResult);
            }
        }
        return result;
    }
    // Return the spiral printed matrix
    public static List<Integer> spiralOrder(int[][] matrix) {
        int top = 0;
        int left = 0;
        int right = matrix[0].length-1;
        int bottom = matrix.length-1;
        List<Integer> result = new ArrayList<>();
        while (top <= bottom && left <= right) {
            for (int i=left;i<=right;i++) {
                result.add(matrix[top][i]);
            }
            top++;
            for (int i=top;i<=bottom;i++) {
                result.add(matrix[i][right]);
            }
            right--;
            if (bottom>=top) {
                for (int i=right;i>=left;i--) {
                    result.add(matrix[bottom][i]);
                }
                bottom--;
            }
            if (right>=left) {
                for (int i=bottom;i>=top;i--) {
                    result.add(matrix[i][left]);
                }
                left++;
            }
        }
        return result;
    }
    // Best way is to create a transpose of a matrix and then rotate each row
    public void rotateMatrix(int[][] matrix) {
        int row = matrix.length;
        int column = matrix[0].length;
        for (int i=0;i<row;i++) {
            for (int j=i+1;j<column;j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
        for (int i=0;i<row;i++) {
            int low = 0;
            int high = column-1;
            while (low<high) {
                int temp = matrix[i][low];
                matrix[i][low] = matrix[i][high];
                matrix[i][high] = temp;
                low++;
                high--;
            }
        }
    }

    // Using hashmap
    // There is a better approach that will help without extra space
    public int[] twoSum(int[] nums, int target) {
        Map<Integer,Integer> map = new HashMap<>();
        for (int i=0;i<nums.length;i++) {
            int sumLeft = target - nums[i];
            if (map.containsKey(sumLeft)) {
                return Arrays.stream(new int[] {i,map.get(sumLeft)}).sorted().toArray();
            }
            map.put(nums[i],i);
        }
        return new int[] {-1,-1};
    }
    // Solving it with space complexity
    // all the three triplets should not be equal to each other
    public List<List<Integer>> threeSum(int[] nums) {
        Set<List<Integer>> result = new HashSet<>();
        for (int i=0;i<nums.length;i++) {
            Set<Integer> set = new HashSet<>();
            for (int j=i+1;j<nums.length;j++) {
                int third = -(nums[i] + nums[j]);
                if (set.contains(third)) {
                    List<Integer> temp = new ArrayList<>();
                    temp.add(nums[i]);
                    temp.add(nums[j]);
                    temp.add(third);
                    Collections.sort(temp);
                    result.add(temp);
                }
                set.add(nums[j]);
            }
        }
        return new ArrayList<>(result);
    }
    public List<List<Integer>> fourSum(int[] nums,int target) {
        Set<List<Integer>> result = new HashSet<>();
        for (int i=0;i<nums.length;i++) {
            for (int j=i+1;j<nums.length;j++) {
                Set<Integer> set = new HashSet<>();
                for (int k=j+1;k<nums.length;k++) {
                    int fourth = target - (nums[i] + nums[j] + nums[k]);
                    if (set.contains(fourth)) {
                        List<Integer> temp = new ArrayList<>();
                        temp.add(nums[i]);
                        temp.add(nums[j]);
                        temp.add(nums[k]);
                        temp.add(fourth);
                        Collections.sort(temp);
                        result.add(temp);
                    }
                    set.add(nums[k]);
                }
            }
        }
        return new ArrayList<>(result);
    }
}

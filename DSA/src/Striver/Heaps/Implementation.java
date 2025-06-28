package Striver.Heaps;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Implementation {
    public static void main(String[] args) {

    }

    public void heapify(int[] nums, int ind, int val) {
        if (nums[ind] > val) {
            nums[ind] = val;
            heapifyUp(nums,ind);
        } else {
            nums[ind] = val;
            heapifyDown(nums,ind);
        }
    }

    private void heapifyUp(int[] nums,int index) {
        int parentIndex = (index-1)/2;
        if (index > 0 && nums[parentIndex] > nums[index]) {
            int temp = nums[index];
            nums[index] = nums[parentIndex];
            nums[parentIndex] = temp;
            heapifyUp(nums,parentIndex);
        }
    }
    private void heapifyDown(int[] nums,int index) {
        int smallerIndex = index;
        int leftIndex = 2*index+1;
        int rightIndex = 2*index+2;
        if (leftIndex < nums.length && nums[leftIndex] < nums[smallerIndex]) {
            smallerIndex = leftIndex;
        }
        if (rightIndex < nums.length && nums[rightIndex] < nums[smallerIndex]) {
            smallerIndex = rightIndex;
        }

        if (smallerIndex != index) {
            int temp = nums[index];
            nums[index] = nums[smallerIndex];
            nums[smallerIndex] = temp;
            heapifyDown(nums,smallerIndex);
        }

    }

    public void buildMinHeap(int[] nums) {
        for (int i=(nums.length/2)-1;i>=0;i--) {
            heapifyDown(nums,i);
        }
    }


    static class Solution {

        List<Integer> arr;
        int count;

        public Solution() {
            this.arr = new ArrayList<>();
            count = 0;
        }

        public void initializeHeap() {
            arr.clear();
            count = 0;
        }

        public void insert(int key) {
            arr.add(key);
            heapifyUp(arr,count);
            count = count + 1;
        }

        public void changeKey(int index, int newVal) {
            if (arr.get(index) > newVal) {
                arr.set(index,newVal);
                heapifyUp(arr,index);
            } else {
                arr.set(index,newVal);
                heapifyDown(arr,index);
            }
        }

        public void extractMin() {
            // Minimum is the first element
            // swap first and last
            // remove the first element
            // heapify down with the first index
            int min = arr.getFirst();
            int last = arr.getLast();
            arr.set(0,last);
            arr.set(count-1, min);
            arr.remove(count-1);
            count = count - 1;
            if (count > 0) {
                heapifyDown(arr,0);
            }
        }

        public boolean isEmpty() {
            return count == 0;
        }

        public int getMin() {
            return arr.getFirst();
        }

        public int heapSize() {
            return count;
        }

        private void heapifyUp(List<Integer> nums,int index) {
            int parentIndex = (index-1)/2;
            if (index > 0 && nums.get(parentIndex) > nums.get(index) ){
                int temp = nums.get(index);
                nums.set(index, nums.get(parentIndex));
                nums.set(parentIndex, temp);
                heapifyUp(nums,parentIndex);
            }
        }
        private void heapifyDown(List<Integer> nums,int index) {
            int smallerIndex = index;
            int leftIndex = 2*index+1;
            int rightIndex = 2*index+2;
            if (leftIndex < nums.size() && nums.get(leftIndex) < nums.get(smallerIndex)) {
                smallerIndex = leftIndex;
            }
            if (rightIndex < nums.size() && nums.get(rightIndex) < nums.get(smallerIndex)) {
                smallerIndex = rightIndex;
            }

            if (smallerIndex != index) {
                int temp = nums.get(index);
                nums.set(index, nums.get(smallerIndex));
                nums.set(smallerIndex, temp);
                heapifyDown(nums,smallerIndex);
            }

        }
    }

    static class SolutionMax {

        List<Integer> arr;
        int count;

        public SolutionMax() {
            arr = new ArrayList<>();
            count = 0;
        }

        public void initializeHeap() {
            arr.clear();
            count = 0;
        }

        public void insert(int key) {
            arr.add(key);
            heapifyUp(arr,count);
            count++;
        }

        public void changeKey(int index, int newVal) {
            if (arr.get(index) < newVal) {
                arr.set(index,newVal);
                heapifyUp(arr,index);
            } else {
                arr.set(index,newVal);
                heapifyDown(arr,index);
            }
        }

        public void extractMax() {
            int max = arr.get(0);
            int last = arr.get(count-1);
            arr.set(0,last);
            arr.set(count-1, max);
            arr.remove(count-1);
            count = count - 1;
            if (count > 0) {
                heapifyDown(arr,0);
            }
        }

        public boolean isEmpty() {
            return count == 0;
        }

        public int getMax() {
            return arr.get(0);
        }

        public int heapSize() {
            return count;
        }

        private void heapifyUp(List<Integer> nums,int index) {
            int parentIndex = (index-1)/2;
            if (index > 0 && nums.get(parentIndex) < nums.get(index) ){
                int temp = nums.get(index);
                nums.set(index, nums.get(parentIndex));
                nums.set(parentIndex, temp);
                heapifyUp(nums,parentIndex);
            }
        }
        private void heapifyDown(List<Integer> nums,int index) {
            int smallerIndex = index;
            int leftIndex = 2*index+1;
            int rightIndex = 2*index+2;
            if (leftIndex < nums.size() && nums.get(leftIndex) > nums.get(smallerIndex)) {
                smallerIndex = leftIndex;
            }
            if (rightIndex < nums.size() && nums.get(rightIndex) > nums.get(smallerIndex)) {
                smallerIndex = rightIndex;
            }

            if (smallerIndex != index) {
                int temp = nums.get(index);
                nums.set(index, nums.get(smallerIndex));
                nums.set(smallerIndex, temp);
                heapifyDown(nums,smallerIndex);
            }

        }
    }

    public boolean isHeap(int[] nums) {
        // Just check if nums[left] < nums[i]
        // start from the leaf nodes
        int n = nums.length;
        for (int i=(nums.length/2)-1;i>=0;i--) {
            int leftChild = 2*i + 1;
            int rightChild = 2*i + 2;
            if (leftChild < n && nums[leftChild] < nums[i]) return false;
            if (rightChild < n && nums[rightChild] < nums[i]) return false;
        }
        return true;
    }

    private void heapifyDownMax(int[] nums,int index) {
        int largestIndex = index;
        int leftIndex = 2*index+1;
        int rightIndex = 2*index+2;
        if (leftIndex < nums.length && nums[leftIndex] > nums[largestIndex]) {
            largestIndex = leftIndex;
        }
        if (rightIndex < nums.length && nums[rightIndex] > nums[largestIndex]) {
            largestIndex = rightIndex;
        }

        if (largestIndex != index) {
            int temp = nums[index];
            nums[index] = nums[largestIndex];
            nums[largestIndex] = temp;
            heapifyDownMax(nums,largestIndex);
        }

    }
    public int[] minToMaxHeap(int[] nums) {
        int n = nums.length;
        for (int i=(n/2)-1;i>=0;i--) {
            heapifyDownMax(nums,i);
        }
        return nums;
    }

    private void heapifyDownMax2(int[] nums,int index, int last) {
        int largestIndex = index;
        int leftIndex = 2*index+1;
        int rightIndex = 2*index+2;
        if (leftIndex <= last && nums[leftIndex] > nums[largestIndex]) {
            largestIndex = leftIndex;
        }
        if (rightIndex <= last && nums[rightIndex] > nums[largestIndex]) {
            largestIndex = rightIndex;
        }

        if (largestIndex != index) {
            int temp = nums[index];
            nums[index] = nums[largestIndex];
            nums[largestIndex] = temp;
            heapifyDownMax(nums,largestIndex);
        }

    }

    public void heapSort(int[] nums) {
        int n = nums.length;
        for (int i=(n/2)-1;i>=0;i--) {
            heapifyDownMax2(nums,i,n-1);
        }
        int last = n-1;
        // why not >= 0 because we are going to swap it with the 0th index itself
        while (last > 0) {
            int temp = nums[0];
            nums[0] = nums[last];
            nums[last] = temp;
            last--;
            if (last > 0) {
                heapifyDownMax2(nums,0,last);
            }
        }
    }

    public int kthLargestElement(int[] nums, int k) {
        // build a min heap using PriorityQueue
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i=0;i<k;i++) {
            pq.add(nums[i]);
        }
        for (int i=k;i<nums.length;i++) {
            if (nums[i] > pq.peek()) {
                pq.poll();
                pq.add(nums[i]);
            }
        }
        return pq.peek();
    }
}

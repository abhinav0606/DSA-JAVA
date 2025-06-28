package Striver;

import java.util.Arrays;

public class sorting {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(sortArray(new int[]{2, 3, 1, 4, 5})));
        System.out.println(Arrays.toString(bubbleSort(new int[]{2, 3, 1, 4, 5})));
        System.out.println(Arrays.toString(quickSort(new int[]{2, 3, 1, 4, 5})));
        System.out.println(Arrays.toString(selectionSort(new int[]{2, 3, 1, 4, 5})));
    }

    // Insertion sort
    //1. Take the first element as the sorted element
    //2. Start from second element and compare that element with the first one is it is smaller shift it and move to next
    //3. Then third one compare it with the last 2 elements if the element is bigger then move next if the element is smaller
    //   shift the element.
    //4. Do this process till last element

    private static int[] sortArray(int[] arr) {
        for (int i=1;i<arr.length;i++) {
            int j = i-1;
            int key = arr[i];
            while (j>=0 && arr[j] > key) {
                arr[j+1] = arr[j];
                j--;
            }
            arr[j+1] = key;
        }
        return arr;
    }

    // Bubble Sort
    // Take the first element compare it with all the element and swap it if necessary and move ahead for swapping
    private static int[] bubbleSort(int[] arr) {
        // Iterating on one by one element not like the insertion sort
        for (int i=0;i<arr.length-1;i++) {
            boolean swapped = false;
            for (int j=0;j<arr.length-i-1;j++) {
                if (arr[j] > arr[j+1]) {
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                    swapped = true;
                }
            }
            if (!swapped) break;
        }
       return arr;
    }

    // quickSort
    private static int[] quickSort(int[] arr) {
        int low = 0;
        int high = arr.length;
        return quickSortHelper(arr,low,high);
    }
    private static int[] quickSortHelper(int[] arr,int low,int high) {
        if (low<high) {
            int partition = quickSortPartition(arr,low,high);
            quickSortHelper(arr,low,partition-1);
            quickSortHelper(arr,partition+1,high);
        }
        return arr;
    }
    private static int quickSortPartition(int[] arr,int low,int high) {
        int pivot = arr[high-1];
        int j = low-1;
        for (int i=low;i<high;i++) {
            if (arr[i] < pivot) {
                j++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        int temp = arr[j+1];
        arr[j+1] = arr[high-1];
        arr[high-1] = temp;
        return j+1;
    }
    // Selection sort
    // Start from the first element and from the rest of the element after this index find the minimum value and its index
    // swap it with the current element
    private static int[] selectionSort(int[] arr) {
        for (int i=0;i<arr.length;i++) {
            int minIndex = i;
            for (int j=i+1;j<arr.length;j++) {
                if (arr[minIndex] > arr[j]) minIndex = j;
            }
            int temp = arr[minIndex];
            arr[minIndex] = arr[i];
            arr[i] = temp;
        }
        return arr;
    }
}

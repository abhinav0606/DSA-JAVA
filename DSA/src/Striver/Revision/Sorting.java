package Striver.Revision;

public class Sorting {
    public static void main(String[] args) {
        quickSort(new int[] {5,4,3,2,1});
    }
    private static void quickSortHelper(int[] arr,int low, int high) {
        if (low < high) {
            int partition = partitionFinder(arr,low,high);
            quickSortHelper(arr,low,partition-1);
            quickSortHelper(arr,partition+1,high);
        }
    }
    private static int partitionFinder(int[] arr,int low,int high) {
        int pivot = arr[high];
        int j = low - 1;
        for (int i=low;i<=high;i++) {
            if (arr[i] < pivot) {
                j++;
                int temp = arr[j];
                arr[j] = arr[i];
                arr[i] = temp;
            }
        }
        int temp = arr[j + 1];
        arr[j + 1] = arr[high];
        arr[high] = temp;
        return j+1;
    }
    private static int[] quickSort(int[] arr) {
        int n = arr.length;
        quickSortHelper(arr,0,n-1);
        return arr;
    }
}

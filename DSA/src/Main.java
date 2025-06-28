//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        int[] arr1 = {2, 7,9,10}; // Example array 1
        int[] arr2 = {5,6,11}; // Example array 2
        int k = 3; // Find the 5th smallest el
        System.out.println(kthSmallest(4,arr1,3,arr2,k));
    }
    public static int kthSmallest(int n, int[] arr1, int m, int[] arr2, int k) {
        int i=0;
        int j=0;
        int z=0;
        int[] result = new int[n+m];
        while(i<n && j<m) {
            if (arr1[i]<=arr2[j]) {
                result[z++] = arr1[i++];
            } else {
                result[z++] = arr2[j++];
            }
        }
        while(i<n) {
            result[z++]=arr1[i++];
        }
        while(j<m) {
            result[z++]=arr2[j++];
        }
        return result[k-1];

    }
}
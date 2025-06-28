package Striver.BinarySearch;

public class TwoDArray {
    public static void main(String[] args) {
        int[][] arr = {
                {1,2,3,4},{5,6,7,8}
        };
        int[][] matrix = {{1, 4, 9},
                {2,5,6},
                {3,8,7}};
//        System.out.println(rowWithMax1s(arr));
        System.out.println(searchMatrix(arr,8));
        System.out.println(findMedian(matrix));
    }
    // What i did in this question is
    // iterated in each row and using binary search
    // calculated the mid if mid is 1 moved to left half and searched the starting index
    // and calculated the max
    public static int rowWithMax1s(int[][] mat) {
        int rows = mat.length;
        int columns = mat[0].length;
        int index = -1;
        int maxOnes = 0;
        for (int i=0;i<rows;i++) {
            int low = 0;
            int high = columns-1;
            int startingOne = -1;
            while (low<=high) {
                int mid = (low + high)/2;
                if (mat[i][mid] == 1) {
                    startingOne = mid;
                    high = mid-1;
                } else {
                    low = mid + 1;
                }
            }
            if (startingOne != -1) {
                int numberOfOnes = columns - startingOne;
                if (numberOfOnes>maxOnes) {
                    index = i;
                    maxOnes = numberOfOnes;
                }
            }
        }
        return index;
    }

    // This is a classic question of searching in a sorted array
    public static boolean searchMatrix(int[][] mat, int target) {
        int i = 0;
        int j = mat[0].length-1;
        while (i<mat.length && j>=0) {
            if (mat[i][j] == target) return true;
            else if (mat[i][j] > target) j--;
            else i++;
        }
        return false;
    }
    // Lets try the binary search approach
    public static boolean searchMatrixBinary(int[][] mat, int target) {
        int rows = mat.length;
        int columns = mat[0].length;
        int low = 0;
        int high = rows*columns - 1;
        while (low <= high) {
            int mid = (low + high)/2;
            // Because the indexing that we are doing while considering them as 1d array is based on column number
            // so for first row till column - 1 second row column*2 - 1 like that
            // so row will be calculated by mid/columns and column will be the simple math that is mid % columns number
            int row = mid/columns;
            int column = mid%columns;
            if (mat[row][column] == target) return true;
            if (mat[row][column] < target) low = mid + 1;
            else high = mid - 1;
        }
        return false;
    }
    public boolean searchMatrix2(int[][] mat, int target) {
        int i = 0;
        int j = mat[0].length-1;
        while (i<mat.length && j>=0) {
            if (mat[i][j] == target) return true;
            else if (mat[i][j] > target) j--;
            else i++;
        }
        return false;
    }

    public int[] findPeakGrid(int[][] mat) {
        // Have to Implement this code in a different way
        // first figure out a mid column and in all the rows figure out the max one
        // as the max one will be the max as the top and bottom greater i s covered with the max scenario

        int rows = mat.length;
        int columns = mat[0].length;
        int low = 0;
        int high = columns-1;
        while (low <= high) {
            int mid = (low + high)/2;
            int row = -1;
            int max = Integer.MIN_VALUE;
            for (int i=0;i<rows;i++) {
                if (mat[i][mid] > max) {
                    max = mat[i][mid];
                    row = i;
                }
            }
            int left = mid - 1 >=0 ? mat[row][mid-1] : Integer.MIN_VALUE;
            int right = mid + 1 < columns ? mat[row][mid+1] : Integer.MIN_VALUE;
            if (mat[row][mid] > left && mat[row][mid] > right) return new int[] {row,mid};
            else if (left > mat[row][mid]) high = mid-1;
            else low = mid+1;
        }
        return new int[] {-1,-1};
    }
    public static int findMedian(int[][] matrix) {
        int rows = matrix.length;
        int columns = matrix[0].length;
        int low = Integer.MAX_VALUE;
        int high = Integer.MIN_VALUE;
        int requiredNumber = (rows*columns)/2;
        for (int i=0;i<rows;i++) {
            low = Math.min(low,matrix[i][0]);
            high = Math.max(high,matrix[i][columns-1]);
        }
        while (low <= high) {
            int mid = (low+high)/2;
            // Calculate count of Smaller numbers than mid
            int smallNumberCount = smallerNumberCount(matrix,rows,columns,mid);
            if (smallNumberCount <= requiredNumber) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return low;
    }
    private static int smallerNumberCount(int[][] arr,int rows,int columns,int mid) {
        int count = 0;
        for (int i=0;i<rows;i++) {
            int low = 0;
            int high = columns-1;
            int innerCount = columns;
            while (low <= high) {
                int midIndex = (low + high)/2;
                if (arr[i][midIndex] > mid) {
                    innerCount = midIndex;
                    high = midIndex-1;
                } else {
                    low = midIndex+1;
                }
            }
            count = count + innerCount;
        }
        return count;
    }
}

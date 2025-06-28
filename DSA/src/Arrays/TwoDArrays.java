package Arrays;

import java.util.Arrays;

public class TwoDArrays {
    public static void main(String[] args) {
        int[][] arr = {{1,2,3},{4,5,6},{7,8,9}};
        int[][] arr1 = {{9,8,7},{6,5,4},{3,2,1}};
        matrixTraversal(arr);
        System.out.println(Arrays.deepToString(sumOfMatrix(arr, arr1)));
        System.out.println("----------");
        int[][] arr2 = {{1,2},{3,4},{5,6}};
        System.out.println(Arrays.deepToString(multiplicationOfMatrix(arr,arr2)));
        matrixColumnTraversal(arr);
        System.out.println("------------");
        waveTraversal(arr);
        System.out.println("---------------");
        searchIn2DSortedArray(arr,5);
        searchIn2DSortedArray(arr,4);
        System.out.println();
        System.out.println("----------------");
        spiralTraversal(arr);
        System.out.println("----------------");
        System.out.println(Arrays.deepToString(transposeOfMatrix(arr)));
        arr = new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        System.out.println(Arrays.deepToString(rotateImage90(arr)));
        arr = new int[][]{{1,0,0},{2,3,4},{5,6,7}};
        System.out.println(Arrays.deepToString(setMatrixToZero(arr)));

    }
    public static void matrixTraversal(int[][] arr) {
        for (int i=0;i< arr.length;i++) {
            for (int j=0;j<arr[0].length;j++) {
                System.out.print(arr[i][j]);
                System.out.print(" ");
            }
        }
    }
    public static int[][] sumOfMatrix(int[][] arr1,int[][] arr2) {
        int[][] ansArr = new int[arr1.length][arr1[0].length];
        if (arr1.length == arr2.length && arr1[0].length == arr2[0].length) {
            for (int i=0;i<arr1.length;i++) {
                for (int j=0;j<arr1[0].length;j++) {
                    ansArr[i][j] = arr1[i][j] + arr2[i][j];
                }
            }
        } else {
            System.out.println("Arrays are of not same size");
        }
        return ansArr;
    }

    public static int[][] multiplicationOfMatrix(int[][] arr1,int[][] arr2) {
        if (arr1[0].length == arr2.length) {
            int[][] ansArr = new int[arr1.length][arr2[0].length];
            for(int i=0;i<arr1.length;i++) {
                for(int j=0;j<arr2[0].length;j++) {
                    for (int k=0;k<arr2.length;k++) {
                        ansArr[i][j] = ansArr[i][j] + (arr1[i][k] * arr2[k][j]);
                    }
                }
            }
            return ansArr;
        } else {
            System.out.println("Cant multiply the arrays");
            return null;
        }
    }

    public static void matrixColumnTraversal(int[][] arr) {
        for (int i=0;i< arr[0].length;i++) {
            for(int j=0;j<arr.length;j++) {
                System.out.print(arr[j][i] + " ");
            }
        }
    }
    public static void waveTraversal(int[][] arr) {
        for (int i=0;i<arr[0].length;i++) {
            if (i%2==0) {
                for (int j=0;j<arr.length;j++) {
                    System.out.print(arr[j][i] + " ");
                }
            } else {
                for (int j=arr.length-1;j>=0;j--) {
                    System.out.print(arr[j][i] + " ");
                }
            }
        }
    }
    private static void searchIn2DSortedArray(int[][] arr,int target) {
        // Taking 2 pointer approach help
        int i=0;
        int j=arr[0].length-1;
        while (i<arr.length && j>=0) {
            if (arr[i][j] == target) {
                System.out.printf("%s-%s",i,j);
                System.out.println();
                break;
            } else if (arr[i][j] > target) {
                j--;
            } else {
                i++;
            }
        }
    }

    private static void spiralTraversal(int[][] arr) {
        int top=0;
        int bottom=arr.length-1;
        int left=0;
        int right=arr[0].length-1;
        while (top<=bottom  && left<=right) {
            //left to right
            for (int i=left;i<=right;i++) {
                System.out.print(arr[top][i] + " ");
            }
            top++;
            for (int i=top;i<=bottom;i++) {
                System.out.print(arr[i][right] + " ");
            }
            right--;
            if (top<=bottom) {
                for (int i = right; i >= left; i--) {
                    System.out.print(arr[bottom][i] + " ");
                }
                bottom--;
            }
            if (left<=right) {
                for (int i = bottom; i >= top; i--) {
                    System.out.print(arr[i][left] + " ");
                }
                left++;
            }
        }
    }

    private static int[][] transposeOfMatrix(int[][] arr) {
        if (arr.length == arr[0].length) {
            for(int i=0;i< arr.length;i++) {
                for (int j=i+1;j<arr[0].length;j++) {
                    int temp = arr[i][j];
                    arr[i][j] = arr[j][i];
                    arr[j][i] = temp;
                }
            }
            return arr;
        } else {
            int[][] transposeMatrix = new int[arr.length][arr[0].length];
            for (int i=0;i<arr.length;i++) {
                for (int j=0;j<arr[0].length;j++) {
                    transposeMatrix[i][j] = arr[j][i];
                }
            }
            return transposeMatrix;
        }
    }

    private static int[][] rotateImage90(int[][] arr) {
        int[][] transposeMatrix = transposeOfMatrix(arr);
        for (int i=0;i<arr.length;i++) {
            int j=0;
            int k= arr.length-1;
            while (j<k) {
                int temp = arr[i][j];
                transposeMatrix[i][j] = transposeMatrix[i][k];
                transposeMatrix[i][k] = temp;
                j++;
                k--;
            }
        }
        return transposeMatrix;
    }

    private static int[][] setMatrixToZero(int[][] arr) {
        int rows = arr.length;
        int columns = arr[0].length;
        boolean firstRow = false;
        boolean firstColumn = false;

        //1. first check if first row and first col has any zero
        //2. start from second row and column and check if any zero then set index of first row and first column as 0
        //3. start from second row and column again and check if the index of first row is zero then set the whole row as 0 same with column
        //4. based on the first row and first column zero presence set the whole first row and column as zero.

        for (int i=0;i<rows;i++) {
            if (arr[i][0]==0) {
                firstColumn = true;
                break;
            }
        }
        for (int i=0;i<columns;i++) {
            if (arr[0][i]==0) {
                firstRow = true;
                break;
            }
        }
        for(int i=1;i<rows;i++) {
            for (int j=1;j<columns;j++) {
                if (arr[i][j] == 0) {
                    arr[i][0] = 0;
                    arr[0][j] = 0;
                }
            }
        }

        for (int i=1;i<rows;i++) {
            if (arr[i][0] == 0) {
                for (int j=1;j<columns;j++) {
                    arr[i][j] = 0;
                }
            }
        }

        for (int i=1;i<columns;i++) {
            if (arr[0][i] == 0) {
                for (int j=1;j<columns;j++) {
                    arr[j][i] = 0;
                }
            }
        }

        if (firstRow) {
            for (int i=0;i<columns;i++) {
                arr[0][i] = 0;
            }
        }
        if (firstColumn) {
            for (int i=0;i<rows;i++) {
                arr[i][0] = 0;
            }
        }
        return arr;
    }

}

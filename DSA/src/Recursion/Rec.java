package Recursion;

public class Rec {
    public static void main(String[] args) {
        printNumberInDescendingOrder(5);
        System.out.println();
        printNumberInAscendingOrder(5);
        System.out.println();
        System.out.println(sumOfNNaturalNumbers(5));
        System.out.println(factorial(5));
        System.out.println(powerOfTwoNumber(2,3));
        System.out.println(sumAllValueInsideArray(new int[] {1,2,3,4,5},0));
        System.out.println(sumOfDigit(1234));
        System.out.println(isSorted(new int[] {1,2,3,4,5},0));
        System.out.println(isSorted(new int[] {1,2,3,5,4},0));
        System.out.println(removeMFromString("abcmmdsmm"));
    }
    private static void printNumberInDescendingOrder(int n) {
        if (n>0) {
            System.out.print(n + " ");
            printNumberInDescendingOrder(n-1);
        }
    }
    private static void printNumberInAscendingOrder(int n) {
        if (n>0) {
            printNumberInAscendingOrder(n-1);
            System.out.print(n + " ");
        }
    }
    private static int sumOfNNaturalNumbers(int n) {
        if (n==1) {
            return 1;
        } else {
            return n + sumOfNNaturalNumbers(n-1);
        }
    }
    private static int factorial(int n) {
        if (n==1) {
            return 1;
        } else {
            return n * factorial(n-1);
        }
    }
    private static int powerOfTwoNumber(int n,int m) {
        if (m==0) {
            return 1;
        } else  {
            return n * powerOfTwoNumber(n,m-1);
        }
    }
    private static int sumAllValueInsideArray(int[] arr,int index) {
        if (index >= arr.length) {
            return 0;
        } else {
            return arr[index] + sumAllValueInsideArray(arr,index+1);
        }
    }
    private static int sumOfDigit(int digit) {
        if (digit == 0)  {
            return 0;
        } else {
            return digit%10 + sumOfDigit(digit/10);
        }
    }
    private static boolean isSorted(int[] arr,int index) {
        if (index == arr.length -1) {
            return true;
        }
        if (arr[index] > arr[index+1]) {
            return false;
        }
        return isSorted(arr,index+1);
    }
    private static String removeMFromString(String s) {
        if (s.isEmpty()) {
            return "";
        }
        if (Character.toString(s.charAt(0)).equals("m")) {
            return removeMFromString(s.substring(1));
        }
        return s.charAt(0) + removeMFromString(s.substring(1));
    }
}

package Recursion;

import java.util.ArrayList;
import java.util.List;

public class Rec2 {
    public static void main(String[] args) {
        List<String> s = new ArrayList<String>();
        System.out.println(nthTermOfFib(5));
        System.out.println(lengthOfString("abcde"));
        System.out.println(maxOfArray(new int[] {2,4,6,9,1},0));
        System.out.println(moveXtoEnd("xaxbvxcd"));
        System.out.println(removeDuplicateFromString("abbcddaeedf",new boolean[30]));
        System.out.println(placeTiles(5,2));
        subSeqRec("abc",1,"",s);
        System.out.println(s);
    }
    private static int nthTermOfFib(int n) {
        if (n==0 || n==1) {
            return n;
        }
        return nthTermOfFib(n-1) + nthTermOfFib(n-2);
    }
    private static int lengthOfString(String s) {
        if (s.isEmpty()) {
            return 0;
        }
        return 1 + lengthOfString(s.substring(1));
    }
    private static int maxOfArray(int[] arr,int index) {
        if (index == arr.length) {
            return Integer.MIN_VALUE;
        }
        return Math.max(arr[index],maxOfArray(arr,index+1));
    }
    private static String moveXtoEnd(String s) {
        if (s.isEmpty()) {
            return "";
        }
        if (Character.toString(s.charAt(0)).equals("x")) {
            return moveXtoEnd(s.substring(1)) + s.charAt(0);
        }
        return s.charAt(0) + moveXtoEnd(s.substring(1));
    }
    private static String removeDuplicateFromString(String s,boolean[] arr) {
        if (s.isEmpty()) {
            return "";
        }
        if (!arr[s.charAt(0) - 'a']) {
            arr[s.charAt(0) - 'a'] = true;
            return s.charAt(0) + removeDuplicateFromString(s.substring(1),arr);
        } else {
            return removeDuplicateFromString(s.substring(1),arr);
        }
    }
    private static int placeTiles(int n,int m) {
        if (n==m) {
            return 2;
        }
        if (n<m) {
            return 1;
        }
        return placeTiles(n-1,m) + placeTiles(n-m,m);
    }
    private static void subSeqRec(String s, int index, String ns, List schar) {
        if (s.isEmpty()) {
            schar.add(ns);
            return;
        }
        subSeqRec(s.substring(1),index+1,ns + s.charAt(0),schar);
        subSeqRec(s.substring(1),index+1,ns,schar);
    }
}

package Strings;

import java.util.Arrays;

public class String1 {
    public static void main(String[] args) {
        reverseString1("Hello");
        reverseString2("Hello");
        countFrequency("hello");
        countFrequencyIfCapitalLetters("Hello");
        countOfAnagram("cbacbabacd","abc");
        uniqueElementInString("abhinav");
        longestSubStringWithoutRepeatingChars("abccbde");
        longestSubStringWithCharReplacement("aabab",0);
    }
    private static void reverseString1(String s) {
        String ansString = "";
        for(int i = s.length()-1;i>=0;i--) {
            ansString = ansString + s.charAt(i);
        }
        System.out.println(ansString);
    }
    private static void reverseString2(String s) {
        int i=0;
        int j=s.length()-1;
        char[] charArray = s.toCharArray();
        while (j>i) {
            char temp = charArray[i];
            charArray[i] = charArray[j];
            charArray[j] = temp;
            i++;
            j--;
        }
        System.out.println(new String(charArray));
    }
    private static void countFrequency(String s) {
        int[] frequencyArray = new int[26];
        for (int i=0;i<s.length();i++) {
            frequencyArray[s.charAt(i) - 'a'] += 1;
        }
        for (int i=0;i<frequencyArray.length;i++) {
            if (frequencyArray[i] != 0) {
                String s1 = Character.toString((char) (i + 'a')) + ":" + frequencyArray[i];
                System.out.println(s1);
            }
        }
    }
    private static void countFrequencyIfCapitalLetters(String s) {
        int[] frequencyArray = new int[26];
        for (int i=0;i<s.length();i++) {
            if ((int)(s.charAt(i)) > 97) {
                frequencyArray[s.charAt(i) - 'a'] += 1;
            } else {
                frequencyArray[s.charAt(i) - 'A'] += 1;
            }
        }
        for (int i=0;i<frequencyArray.length;i++) {
            if (frequencyArray[i] != 0) {
                String s1 = Character.toString((char) (i + 'a')) + ":" + frequencyArray[i];
                System.out.println(s1);
            }
        }
    }
    private static void countOfAnagram(String s, String p) {
        int[] sArr = new int[26];
        int[] pArr = new int[26];
        for (int i=0;i<p.length();i++) {
            pArr[p.charAt(i) - 'a'] += 1;
        }
        int i=0;
        int j=0;
        int count=0;
        while (j<s.length()) {
            sArr[s.charAt(j) - 'a']++;
            if (j-i+1 == p.length()) {
                if (Arrays.equals(sArr,pArr)) {
                    count++;
                }
                sArr[s.charAt(i)-'a']--;
                i++;
            }
            j++;
        }
        System.out.println(count);
    }
    private static void uniqueElementInString(String s) {
        int[] sArr = new int[26];
        for (int i=0;i<s.length();i++) {
            sArr[s.charAt(i) - 'a'] += 1;
        }
        for (int i=0;i<s.length();i++) {
            if (sArr[s.charAt(i) - 'a'] == 1) {
                System.out.print(s.charAt(i) + " ");
            }
        }
    }
    private static void longestSubStringWithoutRepeatingChars(String s) {
        int[] sArr = new int[26];
        int i=0;
        int j=0;
        int maxLength = Integer.MIN_VALUE;
        System.out.println();
        while (j<s.length()) {
            sArr[s.charAt(j) - 'a']++;
            while (sArr[s.charAt(j) - 'a'] > 1) {
                sArr[s.charAt(i) - 'a']--;
                i++;
            }
            maxLength = Math.max(maxLength,j-i+1);
            j++;
        }
        System.out.println(maxLength);
    }
    private static void longestSubStringWithCharReplacement(String s, int k) {
        int[] sArr = new int[26];
        int maxLength = Integer.MIN_VALUE;
        int maxCount = Integer.MIN_VALUE;
        int i=0;
        int j=0;
        while (j<s.length()) {
            sArr[s.charAt(j) - 'a']++;
            maxCount = Math.max(maxCount,sArr[s.charAt(j) -'a']);
            while ((j-i+1) - maxCount > k) {
                sArr[s.charAt(i) - 'a']--;
                maxCount = Math.max(maxCount,sArr[s.charAt(j) - 'a']);
                i++;
            }
            maxLength = Math.max(maxLength,j-i+1);
            j++;
        }
        System.out.println(maxLength);
    }
}

package Strings;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Hackerrank {
    public static void main(String[] args) {
        String s = "abcabcbb";
        System.out.println(longestSubstringWithoutRepeatingCharacters(s));
        isomorphicString("egg","add");
    }
    public static int longestSubstringWithoutRepeatingCharacters(String s) {
        int i=0;
        int j=0;
        int maxLength = Integer.MIN_VALUE;
        int[] charArray = new int[s.length()];
        while (j<s.length()) {
            charArray[s.charAt(j) - 'a'] += 1;
            while (charArray[s.charAt(j) - 'a'] > 1) {
                charArray[s.charAt(i) - 'a'] -= 1;
                i++;
            }
            maxLength = Math.max(maxLength,j-i+1);
            j++;
        }
        return maxLength;
    }
    public static boolean isomorphicString(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        Map<Character,Integer> sMap = new HashMap<>();
        for (int i=0;i<s.length();i++) {
            sMap.put(s.charAt(i),sMap.getOrDefault(s.charAt(i),0) + 1);
        }
        Map<Character,Integer> tMap = new HashMap<>();
        for (int i=0;i<t.length();i++) {
            tMap.put(t.charAt(i),tMap.getOrDefault(t.charAt(i),0) + 1);
        }
        List<Integer> sValues = sMap.values().stream().collect(Collectors.toList());
        List<Integer> tValues = tMap.values().stream().collect(Collectors.toList());
        if (sValues.equals(tValues)) {
            return true;
        }
        return false;
    }

}

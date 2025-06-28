package HashMaps;

import java.util.*;


public class hashmap2 {
    public static void main(String[] args) {
        String[] arr = new String[] {"eat","tea","tan","ate","net","bat","ant","ten","tab"};
        groupAnagramsQuestion(arr);
        longestSubStringWithDistinctKChars("Abhinav");
        longestSubArrayWithSumk(new int[] {1,2,3,4,2,5,1,3,2,1});
        contiguousArrayProblem(new int[] {0,0,0,1,1,1});
    }
    private static void groupAnagramsQuestion(String[] arr) {
        HashMap<String, List<String>> map = new HashMap<>();
        for (String s : arr) {
            char[] characterArray = s.toCharArray();
            Arrays.sort(characterArray);
            String newString = new String(characterArray);
            if (!map.containsKey(newString)) {
                map.put(newString,new ArrayList<>());
            }
            map.get(newString).add(s);
        }
        System.out.println(new ArrayList<>(map.values()));
    }
    private static void longestSubStringWithDistinctKChars(String s) {
        HashMap<Character,Integer> map  = new HashMap<>();
        char[] charArray = s.toCharArray();
        int i=0;
        int j=0;
        int maxLength = 0;
        while (j<s.length()) {
            map.put(charArray[j], map.getOrDefault(charArray[j],0)+1);
            while (map.size() > 4) {
                map.put(charArray[i],map.get(charArray[i])-1);
                if (map.get(charArray[i]) == 0) {
                    map.remove(charArray[i]);
                }
                i++;
            }
            maxLength = Math.max(maxLength,j-i+1);
            j++;
        }
        System.out.println(maxLength);
    }
    private static void longestSubArrayWithSumk(int[] arr) {
        int i = 0;
        int cws = 0;
        int maxLength = 0;
        HashMap<Integer,Integer> map = new HashMap<>();
        while (i<arr.length) {
            cws = cws + arr[i];
            if (cws == 6) {
                maxLength = i+1;
            }
            if (map.containsKey(cws-6)) {
                maxLength = Math.max(maxLength,i-map.get(cws-6));
            }
            if (!map.containsKey(cws)) {
                map.put(cws,i);
            }
            i++;
        }
        System.out.println(maxLength);
    }
    private static void contiguousArrayProblem(int[] arr) {
        int i = 0;
        int cws = 0;
        int maxLength = 0;
        HashMap<Integer,Integer> map = new HashMap<>();
        for (int j=0;j<arr.length;j++) {
            if (arr[j] == 0) {
                arr[j] = -1;
            }
        }
        while (i<arr.length) {
            cws = cws + arr[i];
            if (cws == 0) {
                maxLength = i+1;
            }
            if (map.containsKey(cws)) {
                maxLength = Math.max(maxLength,i-map.get(cws));
            }
            if (!map.containsKey(cws)) {
                map.put(cws,i);
            }
            i++;
        }
        map.forEach((key,value) -> {

        });
        System.out.println(maxLength);
    }
}

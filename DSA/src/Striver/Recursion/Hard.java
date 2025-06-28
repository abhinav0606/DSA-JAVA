package Striver.Recursion;

import java.util.ArrayList;
import java.util.List;

public class Hard {
    public static void main(String[] args) {
        System.out.println(letterCombinations("34"));
    }
    // This question is almost a similar question to a kind of subsequence pattern of recursion but with a twist
    // So like keypad phones we will keep a track of all those combinations with index like 0 and 1 as "" and other index as the combination
    // Now we will keep a track of indexing first we will take the 0 index for the stringed digit
    // and will iterate on the combination and make index + 1 and make the current as current + combination.charAt(i)
    // and base condition will be index == digits.length() and add it as part of like
    public static List<String> letterCombinations(String digits) {
        String s = "";
        List<String> result  = new ArrayList<>();
        if (digits.isEmpty()) return new ArrayList<>();
        List<String> map = new ArrayList<>(List.of("","","abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"));
        buildLetterCombination(0,s,result,map,digits);
        return result;
    }
    private static void buildLetterCombination(int index,String s,List<String> result,List<String> map,String digit) {
        if (index == digit.length()) {
            result.add(s);
            return;
        }
        String combination = map.get(digit.charAt(index) - '0');
        for (int i=0;i<combination.length();i++) {
            buildLetterCombination(index+1,s + combination.charAt(i),result,map,digit);
        }
    }
}


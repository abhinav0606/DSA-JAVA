package Recursion;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.stream.Collectors;

public class Hackerrank {
    public static void main(String[] args) {
        String s = removeDuplicateFromString("aaabbbbccccdddd");
        System.out.println(s);
    }
    private static String removeDuplicateFromString(String s) {
        Set<Character> characters = new LinkedHashSet<>();
        for (char c : s.toCharArray()) {
            characters.add(c);
        }
        return characters.stream().map(String::valueOf).collect(Collectors.joining());
    }
}

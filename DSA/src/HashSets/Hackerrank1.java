package HashSets;

import java.util.HashSet;

public class Hackerrank1 {
    public static void main(String[] args) {

    }
    private static int findDistinctAbsoluteValue(int [] arr) {
        HashSet<Integer> hs = new HashSet<>();
        for (int i : arr) {
            hs.add(Math.abs(i));
        }
        return hs.size();
    }
}

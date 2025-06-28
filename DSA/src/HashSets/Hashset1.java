package HashSets;

import java.util.*;

public class Hashset1 {
    public static void main(String[] args) {
        System.out.println(happyNumber(19));
        System.out.println(happyNumberFastSlow(19));
        System.out.println(longestConsecutiveStreak(new int[]{100, 4, 200, 1, 3, 2}));
        System.out.println(longestStreakUsingHashset(new int[]{100, 4, 200, 1, 3, 2}));
        System.out.println(containsDuplicate(new int[]{1, 2, 3, 1, 3, 4}));
        System.out.println(containsDuplicate(new int[]{1, 2, 3, 4, 5, 6}));
        System.out.println(findAllDuplicatesBruteForce(new int[]{1, 2, 3, 4, 3, 3, 1, 2}));
        System.out.println(findAllDuplicateHashset(new int[]{1, 1, 2, 2, 3, 4, 5, 2, 3, 4}));
        System.out.println(fourSomeProblemBruteForce(new int[]{1, 0, -1, 0, -2, 2}));
        System.out.println(fourSomeProblem(new int[]{1, 0, -1, 0, -2, 2},0));
    }

    // Happy number problem
    private static int sumOfSquare(int number) {
        int sum = 0;
        while (number != 0) {
            int digit = number % 10;
            sum = sum + (digit * digit);
            number = number / 10;
        }
        return sum;
    }

    private static boolean happyNumber(int number) {
        HashSet<Integer> hs = new HashSet<>();
        while (!hs.contains(number)) {
            hs.add(number);
            number = sumOfSquare(number);
            if (number == 1) {
                return true;
            }
        }
        return false;
    }

    // Happy path using fast and slow pointer
    // whenever we get something like loop and cycle patter use floyd warshal
    private static boolean happyNumberFastSlow(int number) {
        int fast = number;
        int slow = number;
        do {
            slow = sumOfSquare(slow);
            fast = sumOfSquare(sumOfSquare(fast));
            if (fast == 1) {
                return true;
            }
        } while (slow != fast);
        return false;
    }

    // Longest consecutive elements
    private static int longestConsecutiveStreak(int[] arr) {
        Arrays.sort(arr);
        int cs = 1;
        int ls = 0;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] == arr[i - 1]) {
                continue;
            } else if (arr[i] == arr[i - 1] + 1) {
                cs = cs + 1;
            } else {
                ls = Math.max(cs, ls);
            }
        }
        return ls;
    }

    private static int longestStreakUsingHashset(int[] arr) {
        HashSet<Integer> hs = new HashSet<>();
        for (int i : arr) {
            hs.add(i);
        }
        int streak = 0;
        for (int i : hs) {
            if (!hs.contains(i - 1)) {
                int current = i;
                int cs = 1;
                while (hs.contains(current + 1)) {
                    current = current + 1;
                    cs = cs + 1;
                }
                streak = Math.max(cs, streak);
            }
        }
        return streak;
    }

    private static boolean containsDuplicate(int[] arr) {
        HashSet<Integer> hs = new HashSet<>();
        for (int i : arr) {
            if (hs.contains(i)) {
                return true;
            }
            hs.add(i);
        }
        return false;
    }

    private static List<Integer> findAllDuplicatesBruteForce(int[] arr) {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] == arr[j] && !result.contains(arr[i])) {
                    result.add(arr[i]);
                }
            }
        }
        return result;
    }

    private static HashSet<Integer> findAllDuplicateHashset(int[] arr) {
        HashSet<Integer> result = new HashSet<>();
        HashSet<Integer> hs = new HashSet<>();
        for (int i : arr) {
            if (hs.contains(i)) {
                result.add(i);
            } else {
                hs.add(i);
            }
        }
        return result;
    }

    private static List<List<Integer>> fourSomeProblemBruteForce(int[] arr) {
        List<List<Integer>> result = new ArrayList<>();
        for (int a = 0; a < arr.length - 3; a++) {
            if (a > 0 && arr[a] == arr[a - 1]) continue;
            for (int b = a + 1; b < arr.length - 2; b++) {
                if (b > a + 1 && arr[b] == arr[b - 1]) continue;
                for (int c = b + 1; c < arr.length - 1; c++) {
                    if (c > b + 1 && arr[c] == arr[c - 1]) continue;
                    for (int d = c + 1; d < arr.length; d++) {
                        if (d > c + 1 && arr[d] == arr[d - 1]) continue;
                        if (arr[a] + arr[b] + arr[c] + arr[d] == 0) {
                            result.add(List.of(arr[a], arr[b], arr[c], arr[d]));
                        }
                    }
                }
            }
        }
        return result;
    }

    // Foursome problem approach:
    // Sort the array to avoid duplicates and for a good ordering
    // keep two iteration a and b like the brute force
    // now we wanted that target - arr[a]-arr[b] == 0
    // so keep another iteration as c to find that complemented value
    // use hashset to keep the track of complement
    private static List<List<Integer>> fourSomeProblem(int [] arr, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(arr);
        for (int a = 0; a < arr.length - 3; a++) {
            if (a > 0 && arr[a] == arr[a - 1]) continue;
            for (int b = a + 1; b < arr.length - 2; b++) {
                if (b > a + 1 && arr[b] == arr[b - 1]) continue;
                HashSet<Integer> seen = new HashSet<>();
                for (int c = b + 1; c < arr.length ; c++) {
                    int compliment = target -arr[a] - arr[b] - arr[c];
                    if (seen.contains(arr[c])) {
                        result.add(List.of(arr[a],arr[b],arr[c],compliment));
                    }
                    seen.add(compliment);
                }
            }
        }
        return result;
    }
}

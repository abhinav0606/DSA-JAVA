package Striver.Revision;

import java.util.*;
import java.util.stream.Collectors;

public class DailyDSA {
    public static void main(String[] args) {
        System.out.println(isAutomorphic(90625));
        System.out.println(isMajorityElement(new int[]{1}, 901));
        System.out.println(fixedPoint(new int[]{-8, -3, 2, 5, 9}));
        System.out.println(validWordAbbreviation("wxoyfeslfxa", "w7f25"));
        System.out.println(averageHeightOfBuildings(new int[][]{{1, 3, 2}, {2, 5, 3}, {2, 8, 3}}));
        System.out.println(wiggleSort(new int[] {3,5,2,1,6,4}));
    }

    public List<Integer> orArray(List<Integer> A) {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < A.size() - 1; i++) {
            result.add(A.get(i) | A.get(i + 1));
        }
        return result;
    }

    public String addFractions(String frac1, String frac2) {
        String[] s1 = frac1.split("/");
        String[] s2 = frac2.split("/");
        int a = Integer.parseInt(s1[0]);
        int b = Integer.parseInt(s1[1]);
        int c = Integer.parseInt(s2[0]);
        int d = Integer.parseInt(s2[1]);
        int gcd = gcd(a * d + c * b, b * d);
        String numerator = String.valueOf((a * d + c * b) / gcd);
        String denominator = String.valueOf((b * d) / gcd);
        return numerator + "/" + denominator;
    }

    private int gcd(int a, int b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }

    public double averageOfArray(int[] nums) {
        double sum = Arrays.stream(nums).asDoubleStream().sum();
        return sum / nums.length;
    }

    public static boolean isAutomorphic(int n) {
        int number = n;
        long square = 1L * number * number;
        while (number != 0 && square != 0) {
            int a = number % 10;
            long b = square % 10;
            if (a != b) return false;
            number = number / 10;
            square = square / 10;
        }
        return true;
    }

    public static boolean isMajorityElement(int[] nums, int target) {
        if (nums.length == 1 && nums[0] != target) return false;
        int lower = 0;
        int upper = 0;
        int low = 0;
        int high = nums.length - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (nums[mid] >= target) {
                high = mid - 1;
                lower = mid;
            } else {
                low = mid + 1;
            }
        }
        low = 0;
        high = nums.length - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (nums[mid] <= target) {
                low = mid + 1;
                upper = mid;
            } else {
                high = mid - 1;
            }
        }
        return (upper - lower + 1) > nums.length / 2;
    }

    public static int fixedPoint(int[] arr) {
        int low = 0;
        int high = arr.length - 1;
        int ans = -1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (mid == arr[mid]) ans = mid;
            if (mid <= arr[mid]) high = mid - 1;
            else low = mid + 1;
        }
        return ans;
    }

    static class PolyNode {
        int coefficient, exponent;
        PolyNode next = null;

        PolyNode() {
        }

        PolyNode(int x, int y) {
            this.coefficient = x;
            this.exponent = y;
        }

        PolyNode(int x, int y, PolyNode next) {
            this.coefficient = x;
            this.exponent = y;
            this.next = next;
        }
    }

    public PolyNode addPoly(PolyNode poly1, PolyNode poly2) {
        PolyNode node = new PolyNode(0, 0);
        PolyNode current = node;
        while (poly1 != null && poly2 != null) {
            if (poly1.exponent == poly2.exponent) {
                int sum = poly1.coefficient + poly2.coefficient;
                if (sum != 0) {
                    current.next = new PolyNode(sum, poly1.exponent);
                    current = current.next;
                }
                poly1 = poly1.next;
                poly2 = poly2.next;
            } else if (poly1.exponent > poly2.exponent) {
                current.next = new PolyNode(poly1.coefficient, poly1.exponent);
                current = current.next;
                poly1 = poly1.next;
            } else {
                current.next = new PolyNode(poly2.coefficient, poly2.exponent);
                current = current.next;
                poly2 = poly2.next;
            }
        }
        while (poly1 != null) {
            current.next = new PolyNode(poly1.coefficient, poly1.exponent);
            current = current.next;
            poly1 = poly1.next;
        }
        while (poly2 != null) {
            current.next = new PolyNode(poly2.coefficient, poly2.exponent);
            current = current.next;
            poly2 = poly2.next;
        }
        return node.next;
    }


    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }


    public String gameResult(ListNode head) {
        int odd = 0;
        int even = 0;
        ListNode node = head;
        while (node != null) {
            int e = node.val;
            node = node.next;
            int o = node.val;
            if (e > o) even++;
            else if (e < o) odd++;
            else {
                even++;
                odd++;
            }
            node = node.next;
        }
        if (even > odd) return "Even";
        else if (odd > even) return "Odd";
        else return "Tie";
    }

    public boolean validWordSquare(List<String> words) {
        for (int i = 0; i < words.size(); i++) {
            for (int j = 0; j < words.get(0).length(); j++) {
                if (j >= words.size() || i >= words.get(j).length() || words.get(i).charAt(j) != words.get(j).charAt(i))
                    return false;
            }
        }
        return true;
    }

    public int twoSumLessThanK(int[] nums, int k) {
        Arrays.sort(nums);
        int i = 0;
        int j = nums.length - 1;
        int ans = -1;
        while (i < j) {
            if (nums[i] + nums[j] < k) {
                ans = Math.max(ans, nums[i] + nums[j]);
                i++;
            } else {
                j--;
            }
        }
        return ans;
    }

    public static boolean validWordAbbreviation(String word, String abbr) {
        int i = 0;
        int j = 0;
        while (j < abbr.length()) {
            if (Character.isDigit(abbr.charAt(j))) {
                if (abbr.charAt(j) == '0') return false;
                else {
                    int num = 0;
                    while (j < abbr.length() && Character.isDigit(abbr.charAt(j))) {
                        num = num * 10 + (abbr.charAt(j) - '0');
                        j++;
                    }
                    i = i + num;
                }
            } else {
                if (i < word.length() && abbr.charAt(j) != word.charAt(i)) return false;
                i++;
                j++;
            }
        }
        return true;
    }

    public List<String> sortFeatures(List<String> features, List<String> responses) {
        Map<String, Integer> position = new HashMap<>();
        Map<String, Integer> freqMap = new HashMap<>();
        int i = 0;
        for (String feature : features) {
            position.put(feature, i);
            freqMap.put(feature, 0);
            i++;
        }
        for (String response : responses) {
            String[] responseArray = response.split(" ");
            Set<String> set = new HashSet<>(Arrays.asList(responseArray));
            for (String s : set) {
                if (features.contains(s)) {
                    freqMap.put(s, freqMap.getOrDefault(s, 0) + 1);
                }
            }
        }
        features.sort((f1, f2) -> {
            int c1 = freqMap.get(f1);
            int c2 = freqMap.get(f2);
            if (c1 != c2) return c2 - c1;
            return position.get(f1) - position.get(f2);
        });
        return features;
    }

    public static List<List<Integer>> averageHeightOfBuildings(int[][] buildings) {
        List<Integer> points = new ArrayList<>();
        for (int[] arr : buildings) {
            points.add(arr[0]);
            points.add(arr[1]);
        }
        points = points.stream().sorted().distinct().collect(Collectors.toList());
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i <= points.size() - 2; i++) {
            result.add(new ArrayList<>(List.of(points.get(i), points.get(i + 1))));
        }
        Iterator<List<Integer>> it = result.iterator();
        while (it.hasNext()) {
            List<Integer> res = it.next();
            int sum = 0, count = 0;

            for (int[] building : buildings) {
                if (res.get(0) >= building[0] && res.get(1) <= building[1]) {
                    sum += building[2];
                    count++;
                }
            }
            if (count > 0) {
                res.add(sum / count);
            } else {
                it.remove();
            }
        }
        int i = 0;
        List<List<Integer>> res2 = new ArrayList<>();
        int index = 0;
        while (i < result.size()) {
            int avg = result.get(i).get(2);
            int start = result.get(i).get(0);
            int end = result.get(i).get(1);
            i=i+1;
            while (i < result.size() && result.get(i).get(2) == avg && result.get(i-1).get(1)+1 == result.get(i).get(0)) {
                end = result.get(i).get(1);
                i++;
            }
            res2.add(new ArrayList<>(List.of(start, end, avg)));
        }
        return res2;
    }

    public static boolean wiggleSort(int[] nums) {
        for (int i=1;i<nums.length;i=i+2) {
            if (i+1 < nums.length && nums[i-1] <= nums[i] && nums[i] >= nums[i+1]) {
                continue;
            } else {
                if (nums[i-1] > nums[i]) {
                    int temp = nums[i-1];
                    nums[i-1] = nums[i];
                    nums[i] = temp;
                }
                if (i+1 < nums.length && nums[i+1] > nums[i]) {
                    int temp = nums[i+1];
                    nums[i+1] = nums[i];
                    nums[i] = temp;
                }
                }
            }
        return false;
    }
}



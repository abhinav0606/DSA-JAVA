package Striver.GreedAlgo;

import java.util.Arrays;

public class Easy {
    public static void main(String[] args) {
        System.out.println(lemonadeChange(new int[] {5, 5, 10, 20}));
    }
    // Steps : Sorted both the array
    // Used while loop and keep two pointer one is student and other is cookies
    // if student greed <= cookie then student is satisfied so move both pointers
    // but if we are not able to satisfy only move cookies
    public int findMaximumCookieStudents(int[] Student, int[] Cookie) {
        int sGreed = 0;
        int cookie = 0;
        Arrays.sort(Student);
        Arrays.sort(Cookie);
        int studentLength = Student.length;
        int cookieLength = Cookie.length;
        while (sGreed < studentLength && cookie < cookieLength) {
            if (Student[sGreed] <= Cookie[cookie]) {
                sGreed++;
            }
            cookie++;
        }
        return sGreed;
    }

    public static boolean lemonadeChange(int[] bills) {
        int five = 0;
        int ten = 0;
        for (int i=0;i<bills.length;i++) {
            if (bills[i] == 5) five++;
            else if (bills[i] == 10) {
                if (five > 0) {
                    five--;
                    ten++;
                } else {
                    return false;
                }
            }
            else {
                if (ten > 0 && five > 0) {
                    five--;
                    ten--;
                }
                else if (five >= 3) {
                    five = five - 3;
                } else {
                    return false;
                }
            }
        }
        return true;
    }
    // Maintain a maxIndex = 0
    public boolean canJump(int[] nums) {
        int maxIndex = 0;
        for (int i=0;i<nums.length;i++) {
            if (maxIndex < i) return false;
            maxIndex = Math.max(maxIndex,i+nums[i]);
            if (maxIndex >= nums.length-1) return true;
        }
        return true;
    }
}

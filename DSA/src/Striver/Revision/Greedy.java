package Striver.Revision;

import java.awt.datatransfer.ClipboardOwner;
import java.sql.PreparedStatement;
import java.util.*;

public class Greedy {
    public int findMaximumCookieStudents(int[] Student, int[] Cookie) {
        Arrays.sort(Student);
        Arrays.sort(Cookie);
        int sGreed = 0;
        int cGreed = 0;
        while (sGreed < Student.length && cGreed < Cookie.length) {
            if (Student[sGreed] <= Cookie[cGreed]) {
                sGreed++;
            }
            cGreed++;
        }
        return sGreed;
    }

    public boolean lemonadeChange(int[] bills) {
        int five = 0;
        int ten = 0;
        for (int bill : bills) {
            if (bill == 5) five++;
            else if (bill == 10) {
                if (five <= 0) return false;
                five--;
                ten++;
            }
            else {
                if (five > 0 && ten > 0) {
                    five--;
                    ten--;
                } else if (five >= 3) {
                    five = five - 3;
                } else {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean canJump(int[] nums) {
        int maxIndex = 0;
        for (int index = 0;index < nums.length;index++) {
            if (maxIndex < index) return false;
            maxIndex = Math.max(maxIndex, nums[index] + index);
            if (maxIndex >= nums.length-1) return true;
        }
        return true;
    }

    public long solve(int[] bt) {
        Arrays.sort(bt);
        long waitingTime = 0;
        long totalWaitingTime = 0;
        for (int i=1;i<bt.length;i++) {
            waitingTime = waitingTime + bt[i - 1];
            totalWaitingTime = totalWaitingTime + waitingTime;
        }
        return totalWaitingTime/bt.length;
    }

    public int[] JobScheduling(int[][] Jobs) {
        // Sort the arrays first
        int totalProfit = 0;
        int count = 0;
        Arrays.sort(Jobs, (a,b) -> b[2]-a[2]);
        int maxDeadline = Integer.MIN_VALUE;
        for (int i=0;i<Jobs.length;i++) {
            maxDeadline = Math.max(maxDeadline,Jobs[i][1]);
        }
        int[] hashed = new int[maxDeadline];
        Arrays.fill(hashed,-1);
        for (int i=0;i<Jobs.length;i++) {
            int deadline = Jobs[i][1];
            if (hashed[deadline-1] == -1) {
                hashed[deadline-1] = Jobs[i][0];
                totalProfit = totalProfit + Jobs[i][2];
                count++;
            } else {
                int j = deadline;
                while (j-1 >= 0 && hashed[j-1] != -1) {
                    j--;
                }
                if (j-1 >= 0) {
                    hashed[j-1] = Jobs[i][0];
                    totalProfit = totalProfit + Jobs[i][2];
                    count++;
                }
            }
        }
        return new int[] {count,totalProfit};
    }
    public int maxMeetings(int[] start, int[] end) {
        List<int[]> arraysList = new ArrayList<>();
        for (int i=0;i<start.length;i++) {
            arraysList.add(new int[] {start[i],end[i]});
        }
        arraysList.sort(Comparator.comparingInt(arr -> arr[1]));
        int count = 1;
        int limit = arraysList.get(0)[1];
        for (int i=1;i<arraysList.size();i++) {
            if (arraysList.get(i)[0] > limit) {
                count++;
                limit = arraysList.get(i)[1];
            }
        }
        return count;
    }
    public int MaximumNonOverlappingIntervals(int[][] Intervals) {
        Arrays.sort(Intervals, (a,b) -> a[1]-b[1]);
        int limit = Intervals[0][1];
        int count = 0;
        for (int i=1;i<Intervals.length;i++) {
            if (Intervals[i][0] < limit) count++;
            else {
                limit = Intervals[i][1];
            }
        }
        return count;
    }

    public int[][] insertNewInterval(int[][] Intervals, int[] newInterval) {
        // Figure out the intervals that cannot be a part or the individual intervals
        int i=0;
        List<int[]> result = new ArrayList<>();
        while (i < Intervals.length && Intervals[i][1] < newInterval[0]) {
            result.add(Intervals[i]);
            i++;
        }
        while (i < Intervals.length && Intervals[i][0] <= newInterval[1]) {
            newInterval[0] = Math.min(newInterval[0],Intervals[i][0]);
            newInterval[1] = Math.max(newInterval[1],Intervals[i][1]);
            i++;
        }
        result.add(newInterval);
        while (i<Intervals.length) {
            result.add(Intervals[i]);
            i++;
        }
        return result.toArray(new int[result.size()][]);
    }

    public int findPlatform(int[] Arrival, int[] Departure) {
        Arrays.sort(Arrival);
        Arrays.sort(Departure);
        int i=1;
        int j=0;
        int count = 1;
        int ans = 1;
        while (i < Arrival.length && j < Departure.length) {
            if (Arrival[i] <= Departure[j]) {
                count++;
                i++;
            } else {
                count--;
                j++;
            }
            ans = Math.max(ans, count);
        }
        return ans;
    }

    public boolean isValid(String s) {
        int min = 0;
        int max = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') {
                max++;
                min++;
            } else if (c == ')') {
                max--;
                min--;
            } else {
                max++;
                min--;
            }
            if (max < 0) return false;
            if (min < 0) min = 0;
        }
        return min == 0;
    }

    public int candy(int[] ratings) {
        int sum = 1;
        int i = 1;
        int n = ratings.length;
        while (i < n) {
            if (ratings[i] == ratings[i-1]) {
                sum = sum + 1;
                i++;
                continue;
            }
            int peak = 1;
            while (i < n && ratings[i] > ratings[i-1]) {
                peak++;
                sum = sum + peak;
                i++;
            }
            int down = 1;
            while (i < n && ratings[i] < ratings[i-1]) {
                sum = sum + 1;
                i++;
                down++;
            }
            if (down > peak) {
                sum = sum + (down - peak);
            }
        }
        return sum;
    }
    }

package Striver.GreedAlgo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class SchedulingInternal {
    public static void main(String[] args) {
        int[][] arr = {
                {1, 2} , {2, 3} , {3, 4} , {1, 3}
        };
//        System.out.println(Arrays.toString(JobScheduling(arr)));
        System.out.println(MaximumNonOverlappingIntervals(arr));
    }

    // maintain list of waiting time
    // maintain a total time as well
    // sumup the total waiting time and keep a average of it
    public static long solve(int[] bt) {
        Arrays.sort(bt);
        long waitingTime = 0;
        long totalTime = 0;
        for (int i=1;i<bt.length;i++) {
            waitingTime = waitingTime + bt[i-1];
            totalTime = totalTime + waitingTime;

        }
        return totalTime/bt.length;
    }
    // JOB sequencing problem
    public static int[] JobScheduling(int[][] Jobs) {
        // first sort the array on the basis of profit so that we can make maximum profit
        Arrays.sort(Jobs,(a,b) -> b[2]-a[2]);
        int totalProfit = 0;
        int count = 0;
        // Tracking what all jobs are performed
        // Checking which is the max deadline
        int n=0;
        for (int i=0;i<Jobs.length;i++) {
            n = Math.max(n,Jobs[i][1]);
        }

        // Filling all those arrays with -1
        int[] hash = new int[n];
        Arrays.fill(hash,-1);

        for (int i=0;i<Jobs.length;i++) {
            int deadline = Jobs[i][1];
            // Check if deadline is not covered then add that
            if (hash[deadline-1] == -1) {
                hash[deadline-1] = Jobs[i][0];
                totalProfit = totalProfit + Jobs[i][2];
                count++;
            } else {
                // find a slot to fit that job and deadline
                int j = deadline;
                while (j-1 >= 0 && hash[j-1] != -1) {
                    j--;
                }
                if (j-1 >= 0) {
                    hash[j-1] = Jobs[i][0];
                    totalProfit = totalProfit + Jobs[i][2];
                    count++;
                }
            }
        }
        // return how many jobs we have done and profitable count
        return new int[] {count,totalProfit};
    }

    public int maxMeetings(int[] start, int[] end) {
        List<int[]> finalArray = new ArrayList<>();
        for (int i=0;i<start.length;i++) {
            finalArray.add(new int[] {start[i],end[i]});
        }
        finalArray.sort(Comparator.comparingInt(arr -> arr[1]));
        int count = 1;
        // Setting up the limit that next meeting start should be greater than this limit
        // as added count for the first meeting and we will start with the second meeting
        int limit = finalArray.get(0)[1];

        for (int i=1;i<finalArray.size();i++) {
            if (finalArray.get(i)[0] > limit) {
                limit = finalArray.get(i)[1];
                count++;
            }
        }
        return count;
    }

    public static int MaximumNonOverlappingIntervals(int[][] Intervals) {
        Arrays.sort(Intervals,(a,b) -> a[1]-b[1]);
        int count = 0;
        int endingTime = Intervals[0][1];
        for (int i=1;i<Intervals.length;i++) {
            if (Intervals[i][0] < endingTime) count++;
            else {
                endingTime = Intervals[i][1];
            }
        }
        return count;
    }

    public int[][] insertNewInterval(int[][] Intervals, int[] newInterval) {
        List<int[]> result = new ArrayList<>();
        int i=0;
        while (i<Intervals.length && Intervals[i][1] < newInterval[0]) {
            result.add(Intervals[i]);
            i = i+1;
        }
        while (i<Intervals.length && Intervals[i][0] <= newInterval[1]) {
            newInterval[0] = Math.min(Intervals[i][0],newInterval[0]);
            newInterval[1] = Math.min(Intervals[i][1],newInterval[1]);
            i=i+1;
        }
        result.add(newInterval);
        while(i<Intervals.length) {
            result.add(Intervals[i]);
            i=i+1;
        }
        return result.toArray(new int[result.size()][]);
    }

    // Basically here we need to calculate the min number of platform requires to accommodate train
    // Here we can sort both the array for every arrival make the count +1 and for every departure decrease it by 1
    // after that return the max number that we have reached that is the min number of platform
    public int findPlatform(int[] Arrival, int[] Departure) {
        Arrays.sort(Arrival);
        Arrays.sort(Departure);
        // keeping arrival from 1st index as for 0th arrival we are sure that minimum we needed is 1 platform
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
            ans = Math.max(count,ans);
        }
        return ans;
    }


}

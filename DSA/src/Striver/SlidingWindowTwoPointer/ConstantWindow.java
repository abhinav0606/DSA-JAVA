package Striver.SlidingWindowTwoPointer;

public class ConstantWindow {
    public static void main(String[] args) {

    }
    public int maxScore(int[] cardScore, int k) {
        int lsum = 0;
        int rSum = 0;
        int maxSum = 0;
        for (int i=0;i<k;i++) {
            lsum = lsum + cardScore[i];
        }
        maxSum = lsum;
        int rIndex = cardScore.length-1;
        for (int i=k-1;i>=0;i--) {
            lsum = lsum - cardScore[i];
            rSum = rSum + cardScore[rIndex];
            maxSum = Math.max(maxSum,lsum+rSum);
            rIndex--;
        }
        return maxSum;
    }
}

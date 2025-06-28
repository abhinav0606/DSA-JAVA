package Striver.Maths;

import java.util.ArrayList;
import java.util.List;

public class SeiveAlgo {
    public static void main(String[] args) {

    }
    public ArrayList<Integer> primeTillN(int n) {
        boolean[] isPrime = new boolean[n+1];
        ArrayList<Integer> ans = new ArrayList<>();
        for (int i=0;i<=n;i++) {
            isPrime[i] = true;
        }
        for (int i=2;i<=n;i++) {
            if (isPrime[i]) {
                ans.add(i);
                for (int j=i*i;j<=n;j = j+i) {
                    isPrime[j] = false;
                }
            }
        }
        return ans;
    }
}

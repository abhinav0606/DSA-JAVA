package Striver.Recursion;

import java.util.ArrayList;
import java.util.List;

public class Implementation {
    public static void main(String[] args) {
        System.out.println(myPow(2.0000,-2));
    }

    public static double myPow(double x, int n) {
        return Math.pow(x,n);
    }

    // This question is pretty much simple in terms of conditions
    // Lets just consider that we will always start with ( and leave all the remaining position
    // now if opening == closing and opening + closing is equal to 2n then return or add to list
    // if opening is greater than closing then just add +1 to closing and do the same recursion
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        int index = 0;
        String s = "";
        int open = 0;
        int close = 0;
        validParenthesis(index,s,open,close,n,result);
        return result;
    }
    private static void validParenthesis(int index,String s,int open,int close,int n,List<String> list) {
        if (open > n) return;
        if (open == close && open + close == 2*n) {
            list.add(s);
            return;
        }
        validParenthesis(index+1,s + "(",open + 1,close,n,list);
        if (open > close) {
            validParenthesis(index + 1,s + ")",open,close + 1,n,list);
        }
    }

    // Power set is simple we will need to create a list  of integers sub arrays of a given array
    // thing is we will use same indexing like above
    // first we will pick up the single items then we will add the following indexes continuously and will remove the
    // other things
    public List<List<Integer>> powerSet(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> current = new ArrayList<>();
        buildPowerSet(0,result,current,nums);
        return result;
    }
    private static void buildPowerSet(int index,List<List<Integer>> result,List<Integer> current,int[] nums) {
        if (index == nums.length) {
            result.add(new ArrayList<>(current));
            return;
        }
        buildPowerSet(index + 1,result,current,nums);
        current.add(nums[index]);
        buildPowerSet(index+1,result,current,nums);
        current.removeLast();
    }
}

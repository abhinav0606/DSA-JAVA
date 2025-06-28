package Striver.Strings;

public class Medium {
    public static void main(String[] args) {
        reverseWords(" amazing coding skills ");
    }
    public static void reverse(char[] s, int start,int end) {
        while (start < end) {
            char temp = s[start];
            s[start] = s[end];
            s[end] = temp;
            start++;
            end--;
        }
    }
    public static String reverseWords(String s) {
        char[] sArr = s.toCharArray();
        reverse(sArr,0,s.length()-1);
        int start = 0;
        int end = 0;
        int i = 0;int j=0;
        while (j<s.length()) {
            while (j<s.length() && sArr[j] == ' ') {
                j++;
            }
            start = i;
            while (j<s.length() && sArr[j] != ' ') {
                sArr[i] = sArr[j];
                j++;
                i++;
            }
            end = i-1;
            reverse(sArr,start,end);
            if (i < s.length()) sArr[i++] = ' ';
        }
        return new String(sArr).substring(0,end+1);
    }
    public int countRev(String s) {
        int open = 0;
        int close = 0;
        if (s.length() % 2 != 0) return -1;
        for (char ch : s.toCharArray()) {
            if (ch == '(') {
                open++;
            } else {
                // Just for balancing
                if (open > 0) open--;
                else close++;
            }
        }
        return (open/2) + (open%2) + (close/2) + (close%2);
    }

    public String countAndSay(int n) {
        if (n == 1) return "1";
        String prev = countAndSay(n-1);
        int len = prev.length();
        int count = 1;
        String ans = "";
        for (int i=1;i<len;i++) {
            if (prev.charAt(i) == prev.charAt(i-1)) count++;
            else {
                ans = ans + (char)('0' + count);
                ans = ans + prev.charAt(i-1);
                count = 1;
            }
        }
        ans = ans + (char)('0' + count);
        ans = ans + prev.charAt(len-1);
        return ans;
    }
}

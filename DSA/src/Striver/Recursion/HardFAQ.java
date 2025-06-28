package Striver.Recursion;

import java.util.ArrayList;
import java.util.List;

public class HardFAQ {
    public static void main(String[] args) {
        System.out.println(partition("aabaa"));
        int[][] grid = {
                {1, 0, 0, 0},
                {1, 1, 0, 1},
                {1, 1, 0, 0},
                {0, 1, 1, 1}
        };
        System.out.println(findPath(grid));
    }
    public static List<List<String>> partition(String s) {
        List<List<String>> result = new ArrayList<>();
        List<String> current = new ArrayList<>();
        buildPalindromicPartition(0,current,result,s);
        return result;
    }
    private static void buildPalindromicPartition(int index, List<String> current, List<List<String>> result,String s) {
        if (index == s.length()) {
            result.add(new ArrayList<>(current));
            return;
        }
        for (int i = index; i < s.length(); i++) {
            if (palindromeCheck(s, index, i)) {
                String subString = s.substring(index, i + 1);
                current.add(subString);
                buildPalindromicPartition(i + 1, current, result, s);
                current.removeLast();
            }
        }
    }
    private static boolean palindromeCheck(String s, int left, int right) {
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
    public boolean exist(char[][] board, String word) {
        // Checking the starting letter
        for (int i=0;i<board.length;i++) {
            for (int j=0;j<board[i].length;j++) {
                if (board[i][j] == word.charAt(0)) {
                    if (wordSearchHelper(0,i,j,board,word)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    private boolean wordSearchHelper(int index,int i,int j,char[][] board, String word) {
        if (index == word.length()) {
            return true;
        }
        if (i < 0 || j < 0 || i >= board.length || j >= board[0].length || board[i][j] == ' ' || board[i][j] != word.charAt(index)) {
            return false;
        }
        char temp = board[i][j];
        board[i][j] = ' ';
        // top
        boolean top = wordSearchHelper(index + 1,i-1,j,board,word);
        // left
        boolean left = wordSearchHelper(index + 1,i,j-1,board,word);
        // right
        boolean right = wordSearchHelper(index+1,i,j+1,board,word);
        // bottom
        boolean bottom = wordSearchHelper(index+1,i+1,j,board,word);

        board[i][j] = temp;
        return top || bottom || left || right;
    }

    // N- Queens Problem
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> result  = new ArrayList<>();
        List<String> current = new ArrayList<>();
        int index = 0;
        String s = ".".repeat(n);
        for (int i=0;i<n;i++) {
          current.add(s);
        }
        solvePuzzle(index,result,current);
        return result;
    }
    private static void solvePuzzle(int index,List<List<String>> result,List<String> current) {
        if (index == current.size()) {
            result.add(new ArrayList<>(current));
            return;
        }
        for (int row = 0;row < current.size();row++) {
            if (safeToPlace(row,index,current)) {
                char[] charArray = current.get(row).toCharArray();
                charArray[index] = 'Q';
                current.set(row,new String(charArray));
                solvePuzzle(index+1,result,current);
                charArray[index] = '.';
                current.set(row,new String(charArray));
            }
        }
    }
    private static boolean safeToPlace(int row,int col,List<String> current) {
        int r = row;
        int c = col;
        //  top left diagonal
        while (r >=0 && c >= 0) {
            if (current.get(r).charAt(c) == 'Q') return false;
            r--;
            c--;
        }
        r = row;
        c = col;
        // low left diagonal
        while (r <current.size() && c >= 0) {
            if (current.get(r).charAt(c) == 'Q') return false;
            r++;
            c--;
        }
        r = row;
        c = col;
        // left check
        while (c >= 0) {
            if (current.get(r).charAt(c) == 'Q') return false;
            c--;
        }
        return true;
    }

    public static List<String> findPath(int[][] grid) {
        List<String> result = new ArrayList<>();
        if (grid[0][0] == 0 || grid[grid.length-1][grid[0].length-1] == 0) return result;
        String current = "";
        pathFinder(0,0,current,result,grid);
        return result;
    }
    private static void pathFinder(int row,int col,String current,List<String> result,int[][] grid) {
        if (col == grid[0].length-1 && row == grid.length-1) {
            result.add(current);
            return;
        }
        if (row < 0 || col < 0 || row >= grid.length || col >= grid[0].length || grid[row][col] == 0) {
            return;
        }
        int temp = grid[row][col];
        grid[row][col] = 0;
        //UP
        pathFinder(row-1,col,current + "U",result,grid);
        //DOWN
        pathFinder(row+1,col,current + "D",result,grid);
        //LEFT
        pathFinder(row,col-1,current + "L",result,grid);
        //RIGHT
        pathFinder(row,col+1,current + "R",result,grid);

        grid[row][col] = temp;
    }

    // graph colouring question
    public boolean graphColoring(int[][] edges, int m, int n) {
        List<List<Integer>> adjList = new ArrayList<>();
        for (int i=0;i<n;i++) {
            adjList.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            adjList.get(edge[0]).add(edge[1]);
            adjList.get(edge[1]).add(edge[0]);
        }
        int[] colorArray = new int[n];
        return colorGraph(0,colorArray,adjList,n,m);
    }
    private boolean colorGraph(int index, int[] colorArray, List<List<Integer>> adj,int n,int m) {
        if (index == n) return true;
        for (int i=1;i<=m;i++) {
            if (isSafeToColor(index,i,colorArray,adj)) {
                colorArray[index] = i;
                if (colorGraph(index+1,colorArray,adj,n,m)) {
                    return true;
                }
                colorArray[index] = -1;
            }
        }
        return false;
    }
    private boolean isSafeToColor(int index,int color,int[] colorArray,List<List<Integer>> adj) {
        for (int neighbor : adj.get(index)) {
            if (colorArray[neighbor] == color) return false;
        }
        return true;
    }
    //
    public void solveSudoku(char[][] board) {
        solve(board);
    }
    private boolean solve(char[][] board) {
        for (int i=0;i<9;i++) {
            for (int j=0;j<9;j++) {
                if (board[i][j] == '.') {
                    for (char k='1';k<='9';k++) {
                        if (sodukoRules(board,i,j,k)) {
                            board[i][j] = k;
                            if (solve(board)) return true;
                            board[i][j] = '.';
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }
    private boolean sodukoRules(char[][] board,int row,int col,char digit) {
        for (int i=1;i<=9;i++) {
            if (board[row][i] == digit || board[i][col] == digit) return false;
        }
        int startRow = (row/3)*3;
        int startCol = (col/3)*3;
        for (int i=startRow;i<startRow+3;i++) {
            for (int j=startCol;j<startCol+3;j++) {
                if (board[i][j] == digit) return false;
            }
        }
        return true;
    }
}

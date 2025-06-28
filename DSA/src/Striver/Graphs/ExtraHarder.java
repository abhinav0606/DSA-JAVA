package Striver.Graphs;

import java.util.*;

public class ExtraHarder {
    public static void main(String[] args) {

    }
    static class DisjointSet {

        int[] size;
        int[] parent;
        int[] rank;

        public DisjointSet(int n) {
            size = new int[n+1];
            parent = new int[n+1];
            rank = new int[n+1];
            for (int i=0;i<=n;i++) {
                parent[i] = i;
            }
        }

        public int findParent(int i) {
            if (parent[i] == i) return i;
            int p = findParent(parent[i]);
            return p;
        }

        public boolean find(int u, int v) {
            return findParent(u) == findParent(v);
        }

        public void unionByRank(int u, int v) {
            int up = findParent(u);
            int vp = findParent(v);
            if (up == vp) return;
            if (rank[up] < rank[vp]) {
                parent[up] = vp;
            } else if (rank[up] > rank[vp]) {
                parent[vp] = up;
            } else {
                parent[vp] = up;
                rank[up]++;
            }
        }

        public void unionBySize(int u, int v) {
            int up = findParent(u);
            int vp = findParent(v);
            if (up == vp) return;
            if (size[up] < size[vp]) {
                parent[up] = vp;
                size[vp] = size[vp] + size[up];
            }  else {
                parent[vp] = up;
                size[up] = size[vp] + size[up];
            }
        }
    }
    public int solve(int n, int[][] Edge) {
        int size = Edge.length;
        if (size < n-1) return -1;
        DisjointSet ds = new DisjointSet(n);
        for (int i=0;i<size;i++) {
            ds.unionByRank(Edge[i][0],Edge[i][1]);
        }
        int count = 0;
        for (int i=0;i<n;i++) {
            if (ds.parent[i] == i) count++;
        }
        return count-1;
    }

    static List<List<String>> accountsMerge(List<List<String>> accounts) {
        int n = accounts.size();
        DisjointSet ds = new DisjointSet(n);
        HashMap<String,Integer> map = new HashMap<>();
        // Map creation and disjoint union making
        for (int i=0;i<n;i++) {
            for (int j=1;j<accounts.get(i).size();j++) {
                if (!map.containsKey(accounts.get(i).get(j))) {
                    map.put(accounts.get(i).get(j), i);
                } else {
                    ds.unionByRank(i,map.get(accounts.get(i).get(j)));
                }
            }
        }
        // Merging the mails
        List<List<String>> mergedMail = new ArrayList<>();
        for (int i=0;i<n;i++) {
            mergedMail.add(new ArrayList<>());
        }
        for (Map.Entry<String,Integer> entry : map.entrySet()) {
            int parentNode = ds.parent[entry.getValue()];
            mergedMail.get(parentNode).add(entry.getKey());
        }

        List<List<String>> ans = new ArrayList<>();
        for (int i=0;i<n;i++) {
            if (mergedMail.get(i).isEmpty()) continue;
            Collections.sort(mergedMail.get(i));
            List<String> temp = new ArrayList<>();
            temp.add(accounts.get(i).get(0));
            temp.addAll(mergedMail.get(i));
            ans.add(temp);
        }
        return ans;
    }


    private boolean isValid(int row, int col,
                            int n, int m) {

        if (row < 0 || row >= n) return false;
        if (col < 0 || col >= m) return false;
        return true;
    }

    public List<Integer> numOfIslands(int n, int m, int[][] A) {
        int[][] visited = new int[n][m];
        DisjointSet ds = new DisjointSet(n*m);
        for (int[] row : visited) Arrays.fill(row,0);
        int count = 0;
        List<Integer> ans = new ArrayList<>();
        int[] delRow = {-1, 0, 1, 0};
        int[] delCol = {0, -1, 0, 1};
        for (int[] it : A) {
            int row = it[0];
            int col = it[1];
            if (visited[row][col] == 1) {
                ans.add(count);
                continue;
            }
            visited[row][col] = 1;
            count++;
            for (int i=0;i<4;i++) {
                int newRow = row + delRow[i];
                int newCol = col + delCol[i];
                if (isValid(newRow,newCol,n,m) && visited[newRow][newCol] == 1) {
                    int oldIndex = row * m + col;
                    int newIndex = newRow * m +newCol;
                    if (ds.findParent(oldIndex) != ds.findParent(newIndex)) {
                        count--;
                        ds.unionBySize(oldIndex,newIndex);
                    }
                }
            }
            ans.add(count);
        }
        return ans;
    }

    public int largestIsland(int[][] grid) {
        int n = grid.length;
        DisjointSet ds = new DisjointSet(n*n);
        int[] delRow = {-1, 0, 1, 0};
        int[] delCol = {0, -1, 0, 1};
        for (int i=0;i<n;i++) {
            for (int j=0;j<n;j++) {
                if (grid[i][j] == 0) continue;
                for (int k=0;k<4;k++) {
                    int newRow = i + delRow[k];
                    int newCol = j + delCol[k];
                    if (isValid(newRow,newCol,n,n) && grid[newRow][newCol] == 1) {
                        int oldIndex = i * n + j;
                        int newIndex = newRow * n + newCol;
                        ds.unionBySize(oldIndex,newIndex);
                    }
                }
            }
        }
        int ans = 0;
        for (int i=0;i<n;i++) {
            for (int j=0;j<n;j++) {
                if (grid[i][j] == 1) continue;
                Set<Integer> components = new HashSet<>();
                int size = 0;
                for (int k=0;k<4;k++) {
                    int newRow = i + delRow[k];
                    int newCol = j + delCol[k];
                    if (isValid(newRow,newCol,n,n) && grid[newRow][newCol] == 1) {
                        components.add(ds.findParent(newRow*n + newCol));
                    }
                }
                for (int component : components) {
                    size = size + ds.size[component];
                }
                ans = Math.max(ans,size+1);
            }
        }
        for (int i=0;i<n*n;i++) {
            ans = Math.max(ans,ds.size[ds.findParent(i)]);
        }
        return ans;
    }
}

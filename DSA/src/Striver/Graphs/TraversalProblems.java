package Striver.Graphs;

import java.util.*;

public class TraversalProblems {
    public static void main(String[] args) {
        char[][] mat = {
                {'X', 'X', 'X', 'X'},
                {'X', 'O', 'O', 'X'},
                {'X', 'X', 'O', 'X'},
                {'X', 'O', 'X', 'X'}
        };
        fill(mat);
    }

    private void bfs(int node, List<Integer>[] adjList, boolean[] visited) {
        visited[node] = true;
        Queue<Integer> q = new LinkedList<>();
        q.add(node);
        while (!q.isEmpty()) {
            int polled = q.poll();
            for (int i : adjList[polled]) {
                if (!visited[i]) {
                    visited[i] = true;
                    q.add(i);
                }
            }
        }
    }

    public int numProvinces(int[][] adj) {
        int V = adj.length;
        List<Integer>[] adjList = new ArrayList[V];
        for (int i = 0; i < V; i++) {
            adjList[i] = new ArrayList<>();
        }
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                if (adj[i][j] == 1 && i != j) {
                    adjList[i].add(j);
                    adjList[j].add(i);
                }
            }
        }
        boolean[] visited = new boolean[V];
        int count = 0;
        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                count++;
                bfs(i, adjList, visited);
            }
        }
        return count;
    }


    public int numIslands(char[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        boolean[][] visited = new boolean[n][m];
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == '1' && !visited[i][j]) {
                    count++;
                    bfsIsland(i, j, grid, visited);
                }
            }
        }
        return count;
    }

    private void bfsIsland(int i, int j, char[][] grid, boolean[][] visited) {
        visited[i][j] = true;
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{i, j});
        while (!queue.isEmpty()) {
            int[] polledData = queue.poll();
            int row = polledData[0];
            int column = polledData[1];
            for (int delRow = -1; delRow <= 1; delRow++) {
                for (int delCol = -1; delCol <= 1; delCol++) {
                    int newRow = row + delRow;
                    int newCol = column + delCol;
                    if (isValid(newRow, newCol, grid.length, grid[0].length) && grid[newRow][newCol] == '1' && !visited[newRow][newCol]) {
                        visited[newRow][newCol] = true;
                        queue.add(new int[]{newRow, newCol});
                    }
                }
            }
        }
    }

    private static boolean isValid(int newN, int newM, int n, int m) {
        if (newN < 0 || newN >= n) return false;
        if (newM < 0 || newM >= m) return false;
        return true;
    }


    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        int n = image.length;
        int m = image[0].length;
        int[][] ans = new int[n][m];
        int currentColor = image[sr][sc];
        for (int i = 0; i < n; i++) {
            ans[i] = Arrays.copyOf(image[i], image[i].length);
        }
        bfsFloodFill(sr, sc, image, newColor, ans, currentColor);
        return ans;
    }

    private void bfsFloodFill(int row, int col, int[][] image, int newColor, int[][] ans, int currentColor) {
        ans[row][col] = newColor;
        Queue<int[]> queue = new LinkedList<>();
        int[] delRow = new int[]{-1, 0, 1, 0};
        int[] delCol = new int[]{0, 1, 0, -1};
        queue.add(new int[]{row, col});
        while (!queue.isEmpty()) {
            int[] polledArray = queue.poll();
            for (int i=0;i<4;i++) {
                int newRow = polledArray[0] + delRow[i];
                int newCol = polledArray[1] + delCol[i];
                if (isValid(newRow, newCol, image.length, image[0].length)
                        && image[newRow][newCol] == currentColor
                        && ans[newRow][newCol] != newColor
                ) {
                    ans[newRow][newCol] = newColor;
                    queue.add(new int[]{newRow, newCol});
                }
            }
        }
    }

    public int numberOfEnclaves(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        boolean[][] visited = new boolean[n][m];
        for (int i=0;i<n;i++) {
            for (int j=0;j<m;j++) {
                if ((i==0 || i == n-1 || j == 0 || j == m-1) && grid[i][j] == 1 && !visited[i][j]) {
                    dfsEnclave(i,j,grid,visited);
                }
            }
        }
        int count = 0;
        for (int i=0;i<n;i++) {
            for (int j=0;j<m;j++) {
                if (grid[i][j] == 1 && !visited[i][j]) {
                    count++;
                }
            }
        }
        return count;
    }
    private void dfsEnclave(int row,int col, int[][] grid, boolean[][] visited) {
        int[] delRow = new int[]{-1, 0, 1, 0};
        int[] delCol = new int[]{0, 1, 0, -1};
        visited[row][col] = true;
        for (int i=0;i<4;i++) {
            int newRow = row + delRow[i];
            int newCol = col + delCol[i];
            if (isValid(newRow, newCol, grid.length, grid[0].length)
                    && grid[newRow][newCol] == 1 && !visited[newRow][newCol]
            ) {
                dfsEnclave(newRow,newCol,grid,visited);
            }
        }
    }

    public int orangesRotting(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int[] delRow = new int[]{-1, 0, 1, 0};
        int[] delCol = new int[]{0, 1, 0, -1};
        Queue<int[]> queue = new LinkedList<>();
        int total = 0;
        int count = 0;
        int time = 0;
        for (int i=0;i<n;i++) {
            for (int j=0;j<m;j++) {
                if (grid[i][j] != 0) total++;
                if (grid[i][j] == 2) queue.add(new int[] {i,j});
            }
        }
        while (!queue.isEmpty()) {
            int k = queue.size();
            count = count + k;
            while (k > 0) {
                int[] polledData = queue.poll();
                for (int i=0;i<4;i++) {
                    int newRow = polledData[0] + delRow[i];
                    int newCol = polledData[1] + delCol[i];
                    if (isValid(newRow,newCol,grid.length,grid[0].length) && grid[newRow][newCol] == 1) {
                        grid[newRow][newCol] = 2;
                        queue.add(new int[] {newRow,newCol});
                    }
                }
                k--;
            }
            if (!queue.isEmpty()) time++;
        }
        if (total == count) return time;
        return -1;
    }

    public int[][] nearest(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int[][] dist = new int[n][m];
        int[][] visibility = new int[n][m];
        int[] delRow = new int[]{-1, 0, 1, 0};
        int[] delCol = new int[]{0, 1, 0, -1};
        Queue<int[]> queue = new LinkedList<>();
        for (int i=0;i<n;i++) {
            for (int j=0;j<m;j++) {
                if (grid[i][j] == 1) {
                    queue.add(new int[] {i,j,0});
                    visibility[i][j] = 1;
                } else {
                    visibility[i][j] = 0;
                }
            }
        }
        while (!queue.isEmpty()) {
            int[] polledData = queue.poll();
            int row = polledData[0];
            int col = polledData[1];
            int steps = polledData[2];
            dist[row][col] = steps;
            for (int i=0;i<4;i++) {
                int newRow = row + delRow[i];
                int newCol = col + delCol[i];
                if (isValid(newRow,newCol,grid.length,grid[0].length) && visibility[newRow][newCol] == 0) {
                    visibility[newRow][newCol] = 1;
                    queue.add(new int[] {newRow,newCol,steps+1});
                }
            }
        }
        return dist;
    }

    public static char[][] fill(char[][] mat) {
        int n = mat.length;
        int m = mat[0].length;
        Queue<int[]> queue = new LinkedList<>();
        int[][] vis = new int[n][m];
        for (int i=0;i<n;i++) {
            for (int j=0;j<m;j++) {
                if ((i==0 || i == n-1 || j == 0 || j == m-1) && mat[i][j] == 'O') {
                    vis[i][j] = 1;
                    queue.add(new int[] {i,j});
                } else {
                    vis[i][j] = 0;
                }
            }
        }
        bfsFill(queue,vis,mat);
        for (int i=0;i<n;i++) {
            for (int j=0;j<m;j++) {
                if (mat[i][j] == 'O' && vis[i][j] == 0) {
                    mat[i][j] = 'X';
                }
            }
        }
        return mat;
    }
    private static void bfsFill(Queue<int[]> queue,int[][] vis,char[][] mat) {
        int[] delRow = new int[]{-1, 0, 1, 0};
        int[] delCol = new int[]{0, 1, 0, -1};
        while (!queue.isEmpty()) {
            int[] polledData = queue.poll();
            int row = polledData[0];
            int col = polledData[1];
            for (int i=0;i<4;i++) {
                int newRow = row + delRow[i];
                int newCol = col + delCol[i];
                if (isValid(newRow,newCol,mat.length,mat[0].length) && mat[newRow][newCol] == 'O' && vis[newRow][newCol] == 0) {
                    vis[newRow][newCol] = 1;
                    queue.add(new int[] {newRow,newCol});
                }
            }
        }
    }

    public int countDistinctIslands(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        boolean[][] vis = new boolean[n][m];
        Set<List<String>> standardPath = new HashSet<>();
        for (int i=0;i<n;i++) {
            for (int j=0;j<m;j++) {
                if (grid[i][j] == 1 && !vis[i][j]) {
                    List<String> path = new ArrayList<>();
                    vis[i][j] = true;
                    dfsDistinctIslands(i,j,i,j,path,vis,grid);
                    standardPath.add(path);
                }
            }
        }
        return standardPath.size();
    }

    private void dfsDistinctIslands(int row, int col, int baseRow, int baseCol, List<String> path,
                                    boolean[][] vis, int[][] grid) {
        int[] delRow = new int[]{-1, 0, 1, 0};
        int[] delCol = new int[]{0, 1, 0, -1};
        path.add((row-baseRow) + "," + (col-baseCol));
        for (int i=0;i<4;i++) {
            int newRow = row + delRow[i];
            int newCol = col + delCol[i];
            if (isValid(newRow,newCol,grid.length,grid[0].length) && grid[newRow][newCol] == 1 && !vis[newRow][newCol]) {
                vis[newRow][newCol] = true;
                dfsDistinctIslands(newRow,newCol,baseRow,baseCol,path,vis,grid);
            }
        }
    }

}

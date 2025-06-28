package Striver.Graphs;

import java.util.*;

public class ShortestPath {
    public static void main(String[] args) {
        int n = 5, m = 6;
        int[][] edges = {
                {1, 2, 2}, {2, 5, 5}, {2, 3, 4},
                {1, 4, 1}, {4, 3, 3}, {3, 5, 1}
        };
        shortestPath(n,m,edges);
    }
    public  int[] dijkstra(int V, ArrayList<ArrayList<ArrayList<Integer>>> adj, int S) {
        // We will use priority queue here and we will store queue here this time
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        int[] distance = new int[V];
        Arrays.fill(distance,(int)1e9);
        distance[S] = 0;
        pq.add(new int[] {0,S});
        while (!pq.isEmpty()) {
            int dis = pq.peek()[0];
            int node = pq.peek()[1];
            pq.poll();
            for(ArrayList<Integer> data : adj.get(node)) {
                if (dis + data.get(1) < distance[data.get(0)]) {
                    distance[data.get(0)] = dis + data.get(1);
                    pq.add(new int[] {dis + data.get(1),data.get(0)});
                }
            }
        }
        for (int i=0;i<V;i++) {
            if(distance[i] == (int)1e9) {
                distance[i] = -1;
            }
        }
        return distance;
    }

    public static List<Integer> shortestPath(int n, int m, int[][] edges) {
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        List<List<int[]>> adj = new ArrayList<>();
        for (int i=0;i<n+1;i++) {
            adj.add(new ArrayList<>());
        }

        for (int[] edge: edges) {
            adj.get(edge[0]).add(new int[] {edge[1],edge[2]});
            adj.get(edge[1]).add(new int[] {edge[0],edge[2]});
        }
        int[] distance = new int[n+1];
        Arrays.fill(distance,(int)1e9);
        distance[1] = 0;
        pq.add(new int[] {0,1});
        int[] parent = new int[n+1];
        for(int i=0;i<n+1;i++) {
            parent[i] = i;
        }
        while (!pq.isEmpty()) {
            int dis = pq.peek()[0];
            int node = pq.peek()[1];
            pq.poll();
            for(int[] data : adj.get(node)) {
                if (dis + data[1] < distance[data[0]]) {
                    distance[data[0]] = dis + data[1];
                    pq.add(new int[] {dis + data[1],data[0]});
                    parent[data[0]] = node;
                }
            }
        }
        if (distance[n] == 1e9) return List.of(-1);
        int node = n;
        List<Integer> path = new ArrayList<>();
        while (parent[node] != node) {
            path.add(node);
            node = parent[node];
        }
        path.add(1);
        Collections.reverse(path);
        path.add(0,distance[n]);
        return path;
    }

    private boolean isValid(int row, int col,
                            int n, int m) {

        if (row < 0 || row >= n) return false;
        if (col < 0 || col >= m) return false;
        return true;
    }

    int shortestPath(int[][] grid, int[] source, int[] destination) {
        int[] delRow = {-1, 0, 1, 0};
        int[] delCol = {0, -1, 0, 1};
        if (source[0] == destination[0] && source[1] == destination[1]) return 0;
        Queue<int[]> queue = new LinkedList<>();
        int[][] distance = new int[grid.length][grid[0].length];
        for (int[] rows : distance) {
            Arrays.fill(rows,Integer.MAX_VALUE);
        }
        distance[source[0]][source[1]] = 0;
        queue.add(new int[] {0,source[0],source[1]});
        while (!queue.isEmpty()) {
            int[] polledData = queue.poll();
            int dist = polledData[0];
            int startRow = polledData[1];
            int startCol = polledData[2];
            for (int i=0;i<4;i++) {
                int newRow = startRow + delRow[i];
                int newCol = startCol + delCol[i];
                if (isValid(newRow,newCol,grid.length,grid[0].length)
                    && grid[newRow][newCol] == 1 && dist + 1 < distance[newRow][newCol]
                ) {
                    distance[newRow][newCol] = dist + 1;
                    if (newRow == destination[0] && newCol == destination[1]) return dist+1;
                    queue.add(new int[] {dist+1,newRow,newCol});
                }
            }
        }
        return -1;
    }

    // Cheapest flight question
    public int CheapestFlight(int n, int[][] flights, int src, int dst, int K) {
        // Create a Adjacency list
        List<List<int[]>> adj = new ArrayList<>();
        for (int i=0;i<n;i++) {
            adj.add(new ArrayList<>());
        }
        for (int[] flight : flights) {
            adj.get(flight[0]).add(new int[] {flight[1],flight[2]});
        }
        int[] dist = new int[n];
        Arrays.fill(dist,Integer.MAX_VALUE);
        Queue<int[]> q = new LinkedList<>();
        // Adding stop to source then source node then price
        q.add(new int[] {0,src,0});
        while (!q.isEmpty()) {
            int[] polledData = q.poll();
            int stops = polledData[0];
            int node = polledData[1];
            int wt = polledData[2];
            if (stops > K) continue;
            for (int[] adjNode : adj.get(node)) {
                if (wt + adjNode[1] < dist[adjNode[0]] && stops <= K) {
                    dist[adjNode[0]] = wt + adjNode[1];
                    q.add(new int[] {stops+1,adjNode[0],dist[adjNode[0]]});
                }
            }
        }
        if (dist[dst] == Integer.MAX_VALUE) return -1;
        return dist[dst];
    }

    public int MinimumEffort(List<List<Integer>> heights) {
        int[] delRow = {-1, 0, 1, 0};
        int[] delCol = {0, -1, 0, 1};
        int n = heights.size();
        int m = heights.get(0).size();
        int[][] diffArray = new int[n][m];
        for (int[] arr : diffArray) {
            Arrays.fill(arr,Integer.MAX_VALUE);
        }
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        // Adding diff , row start row and col start col
        pq.add(new int[] {0,0,0});
        while (!pq.isEmpty()) {
            int[] polledData = pq.poll();
            int diff = polledData[0];
            int row = polledData[1];
            int col = polledData[2];
            if (row == n-1 && col == m-1) return diff;
            for (int i=0;i<4;i++) {
                int newRow = row + delRow[i];
                int newCol = col + delCol[i];
                if (isValid(newRow,newCol,n,m)) {
                    int currentDiff = Math.abs(heights.get(newRow).get(newCol) - heights.get(row).get(col));
                    if (Math.max(currentDiff,diff) < diffArray[newRow][newCol]) {
                        diffArray[newRow][newCol] = Math.max(currentDiff,diff);
                        pq.add(new int[] {Math.max(currentDiff,diff),newRow,newCol});
                    }
                }
            }
        }
        return -1;
    }

    public int minimumMultiplications(int[] arr, int start, int end) {
        if (start == end) return 0;
        int[] minSteps = new int[100000];
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        pq.add(new int[] {0,start});
        Arrays.fill(minSteps,Integer.MAX_VALUE);
        while (!pq.isEmpty()) {
            int[] polledData = pq.poll();
            int number = polledData[1];
            int steps = polledData[0];
            if (number == end) return steps;
            for (int i : arr) {
                int newNumber = ( number * i ) % 100000;
                if (steps + 1 < minSteps[newNumber]) {
                    minSteps[newNumber] = steps + 1;
                    pq.add(new int[] {steps+1,newNumber});
                }
            }
        }
        return -1;
    }

    public int countPaths(int n, List<List<Integer>> roads) {
        List<List<int[]>> adj = new ArrayList<>();
        for (int i=0;i<n;i++) {
            adj.add(new ArrayList<>());
        }
        for (List<Integer> list : roads) {
            adj.get(list.get(0)).add(new int[] {list.get(1),list.get(2)});
            adj.get(list.get(1)).add(new int[] {list.get(0),list.get(2)});
        }
        long[] minTime = new long[n];
        Arrays.fill(minTime,Long.MAX_VALUE);
        int[] ways = new int[n];
        minTime[0] = 0;
        ways[0] = 1;
        PriorityQueue<long[]> pq = new PriorityQueue<>(Comparator.comparingLong(a -> a[0]));
        pq.add(new long[] {0,0});
        while (!pq.isEmpty()) {
            long[] polledData = pq.poll();
            long time = polledData[0];
            int node = (int)polledData[1];
            for (int[] adjNode : adj.get(node)) {
                if (time + adjNode[1] < minTime[adjNode[0]]) {
                    minTime[adjNode[0]] = time + adjNode[1];
                    ways[adjNode[0]] = ways[node];
                    pq.add(new long[] {minTime[adjNode[0]],adjNode[0]});
                } else if (time + adjNode[1] == minTime[adjNode[0]]) {
                    ways[adjNode[0]] = (ways[adjNode[0]] + ways[node]) % 1000000007;
                }
            }
        }
        return ways[n-1] % 1000000007;
    }

    static int[] bellman_ford(int V,
                              ArrayList<ArrayList<Integer>> edges, int S) {
        int[] dist = new int[V];
        Arrays.fill(dist,(int)1e9);
        dist[0] = 0;
        for (int i=0;i<V-1;i++) {
            for (ArrayList<Integer> it : edges) {
                int u = it.get(0);
                int v = it.get(1);
                int wt = it.get(2);
                if (dist[u] != 1e9 && dist[u] + wt < dist[v]) {
                    dist[v] = dist[u] + wt;
                }
            }
        }
        for (ArrayList<Integer> it : edges) {
            int u = it.get(0);
            int v = it.get(1);
            int wt = it.get(2);
            if (dist[u] != 1e9 && dist[u] + wt < dist[v]) {
                dist[v] = dist[u] + wt;
            }
        }
        return dist;
    }

    public void shortest_distance(int[][] matrix) {
        int n = matrix.length;
        for (int k=0;k<n;k++) {
            for (int i=0;i<n;i++) {
                for (int j=0;j<n;j++) {
                    if (matrix[i][k] == -1 || matrix[k][j] == -1) continue;
                    if (matrix[i][j] == -1) {
                        matrix[i][j] = matrix[i][k] + matrix[k][j];
                    } else  {
                        matrix[i][j] = Math.min(matrix[i][j],matrix[i][k] + matrix[k][j]);
                    }
                }
            }
        }
    }

    public int findCity(int n, int m, int edges[][],
                        int distanceThreshold) {
        int[][] adj = new int[n][n];
        for (int[] row : adj) Arrays.fill(row,(int)1e9);
        for (int[] edge : edges) {
            adj[edge[0]][edge[1]] = edge[2];
            adj[edge[1]][edge[0]] = edge[2];
        }
        for (int k=0;k<n;k++) {
            for (int i=0;i<n;i++) {
                for (int j=0;j<n;j++) {
                    adj[i][j] = Math.min(adj[i][j],adj[i][k] + adj[k][j]);
                }
            }
        }
        int minCount = (int)1e9;
        int ans = -1;
        for (int i=0;i<n;i++) {
            int count = 0;
            for (int j=0;j<n;j++) {
                if (i!=j && adj[i][j] <= distanceThreshold) count++;
            }
            if (count < minCount) {
                minCount = count;
                ans = i;
            } else if (count == minCount) ans = i;
        }
        return ans;
    }
}

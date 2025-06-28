package Striver.Graphs;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class MinimumSpanningTree {
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

    public int spanningTree(int V, List<List<List<Integer>>> adj) {
        boolean[] visited = new boolean[V];
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        pq.add(new int[] {0,0});
        int sum = 0;
        while (!pq.isEmpty()) {
            int[] polledData = pq.poll();
            int node = polledData[1];
            int wt = polledData[0];
            if (visited[node]) continue;
            visited[node] = true;
            sum = sum + wt;
            for (List<Integer> it : adj.get(node)) {
                int connectedNode = it.get(0);
                int wtEdge = it.get(1);
                if (!visited[connectedNode]) pq.add(new int[] {wtEdge,connectedNode});
            }
        }
        return sum;
    }
}

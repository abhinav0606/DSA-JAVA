package Striver.Graphs;

import java.util.*;

public class Cycles {
    public static void main(String[] args) {

    }
    public boolean isCycle(int V, List<Integer>[] adj) {
        boolean[] visited = new boolean[V];
        boolean ans = false;
        for (int i=0;i<V;i++) {
            if (!visited[i]) {
                visited[i] = true;
                ans = bfsCycle(i,adj,visited);
                if (ans) break;
            }
        }
        return ans;
    }
    private boolean bfsCycle(int node,List<Integer>[] adj, boolean[] visited) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {node,-1});
        while (!queue.isEmpty()) {
            int[] polledData = queue.poll();
            int data = polledData[0];
            int parent = polledData[1];
            for (int i : adj[data]) {
                if (!visited[i]) {
                    visited[i] = true;
                    queue.add(new int[] {i,data});
                } else if (i != parent) return true;
            }
        }
        return false;
    }

    public boolean isBipartite(int V, List<Integer>[] adj) {
        int[] colors = new int[V];
        Arrays.fill(colors,-1);
        for (int i=0;i<V;i++) {
            if (colors[i] == -1) {
                if (!dfsBipertite(i,adj,colors,0)) return false;
            }
        }
        return true;
    }

    private boolean dfsBipertite(int node, List<Integer>[] adj, int[] colors,int color) {
        colors[node] = color;
        for (int i : adj[node]) {
            if (colors[i] != -1) {
                colors[i] = 1 - color;
                if (!dfsBipertite(i,adj,colors,colors[i])) return false;
            } else if (colors[i] == color) return false;
        }
        return true;
    }

    public int[] topoSort(int V, List<Integer>[] adj) {
        int[] inDegree = new int[V];
        for(int i=0;i<V;i++) {
            for (int node : adj[i]) {
                inDegree[node]++;
            }
        }
        Queue<Integer> q = new LinkedList<>();
        for (int i=0;i<V;i++) {
            if (inDegree[i] == 0) q.add(i);
        }
        int[] ans = new int[V];
        int index = 0;
        while (!q.isEmpty()) {
            int polled = q.poll();
            ans[index] = polled;
            index++;
            for (int i : adj[polled]) {
                inDegree[i]--;
                if (inDegree[i] == 0) q.add(i);
            }
        }
        return ans;
    }

    public List<Integer> topoSortCycle(int V, List<Integer>[] adj) {
        int[] inDegree = new int[V];
        for(int i=0;i<V;i++) {
            for (int node : adj[i]) {
                inDegree[node]++;
            }
        }
        Queue<Integer> q = new LinkedList<>();
        for (int i=0;i<V;i++) {
            if (inDegree[i] == 0) q.add(i);
        }
        List<Integer> ans = new ArrayList<>();
        int index = 0;
        while (!q.isEmpty()) {
            int polled = q.poll();
            ans.add(polled);
            index++;
            for (int i : adj[polled]) {
                inDegree[i]--;
                if (inDegree[i] == 0) q.add(i);
            }
        }
        return ans;
    }

    public boolean isCyclic(int N, List<Integer>[] adj) {
        List<Integer> topoData = topoSortCycle(N,adj);
        if (topoData.size() < N) return true;
        return false;
    }

}

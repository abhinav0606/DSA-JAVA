package Striver.Graphs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Traversal {
    public static void main(String[] args) {

    }
    public int findNumberOfComponent(int E, int V, List<List<Integer>> edges) {
        List<Integer>[] adj = new ArrayList[V+1];
        for (int i=0;i<V+1;i++) {
            adj[i] = new ArrayList<>();
        }
        for (int i=0;i<E;i++) {
            adj[edges.get(i).get(0)].add(edges.get(i).get(1));
            adj[edges.get(i).get(1)].add(edges.get(i).get(0));
        }
        boolean[] visited = new boolean[V+1];

        int count = 0;
        for (int i=1;i<V+1;i++) {
            if (!visited[i]) {
                count++;
                bfs(i,adj,visited);
            }
        }
        return count;
    }

    private void bfs(int node,List<Integer>[] adj,boolean[] vis) {
        vis[node] = true;
        Queue<Integer> q = new LinkedList<>();
        q.add(node);
        while (!q.isEmpty()) {
            int polled = q.poll();
            for (int adjNode:adj[polled]) {
                if (!vis[adjNode]) {
                    vis[adjNode] = true;
                    q.add(adjNode);
                }
            }
        }
    }



    public List<Integer> dfsOfGraph(int V, List<Integer> adj[]) {
        boolean[] visited = new boolean[V];
        List<Integer> answer = new ArrayList<>();
        for (int i=0;i<V;i++) {
            if (!visited[i]) {
                dfs(i,adj,visited,answer);
            }
        }
        return answer;
    }

    public List<Integer> bfsOfGraph(int V, List<Integer> adj[]) {
        boolean[] visited = new boolean[V];
        List<Integer> answer = new ArrayList<>();
        for (int i=0;i<V;i++) {
            if (!visited[i]) {
                visited[i] = true;
                bsf(i,adj,visited,answer);
            }
        }
        return answer;
    }

    private void bsf(int node,List<Integer> adj[],boolean[] vis,List<Integer> answer) {
        Queue<Integer> q = new LinkedList<>();
        q.add(node);
        while (!q.isEmpty()) {
            int polled = q.poll();
            answer.add(polled);
            for (int i : adj[polled]) {
                if (!vis[i]) {
                    vis[i] = true;
                    q.add(i);
                }
            }
        }
    }
    private void dfs(int node,List<Integer> adj[],boolean[] vis,List<Integer> answer) {
        answer.add(node);
        vis[node] = true;
        for (int i : adj[node]) {
                if (!vis[i]) {
                    dfs(i,adj,vis,answer);
                }
            }
    }

}

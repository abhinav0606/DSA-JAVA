package Striver.Graphs;

import java.util.*;

public class HardProblems {
    public static void main(String[] args) {

    }
    private boolean dfsCycleDetect(int node,boolean[] visisted, boolean[] pathVisisted, boolean[] check, int[][] adj) {
        visisted[node] = true;
        pathVisisted[node] = true;
        check[node] = false;
        for (int i : adj[node]) {
            if (!visisted[node]) {
                if (dfsCycleDetect(node,visisted,pathVisisted,check,adj)) return true;
            } else if (pathVisisted[i]) return true;
        }
        check[node] = true;
        pathVisisted[node] = false;
        return false;
    }
    public int[] eventualSafeNodes(int V, int[][] adj) {
        boolean[] visisted = new boolean[V];
        boolean[] pathVisisted = new boolean[V];
        boolean[] check = new boolean[V];
        for (int i=0;i<V;i++) {
            if (!visisted[i]) {
                dfsCycleDetect(i,visisted,pathVisisted,check,adj);
            }
        }
        List<Integer> temp = new ArrayList<>();
        for (int i = 0;i<V;i++) {
            if (check[i]) temp.add(i);
        }
        int[] ans = new int[temp.size()];
        for (int i=0;i<temp.size();i++) {
            ans[i] = temp.get(i);
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

    public boolean canFinish(int N, int[][] arr) {
        List<Integer>[] adj = new ArrayList[N];
        for (int i=0;i<N;i++) {
            adj[i] = new ArrayList<>();
        }
        for (int[] a : arr) {
            int u = a[0];
            int v = a[1];
            adj[v].add(u);
        }
        List<Integer> topoData = topoSortCycle(N,adj);
        if (topoData.size() < N) return false;
        return true;
    }

    public int[] findOrder(int N, int[][] arr) {
        List<Integer>[] adj = new ArrayList[N];
        for (int i=0;i<N;i++) {
            adj[i] = new ArrayList<>();
        }
        for (int[] a : arr) {
            int u = a[0];
            int v = a[1];
            adj[v].add(u);
        }
        List<Integer> topoData = topoSortCycle(N,adj);
        int[] ans = new int[N];
        if (topoData.size() < N) return new int[0];
        int index = 0;
        for (int i : topoData) {
            ans[index] = i;
            index++;
        }
        return Arrays.copyOfRange(ans,0,index);
    }

    // Alien Dictionary problem
    public String findOrder(String [] dict, int N, int K) {
        List<Integer>[] adj = new ArrayList[K];
        for (int i=0;i<K;i++) {
            adj[i] = new ArrayList<>();
        }
        for (int i=0;i<N-1;i++) {
            String s1 = dict[i];
            String s2 = dict[i+1];
            int len = Math.min(s1.length(),s2.length());
            for (int j=0;j<len;j++) {
                if (s1.charAt(j) != s2.charAt(j)) {
                    adj[s1.charAt(j) - 'a'].add(s2.charAt(j) - 'a');
                    break;
                }
            }
        }
        List<Integer> topoResult = topoSortCycle(K,adj);
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < K; i++) {
            ans.append((char) ('a' + topoResult.get(i)));
            ans.append(' ');
        }
        return ans.toString();
    }

    static class Pair {
        int first,second;
        Pair(int first,int second) {
            this.first = first;
            this.second = second;
        }
    }

    public int[] shortestPath(int N, int M, int[][] edges) {
        boolean[] visited = new boolean[N];
        List<List<Pair>> adj = new ArrayList<>();
        for (int i=0;i<N;i++) {
            adj.add(new ArrayList<>());
        }
        for (int i=0;i<M;i++) {
            int u = edges[i][0];
            int v = edges[i][1];
            int wt = edges[i][2];
            adj.get(u).add(new Pair(v,wt));
        }
        Stack<Integer> st = new Stack<>();
        for (int i=0;i<N;i++) {
            if (!visited[i]) {
                topoSortDFS(i,st,visited,adj);
            }
        }
        int[] distance = new int[N];
        Arrays.fill(distance,Integer.MAX_VALUE);
        distance[0] = 0;
        while (!st.isEmpty()) {
            int node = st.pop();
            for (Pair p : adj.get(node)) {
                int v = p.first;
                int wt = p.second;
                if (distance[node] + wt < distance[v]) {
                    distance[v] = wt + distance[node];
                }
            }
        }
        for (int i=0;i<N;i++) {
            if (distance[i] == Integer.MAX_VALUE) distance[i] = -1;
        }
        return distance;
    }
    private void topoSortDFS(int node,Stack<Integer> st,boolean[] vis, List<List<Pair>> adj) {
        vis[node] = true;
        for (Pair i : adj.get(node)) {
            if (!vis[i.first]) {
                topoSortDFS(i.first,st,vis,adj);
            }
        }
        st.push(node);
    }

    public int[] shortestPath(int[][] edges,int N, int M) {
        boolean[] visited = new boolean[N];
        List<Integer>[] adj = new ArrayList[N];
        for (int i=0;i<N;i++) {
            adj[i] = new ArrayList<>();
        }
        for (int i=0;i<M;i++) {
            int u = edges[i][0];
            int v = edges[i][1];
            adj[u].add(v);
            adj[v].add(u);
        }
        int[] dist = new int[N];
        Arrays.fill(dist,Integer.MAX_VALUE);
        bfsTopo(visited,adj,dist);
        for (int i=0;i<N;i++) {
            if (dist[i] == Integer.MAX_VALUE) dist[i] = -1;
        }
        return dist;
    }
    private void bfsTopo(boolean[] vis,List<Integer>[] adj,int[] dist) {
        dist[0] = 0;
        Queue<Integer> q = new LinkedList<>();
        q.add(0);
        while (!q.isEmpty()) {
            int polled = q.poll();
            for (int i : adj[polled]) {
                if (dist[polled] + 1 < dist[i]) {
                    dist[i] = dist[polled] + 1;
                    q.add(i);
                }
            }
        }
    }

    static class PairWord {
        String word;
        int steps;
        PairWord(String word, int steps) {
            this.word = word;
            this.steps = steps;
        }
    }
    // Extremely hard problem
    public int wordLadderLength(String startWord, String targetWord, List<String> wordList) {
        Queue<PairWord> q = new LinkedList<>();
        q.add(new PairWord(startWord,1));
        HashSet<String> hashSet = new HashSet<>(wordList);
        hashSet.remove(startWord);
        while (!q.isEmpty()) {
            String word = q.peek().word;
            int steps = q.peek().steps;
            q.poll();
            if (word.equals(targetWord)) return steps;
            for (int i=0;i<word.length();i++) {
                char[] wordArray = word.toCharArray();
                for (char c = 'a'; c <= 'z';c++) {
                    wordArray[i] = c;
                    String newWord = Arrays.toString(wordArray);
                    if (wordList.contains(newWord)) {
                        hashSet.remove(newWord);
                        q.add(new PairWord(newWord,steps+1));
                    }
                }
            }
        }
        return 0;
    }
}

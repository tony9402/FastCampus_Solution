import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {
    static int N, M, K;
    static int lastNode;
    static ArrayList<Long> V = new ArrayList<>();
    static int []indegree;
    static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    static long ans = 1L << 62;
    public static void main(String[] args) {
        FastReader rd = new FastReader();

        N = rd.nextInt();
        M = rd.nextInt();
        K = rd.nextInt();
        indegree = new int[N];
        for(int i = 0; i < N; ++i) {
            V.add(rd.nextLong());
            graph.add(new ArrayList<>());
        }
        for(int i = 0; i < M; ++i) {
            int u = rd.nextInt() - 1;
            int v = rd.nextInt() - 1;
            graph.get(u).add(v);
            ++indegree[v];
        }
        getLastNode();
        select(1, 0);
        System.out.println(ans);
    }

    static public void getLastNode() {
        int []ind = indegree.clone();
        Queue<Integer> Q = new LinkedList<>();
        Q.add(0);
    
        while(!Q.isEmpty()) {
            int cur = Q.poll();
            lastNode = cur;
            for(Integer nxt: graph.get(cur)) {
                -- ind[nxt];
                if(--ind[nxt] == 0) Q.add(nxt);
            }
        }
    } 

    static public long go() {
        int []ind = indegree.clone();
        long []DP = new long[N];

        Queue<Integer> Q = new LinkedList<>();
        Q.add(0);
        
        DP[0] = V.get(0);
        while(!Q.isEmpty()) {
            int cur = Q.poll();
            lastNode = cur;
            for(Integer nxt: graph.get(cur)) {
                if(--ind[nxt] == 0) Q.add(nxt);
                DP[nxt] = Math.max(DP[nxt], DP[cur] + V.get(nxt));
            }
        }
        long mx = 0;
        for(Long x : DP) mx = Math.max(mx, x);
        return mx;
    }

    static public void select(int idx, int cnt) {
        if(cnt == K) {
            ans = Math.min(ans, go());
            return;
        }
    
        for(int i = idx; i < N; i++) {
            if(i == lastNode) continue;
            long tmp = V.get(i);
            V.set(i, 0L);
            select(i + 1, cnt + 1);
            V.set(i, tmp);
        }
    }

    static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() {
            while(st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() { return Integer.parseInt(next()); }
        long nextLong() { return Long.parseLong(next()); }
        double nextDouble() { return Double.parseDouble(next()); }
        String nextLine() {
            String str = "";
            try {
                str = br.readLine();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }
    }
}
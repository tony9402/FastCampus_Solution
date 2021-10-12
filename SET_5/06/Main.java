import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {
    final static int INF = 0x3f3f3f3f;
    static ArrayList<ArrayList<Pair>> graph;
    static int []distS;
    static int []distE;
    static int []used;
    public static void main(String[] args) {
        FastReader rd = new FastReader();

        int N = rd.nextInt();
        int M = rd.nextInt();

        used = new int[N];
        distS = new int[N];
        distE = new int[N];
        graph = new ArrayList<>();
        for(int i = 0; i < N; i++) {
            graph.add(new ArrayList<Pair>());
            distS[i] = distE[i] = INF;
        }

        for(int i = 0; i < M; i++) {
            int a = rd.nextInt() - 1; // 0-based
            int b = rd.nextInt() - 1; // 0-based
            int c = rd.nextInt();
            graph.get(a).add(new Pair(b, c));
            graph.get(b).add(new Pair(a, c));
        }

        int S = rd.nextInt() - 1; // 0-based
        int E = rd.nextInt() - 1; // 0-based

        Dijkstra(distS, S);
        Dijkstra(distE, E);
        long ans = distS[E];

        eraseEdge(S, E);
        for(int i = 0; i < N; i++) distE[i] = INF;

        Dijkstra(distE, E);
        ans += distE[S];
        
        System.out.println(ans);
    }

    static public void Dijkstra(int []dist, int S) {
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        dist[S] = 0;
        pq.add(new Pair(0, S));
        while(!pq.isEmpty()) {
            Pair cur = pq.poll();
            if(dist[cur.second] != cur.first) continue;
            for(Pair next : graph.get(cur.second)) {
                int nxt = (int)next.first;
                if(used[nxt] == 1) continue;
                if(dist[nxt] > dist[cur.second] + next.second) {
                    dist[nxt] = dist[cur.second] + next.second;
                    pq.add(new Pair(dist[nxt], nxt));
                }
            }
        }
    }

    static void eraseEdge(int S, int E) {
        int pre = S;
        while(S != E) {
            int min = Integer.MAX_VALUE;
            for(Pair next : graph.get(S)) {
                int nxt = (int)next.first;
                if(nxt == pre) continue;
                if(distS[S] + next.second + distE[nxt] == distS[E]) {
                    min = Math.min(min, nxt);
                }
            }
            pre = S;
            S = min;
            if(S != E) used[S] = 1;
        }
    }

    static class Pair implements Comparable<Pair> {
        int first;
        int second;

        public Pair(int first, int second) {
            this.first = first;
            this.second = second;
        }
        
        @Override
        public int compareTo(Pair o) {
            if(this.first == o.first) {
                return Integer.compare(this.second, o.second);
            }
            return Integer.compare(this.first, o.first);
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

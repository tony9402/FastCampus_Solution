import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {
    final static int INF = 1 << 30;
    static ArrayList<ArrayList<Pair>> G;
    public static void main(String[] args) {
        FastReader rd = new FastReader();

        int N = rd.nextInt();
        int A = rd.nextInt(), B = rd.nextInt(), C = rd.nextInt();
        int M = rd.nextInt();

        G = new ArrayList<>();
        for(int i = 0; i <= N; ++i) G.add(new ArrayList<Pair>());

        for(int i = 0; i < M; ++i) {
            int u = rd.nextInt(), v = rd.nextInt(), w = rd.nextInt();
            G.get(u).add(new Pair(v, w));
            G.get(v).add(new Pair(u, w));
        }

        PriorityQueue<Pair> pq = new PriorityQueue<>();
        int []dist = new int[N + 1];
        for(int i = 1; i <= N; ++i) dist[i] = INF;

        dist[A] = dist[B] = dist[C] = 0;
        pq.add(new Pair(0, A)); pq.add(new Pair(0, B)); pq.add(new Pair(0, C));

        while(!pq.isEmpty()) {
            Pair top = pq.poll();
            int d = top.first;
            int cur = top.second;
            if(dist[cur] != d) continue;
            for(Pair nxt : G.get(cur)) {
                if(dist[nxt.first] > d + nxt.second) {
                    dist[nxt.first] = d + nxt.second;
                    pq.add(new Pair(dist[nxt.first], nxt.first));
                }
            }
        }

        int mx = 0, ans = 0;
        for(int i = 1; i <= N; ++i) {
            if(mx < dist[i]) {
                mx = dist[i];
                ans = i;
            }
        }
        System.out.println(ans);
    }

    static class Pair implements Comparable<Pair> {
        int first, second;
        public Pair() { first = second = 0; }
        public Pair(int a, int b) {
            first = a;
            second = b;
        }
        @Override
        public int compareTo(Pair o) {
            return first - o.first;
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
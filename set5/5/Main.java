import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {
    static ArrayList<ArrayList<Integer>> G;
    static int []dist;
    static int []pre;
    public static void main(String[] args) {
        FastReader rd = new FastReader();

        int N = rd.nextInt(), M = rd.nextInt();
        G = new ArrayList<>();
        for(int i = 0; i <= N; ++i) G.add(new ArrayList<>());
        for(int i = 0; i <  M; ++i) {
            int u = rd.nextInt(), v = rd.nextInt();
            G.get(u).add(v); G.get(v).add(u);
        }
        for(int i = 1; i <= N; ++i) Collections.sort(G.get(i));
        int S = rd.nextInt(), E = rd.nextInt();

        dist = new int[N + 1];
        pre = new int[N + 1];
        for(int i = 1; i <= N; ++i) pre[i] = dist[i] = -1;
        int dist1 = BFS(S, E);
        for(int i = 1; i <= N; ++i) dist[i] = -1;
        erase(S, E);
        int dist2 = BFS(E, S);
        if(dist1 == -1 || dist2 == -1) System.out.println(-1);
        else System.out.println(dist1 + dist2);
    }

    static public int BFS(int start, int end) {
        Queue<Integer> Q = new LinkedList<>();
        Q.add(start);
        dist[start] = 0;
        while(!Q.isEmpty()) {
            int cur = Q.poll();
            for(int nxt : G.get(cur)) {
                if(dist[nxt] != -1) continue;
                dist[nxt] = dist[cur] + 1;
                Q.add(nxt);
                pre[nxt] = cur;
            }
        }
        return dist[end];
    }

    static public void erase(int start, int end) {
        if(start == pre[end]) return;
        dist[pre[end]] = 0;
        erase(start, pre[end]);
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
import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {
    static public void main(String[] args) {
        FastReader rd = new FastReader();

        int N = rd.nextInt(), M = rd.nextInt();

        int [][]arr = new int[N + 1][N + 1];
        int [][]dist = new int[N + 1][N + 1];
        final int INF = Integer.MAX_VALUE / 2;

        for(int i = 1; i <= N; ++i) {
            for(int j = 1; j <= N; ++j) {
                if(i != j) dist[i][j] = INF;
            }
        }
        
        for(int i = 0; i < M; ++i) {
            int u = rd.nextInt(), v = rd.nextInt(), w = rd.nextInt();
            dist[u][v] = w;
        }

        for(int k = 1; k <= N; ++k) {
            for(int i = 1; i <= N; ++i) {
                for(int j = 1; j <= N; ++j) {
                    if(dist[i][j] > dist[i][k] + dist[k][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }

        int K = rd.nextInt();
        ArrayList<Integer> friends = new ArrayList<>();
        for(int i = 0; i < K; ++i) {
            int x = rd.nextInt();
            friends.add(x);
        }
        
        int mx = INF;
        ArrayList<Integer> answer = new ArrayList<>();
        for(int i = 1; i <= N; ++i) {
            int cur = 0;
            for(int j = 0; j < K; ++j) cur = Math.max(cur, dist[friends.get(j)][i] + dist[i][friends.get(j)]);
            if(cur >= INF) cur = INF;
            if(mx > cur) {
                answer.clear();
                answer.add(i);
                mx = cur;
            }
            else if(mx == cur) {
                answer.add(i);
            }
        }
        for(int x: answer) System.out.printf("%d ", x);
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
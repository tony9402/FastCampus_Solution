import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {
    static int [][]dist = new int[202][202];
    static final int INF = 0x3f3f3f3f;
    public static void main(String[] args) {
        FastReader rd = new FastReader();

        int N = rd.nextInt();
        int M = rd.nextInt();

        for(int i=0;i<=N;i++) {
            for(int j=0;j<=N;j++) {
                if(i == j) dist[i][j] = 0;
                else dist[i][j] = INF;
            }
        }

        for(int i=0;i<M;i++) {
            int from = rd.nextInt();
            int to   = rd.nextInt();
            int cost = rd.nextInt();
            dist[from][to] = cost;
        }

        // Floyd
        for(int k=1;k<=N;k++) {
            for(int i=1;i<=N;i++) {
                for(int j=1;j<=N;j++) {
                    dist[i][j] = Math.min(dist[i][k] + dist[k][j], dist[i][j]);
                }
            }
        }

        int friends = rd.nextInt();
        ArrayList<Integer> friendList = new ArrayList<>();
        ArrayList<Integer> result     = new ArrayList<>();

        for(int i=0;i<friends;i++) {
            int x = rd.nextInt();
            friendList.add(x);
        }

        int max = Integer.MAX_VALUE;
        for(int i=1;i<=N;i++) {
            int cur = 0;
            for(int friend : friendList) {
                if(dist[friend][i] == INF || dist[i][friend] == INF) cur = Integer.MAX_VALUE;
                cur = Math.max(cur, dist[friend][i] + dist[i][friend]);
            }
            if(max > cur) {
                result.clear();
                result.add(i);
                max = cur;
            }
            else if(max == cur) {
                result.add(i);
            }
        }

        StringBuilder out = new StringBuilder();
        for(int x : result) out.append(x + " ");
        System.out.print(out);
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

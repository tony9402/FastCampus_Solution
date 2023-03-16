import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {
    static int []par = new int[100005];
    public static void main(String[] args) {
        FastReader rd = new FastReader();

        int N = rd.nextInt(), M = rd.nextInt();
        ArrayList<Tuple> edges = new ArrayList<>();
        long ans = 0;
        for(int i = 0; i < M; ++i) {
            int u = rd.nextInt(), v = rd.nextInt(), w = rd.nextInt();
            edges.add(new Tuple(w, u, v));
            ans += w;
        }
        Collections.sort(edges);
         
        for(int i = 0; i <= N; ++i) par[i] = -1;
        for(Tuple x : edges) if(merge(x.u, x.v)) ans -= x.w;
        for(int i = 2; i <= N; ++i) if(find(1) != find(i)) ans = -1;
        System.out.println(ans);
    }

    static public int find(int x) {
        if(par[x] < 0) return x;
        return par[x] = find(par[x]);
    }
    static public boolean merge(int u, int v) {
        u = find(u); v = find(v);
        if(u == v) return false;
        par[v] = u;
        return true;
    }

    static class Tuple implements Comparable<Tuple> {
        int w, u, v;
        public Tuple(int w, int u, int v) {
            this.w = w;
            this.u = u;
            this.v = v;
        }
        @Override
        public int compareTo(Tuple o) {
            return Integer.compare(this.w, o.w);
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
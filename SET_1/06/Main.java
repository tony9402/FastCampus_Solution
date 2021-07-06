import java.lang.*;
import java.util.*;
import java.io.*;

public class Main {
    static int[] par;

    public static void main(String[] args) throws IOException {
        FastReader rd = new FastReader();

        int N = rd.nextInt();
        int M = rd.nextInt();
        ArrayList<Edge> arr = new ArrayList<>();
        par = new int[N + 1];
        for(int i = 0; i <= N; i++) par[i] = -1;

        long ans = 0;
        for(int i = 0; i < M; i++){
            Edge cur = new Edge();
            cur.a = rd.nextInt();
            cur.b = rd.nextInt();
            cur.w = rd.nextLong();
            arr.add(cur);
            ans += cur.w;
        }

        Collections.sort(arr);

        int made = 0;
        for(int i = 0; i < M; i++){
            if(merge(arr.get(i).a, arr.get(i).b)) {
                ans -= arr.get(i).w;
                made ++;
            }
        }
        
        if (made != N - 1) ans = -1;
        
        System.out.print(ans);
    }

    static boolean merge(int a, int b) {
        a = find(a);
        b = find(b);
        if(a == b)return false;
        par[b] = a;
        return true;
    }

    static int find(int x) {
        if(par[x] < 0) return x;
        return par[x] = find(par[x]);
    }
    
    static class Edge implements Comparable<Edge> {
        long w;
        int a, b;

        @Override
        public int compareTo(Edge o) {
            return w > o.w ? 1 : -1;
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

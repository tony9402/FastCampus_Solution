import java.util.*;
import java.lang.*;
import java.io.*;

public class Main_bruteforce {
    static int A, B, C, M, mx;
    public static void main(String[] args) {
        FastReader rd = new FastReader();

        A = rd.nextInt();
        B = rd.nextInt();
        C = rd.nextInt();
        M = rd.nextInt();
        mx = 0;
        
        solve(0, 0, 0);
        System.out.println(mx);
    }

    static void solve(int T, int a, int s) {
        if(T == 24) {
            mx = Math.max(mx, s);
            return;
        }
        if(a + A <= M) solve(T + 1, a + A, s + B);
        solve(T + 1, Math.max(0, a - C), s);
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

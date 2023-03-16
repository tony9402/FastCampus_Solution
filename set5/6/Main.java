import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {
    public static void main(String[] args) {
        FastReader rd = new FastReader();

        int N = rd.nextInt(), K = rd.nextInt();
        int []arr = new int[N];
        int []DP = new int[N];
        for(int i = 0; i < N; ++i) arr[i] = rd.nextInt();
        DP[0] = 1;
        for(int i = 1; i < N; ++i) {
            for(int j = 1; i - j >= 0 && j <= K; ++j) {
                int x = j * (1 + Math.abs(arr[i] - arr[i - j]));
                if(x <= K) DP[i] |= DP[i - j];
            }
        }
        if(DP[N - 1] == 1) System.out.println("YES");
        else System.out.println("NO");
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
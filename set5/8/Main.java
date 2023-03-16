import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {
    public static void main(String[] args) {
        FastReader rd = new FastReader();

        int N = rd.nextInt();
        long []arr = new long[N];
        int []DP = new int[N];
        for(int i = 0; i < N; ++i) arr[i] = rd.nextLong();
        int L = 1, R = 1000000;
        while(L <= R) {
            for(int i = 0; i < N; ++i) DP[i] = 0;
            DP[0] = 1;
            int mid = (L + R) / 2;
            for(int i = 0; i < N - 1; i++) {
                for(int j = 1; j <= mid; j++) {
                    if(i + j >= N) break;
                    long x = j * (1L + Math.abs(arr[i] - arr[i + j]));
                    if(x <= mid) DP[i + j] |= DP[i];
                }
            }
            if(DP[N - 1] == 1) R = mid - 1;
            else L = mid + 1;
        }
        System.out.println(L);
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
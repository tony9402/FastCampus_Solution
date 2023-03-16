import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {
    static int[][] DP;
    public static void main(String[] args) {
        FastReader rd = new FastReader();

        int N = rd.nextInt();
        int K = rd.nextInt();
        DP = new int[K + 1][N + 1];

        for(int i = 1; i <= N; i++) {
            int x = rd.nextInt();
            if(x % 2 == 0) DP[0][i] = DP[0][i - 1] + 1;
            for(int j = 1; j <= K; j++) {
                if(x % 2 == 0) DP[j][i] = DP[j][i - 1] + 1;
                else DP[j][i] = DP[j - 1][i - 1];
            }
        }
        int answer = 0;
        for(int i = 1; i <= N; i++) answer = Math.max(answer, DP[K][i]);
        System.out.println(answer);
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

import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {
    public static void main(String[] args) {
        FastReader rd = new FastReader();
        
        int [][]arr = new int[1003][1003];
        int [][][]DP = new int[2][1003][1003];
        int N = rd.nextInt();
        int M = rd.nextInt();

        for(int i = 1; i <= N; ++i) {
            for(int j = 1; j <= M; ++j) {
                arr[i][j] = rd.nextInt();
            }
        }
        for(int i = 0; i <= N + 1; ++i) {
            for(int j = 0; j <= M + 1; ++j) {
                DP[0][i][j] = DP[1][i][j] = Integer.MIN_VALUE / 2;
            }
        }
    
        DP[0][N][1] = arr[N][1];
        DP[1][N][M] = arr[N][M];
        for(int i = N; i >= 1; --i) {
            for(int j = 1; j <= M; ++j) {
                if(i == N && j == 1) continue;
                DP[0][i][j] = Math.max(DP[0][i + 1][j], DP[0][i][j - 1]) + arr[i][j];
            }
        }
        for(int i = N; i >= 1; --i) {
            for(int j = M; j >= 1; --j) {
                if(i == N && j == M) continue;
                DP[1][i][j] = Math.max(DP[1][i + 1][j], DP[1][i][j + 1]) + arr[i][j];
            }
        }
    
        int ans = Integer.MIN_VALUE / 2;
        for(int i = 1; i <= N; ++i) {
            for(int j = 1; j <= M; ++j) {
                ans = Math.max(ans, DP[0][i][j] + DP[1][i][j]);
            }
        }
        System.out.print(ans);
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
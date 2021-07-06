import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {
    static int [][]arr;
    static long [][]LeftTORight;
    static long [][]RightTOLeft;
    public static void main(String[] args) {
        FastReader rd = new FastReader();
        
        int N = rd.nextInt();
        int M = rd.nextInt();
        arr = new int[N + 2][M + 2];
        LeftTORight = new long[N + 2][M + 2];
        RightTOLeft = new long[N + 2][M + 2];

        for(int i=1;i<=N;i++) {
            for(int j=1;j<=M;j++) {
                arr[i][j] = rd.nextInt();
            }
        }
        
        for(int i=0;i<=N+1;i++) {
            for(int j=0;j<=M+1;j++) {
                LeftTORight[i][j] = Long.MIN_VALUE / 2;
                RightTOLeft[i][j] = Long.MIN_VALUE / 2;
            }
        }

        LeftTORight[N][1] = arr[N][1];
        RightTOLeft[N][M] = arr[N][M];
        for(int i=N;i>=1;i--) {
            for(int j=1;j<=M;j++) {
                if(i == N && j == 1) continue;
                LeftTORight[i][j] = Math.max(LeftTORight[i + 1][j], LeftTORight[i][j - 1]) + arr[i][j];
            }
        }

        for(int i=N;i>=1;i--) {
            for(int j=M;j>=1;j--) {
                if(i == N && j == M) continue;
                RightTOLeft[i][j] = Math.max(RightTOLeft[i + 1][j], RightTOLeft[i][j + 1]) + arr[i][j];
            }
        }

        long answer = Long.MIN_VALUE / 2;
        for(int i=1;i<=N;i++) {
            for(int j=1;j<=M;j++) {
                answer = Math.max(answer, RightTOLeft[i][j] + LeftTORight[i][j]);
            }
        }
        System.out.print(answer);
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

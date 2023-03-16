import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {
    static int[][] DP;
    public static void main(String[] args) {
        FastReader rd = new FastReader();

        int N = rd.nextInt(), K = rd.nextInt();
        int []idx = new int[N + 1];
        int []arr = new int[N + 1];
        int []tmp = new int[N + 1];

        for(int i = 1; i <= N; i++) arr[i] = rd.nextInt();
        for(int i = 1; i <= N; i++) idx[i] = rd.nextInt();
        for(int i = 1; i <= K; i++) {
            for(int j = 1; j <= N; j++) tmp[idx[j]] = arr[j];
            for(int j = 1; j <= N; j++) arr[j] = tmp[j];
        }
        for(int i = 1; i <= N; i++) System.out.print(arr[i] + " ");
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

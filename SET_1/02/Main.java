import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {
    static int []arr;
    public static void main(String[] args) {
        FastReader rd = new FastReader();
        
        int N = rd.nextInt();
        arr = new int[N];
        for(int i=0;i<N;i++) arr[i] = rd.nextInt();
        int x = rd.nextInt();

        long sum = 0, cnt = 0;
        for(int i=0;i<N;i++) {
            if(GCD(arr[i], x) == 1) {
                sum += arr[i];
                cnt ++;
            }
        }

        System.out.printf("%.6f", (double)sum / cnt);
    }

    static long GCD(long a, long b) {
        if(b == 0) return a;
        return GCD(b, a % b);
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

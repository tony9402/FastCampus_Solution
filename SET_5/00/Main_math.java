import java.util.*;
import java.lang.*;
import java.io.*;

public class Main_math {
    static int []arr;
    public static void main(String[] args) {
        FastReader rd = new FastReader();

        int A = rd.nextInt();
        int B = rd.nextInt();
        int C = rd.nextInt();
        int M = rd.nextInt();

        int fatigue = 0;
        int ans = 0;
        for(int i = 0; i < 24; i++) {
            if(fatigue + A > M) {
                fatigue = Math.max(0, fatigue - C);
            }
            else {
                fatigue += A;
                ans += B;
            }
        }

        System.out.println(ans);
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

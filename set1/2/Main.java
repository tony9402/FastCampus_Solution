import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {
    public static void main(String[] args) {
        FastReader rd = new FastReader();

        int N = rd.nextInt();
        long ans = 1;
        for(int i = 0; i < N; ++i) {
            long x = rd.nextLong();
            if(check_prime(x)) ans = lcm(ans, x);
        }
        if(ans == 1) System.out.print(-1);
        else System.out.print(ans);
    }

    public static boolean check_prime(long num) {
        if(num < 2) return false;
        for(long i = 2; i * i <= num; ++i) {
            if(num % i == 0) return false;
        }
        return true;
    }

    public static long lcm(long a, long b) {
        return a / gcd(a, b) * b;
    }

    public static long gcd(long a, long b) {
        if(b == 0) return a;
        return gcd(b, a % b);
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
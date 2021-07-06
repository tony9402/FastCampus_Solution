import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {
    public static void main(String[] args) {
        FastReader rd = new FastReader();

        int N = rd.nextInt();
        long answer = 1;
        for(int i=0;i<N;i++) {
            long x = rd.nextLong();
            if(isPrime(x)) answer = LCM(answer, x);
        }
        if(answer == 1) answer = -1;
        System.out.println(answer);
    }

    static boolean isPrime(long x) {
        for(long i = 2; i * i <= x; i++){
            if(x % i == 0) return false;
        }
        return true;
    }
    
    static long GCD(long a, long b) {
        if(b == 0) return a;
        return GCD(b, a % b);
    }

    static long LCM(long a, long b) {
        return a / GCD(a, b) * b;
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

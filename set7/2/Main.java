import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {
    static ArrayList<Long> Prime = new ArrayList<>();
    static final long MAXN = 100000;
    static boolean []is_prime = new boolean[100000];
    static int K, M;
    static int ans;
    public static void main(String[] args) {
        FastReader rd = new FastReader();
        init();
        K = rd.nextInt();
        M = rd.nextInt();
        dfs(0, 0, 0);
        System.out.println(ans);
        return;
    }

    static public void init() {
        for(int i = 2; i < MAXN; ++i) is_prime[i] = true;
        for(int i = 2; i < MAXN; ++i) {
            if(is_prime[i] == false) continue;
            Prime.add((long)i);
            for(long j = (long)i * i; j < MAXN; j += i) is_prime[(int)j] = false;
        }
    }

    static boolean check_first(long x) {
        for(long p : Prime) {
            if(x - p < 0) break;
            if(x - p != p && is_prime[(int)(x - p)]) return true;
        }
        return false;
    }

    static boolean check_second(long x) {
        while(x % M == 0) x /= M;
        for(long p : Prime) {
            if(p * p > x) break;
            if(x % p != 0) continue;
            if(is_prime[(int)(x / p)]) return true;
        }
        return false;
    }

    static void dfs(int cnt, int bit, int x) {
        if(cnt == K) {
            if(check_first(x) && check_second(x)) ans ++;
            return;
        }
        for(int i = cnt == 0 ? 1 : 0; i < 10; i++) {
            if((bit & (1 << i)) != 0) continue;
            dfs(cnt + 1, bit | (1 << i), x * 10 + i);
        }
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
import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {
    public static void main(String[] args) {
        FastReader rd = new FastReader();
        
        int N = rd.nextInt();
        int X = rd.nextInt();
        int []arr = new int[N];
        for(int i = 0; i < N; ++i) arr[i] = rd.nextInt();

        int sum = 0;
        for(int i = 0; i < X; ++i) sum += arr[i];
        int mx = sum, cnt = 1;

        for(int i = X; i < N; ++i) {
            sum -= arr[i - X];
            sum += arr[i];
            if(mx < sum) {
                mx = sum;
                cnt = 1;
            }
            else if(mx == sum) {
                ++ cnt;
            }
        }

        if(mx == 0) System.out.print("SAD");
        else System.out.printf("%d\n%d", mx, cnt);
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
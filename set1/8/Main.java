import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {
    public static void main(String[] args) {
        FastReader rd = new FastReader();
        
        int N = rd.nextInt();
        int []arr = new int[N];
        for(int i = 0; i < N; ++i) arr[i] = rd.nextInt();

        int ans = 0;
        for(int i = 0, j = 0; i < N; ) {
            boolean flag = false;
            for(j = i + 1; j < N; j += 2) {
                int l = i, r = j;
                boolean is_palindrome = true;
                while(is_palindrome && l < r) {
                    if(arr[l] != arr[r]) is_palindrome = false;
                    ++ l; -- r;
                }
                if(is_palindrome) {
                    ++ans;
                    flag = true;
                    break;
                }
            }
            if(flag == false) {
                ans = -1;
                break;
            }
            i = j + 1;
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
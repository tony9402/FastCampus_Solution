import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {
    static int []arr;
    public static void main(String[] args) {
        FastReader rd = new FastReader();
        
        int N = rd.nextInt();
        int X = rd.nextInt();
        arr   = new int[N];
        for(int i=0;i<N;i++) {
            arr[i] = rd.nextInt();
        }

        long cnt = 0, max = 0, sum = 0;
        for(int s = 0, e = 0; e < N; ) {
            while(e - s < X) sum += arr[e ++];
            if(max < sum) {
                cnt = 1;
                max = sum;
            } 
            else if(max == sum) cnt ++;
            sum -= arr[s ++];
        }

        if(max == 0) System.out.print("SAD");
        else {
            System.out.print(max + " " + cnt);
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

import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {
    static int []bulb;
    public static void main(String[] args) {
        FastReader rd = new FastReader();
        int N = rd.nextInt();
        int M = rd.nextInt();

        bulb = new int[N + 1];
        
        for(int i=1;i<=N;i++) {
            bulb[i] = rd.nextInt();
        }
    
        for(int i=0;i<M;i++) {
            int command = rd.nextInt();
            int first   = rd.nextInt();
            int second  = rd.nextInt();
            if(command == 1) {
                bulb[first] = second;
            }
            else if(command == 2) {
                for(int j=first;j<=second;j++)
                    bulb[j] ^= 1; // bulb[j] = 1 - bulb[j];
            }
            else if(command == 3) {
                for(int j=first;j<=second;j++) 
                    bulb[j] &= 0;
            }
            else {
                for(int j=first;j<=second;j++) 
                    bulb[j] |= 1;
            }
        }

        StringBuilder out = new StringBuilder();
        for(int i=1;i<=N;i++) {
            out.append(bulb[i] + " ");
        }

        System.out.println(out);
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

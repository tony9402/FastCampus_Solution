import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {
    public static void main(String[] args) {
        FastReader rd = new FastReader();

        int N = rd.nextInt();
        int K = rd.nextInt();

        int[] index = new int[N + 1];
        int[] array = new int[N + 1];
        int[] temp  = new int[N + 1];

        for(int i = 1; i <= N; i++) array[i] = rd.nextInt();
        for(int i = 1; i <= N; i++) index[i] = rd.nextInt();
        for(int i = 1; i <= K; i++) {
            for(int j = 1; j <= N; j++) temp[index[j]] = array[j];
            for(int j = 1; j <= N; j++) array[j] = temp[j];
        }
        for(int i = 1; i <= N; i++) {
            System.out.print(array[i] + " ");
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


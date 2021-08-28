import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {
    static int MAXN = 1000005;
    static int []D = new int[MAXN];
    static int []arr = new int[MAXN];
    static int []used = new int[MAXN];
    static int []order = new int[MAXN];
    static int []answer = new int[MAXN];
    public static void main(String[] args) {
        FastReader rd = new FastReader();

        int N = rd.nextInt();
        long K = rd.nextLong();

        for(int i = 1; i <= N; i++) arr[i] = rd.nextInt();
        for(int i = 1; i <= N; i++) D[i] = rd.nextInt();

        for(int i = 1; i <= N; i++) {
            if(used[i] == 1) continue;
            int siz = 0, j = i;
            while(true) {
                order[siz ++] = j;
                used[j] = 1;
                j = D[j];
                if(i == j) break;
            }
            for(j = 0; j < siz; j++) {
                int cur = order[j];
                int next = order[(int)((j + K) % siz)];
                answer[next] = arr[cur];
            }
        }
        
        // StringBuilder 사용 O : 약 1.3s
        // StringBuilder 사용 X : 약 6.0s
        StringBuilder sb = new StringBuilder();
        for(int i = 1; i <= N; i++) {
            sb.append(answer[i]);
            sb.append(" ");
        }
        System.out.print(sb.toString());
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


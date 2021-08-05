import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {
    static int []arr;
    static int []choose;
    static int []operator;
    static boolean []used;
    static int P, Q, N;
    static long []values;
    static long answer = 0;
    public static void main(String[] args) {
        FastReader rd = new FastReader();

        N = rd.nextInt();
        used = new boolean[N];
        arr  = new int[N]; 
        choose = new int[N];
        operator = new int[N];
        values = new long[N];

        for(int i=0;i<N;i++) arr[i] = rd.nextInt();
        P = rd.nextInt(); Q = rd.nextInt();

        dfs(0);
        System.out.println(answer);
    }

    static void dfs(int cnt) {
        if(cnt == N) {
            dfs2(0);
            return;
        }

        for(int i=0;i<N;i++){
            if(used[i]) continue;
            used[i] = true;
            choose[cnt] = arr[i];
            dfs(cnt + 1);
            used[i] = false;
        }
    }

    static void dfs2(int cnt) {
        if(cnt == N - 1) {
            int idx = -1;
            values[++idx] = choose[0];
            for(int i=1;i<N;i++) {
                if(operator[i - 1] == 1) values[++idx] = choose[i];
                else values[idx] += choose[i];
            }
            long curValue = 1;
            for(int i=0;i<=idx;i++) curValue *= values[i];
            answer = Math.max(answer, curValue);
            return;
        }

        if(P > 0) {
            P --;
            operator[cnt] = 0;
            dfs2(cnt + 1);
            P ++;
        }
        if(Q > 0) {
            Q --;
            operator[cnt] = 1;
            dfs2(cnt + 1);
            Q ++;
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

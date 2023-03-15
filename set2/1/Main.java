import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {
    static List<Integer> []graph;
    static boolean []visited;
    public static void main(String[] args) {
        FastReader rd = new FastReader();

        int N = rd.nextInt();
        int M = rd.nextInt();

        graph = new ArrayList[N + 1];
        visited  = new boolean[N + 1];
        for(int i = 0; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for(int i = 0; i < M; i++) {
            int a = rd.nextInt();
            int b = rd.nextInt();
            graph[b].add(a);
        }

        Queue<Integer> Q = new LinkedList<>();

        int X = rd.nextInt();
        Q.add(X);
        visited[X] = true;

        int answer = 0;
        while(!Q.isEmpty()) {
            int cur = Q.poll();
            answer ++;
            for(int nxt : graph[cur]) {
                if(visited[nxt]) continue;
                visited[nxt] = true;
                Q.add(nxt);
            }
        }
        System.out.println(answer - 1);
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
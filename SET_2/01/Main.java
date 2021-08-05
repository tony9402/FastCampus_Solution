import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {
    static int [][]Map;
    static final int []dy = {-1,1,0,0};
    static final int []dx = {0,0,-1,1};
    public static void main(String[] args) {
        FastReader rd = new FastReader();

        int N = rd.nextInt();
        int M = rd.nextInt();
        Map = new int[N][M];

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                int color = 0;
                for(int k = 0; k < 3; k++) {
                    int x = rd.nextInt();
                    color += x;
                }
                Map[i][j] = color;
            }
        }
        int threshold = rd.nextInt();

        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++) {
                if(Map[i][j] >= threshold * 3) Map[i][j] = 255;
                else Map[i][j] = 0;
            }
        }

        Queue<Pair> Q = new LinkedList<>();

        int answer = 0;
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++) {
                if(Map[i][j] == 255) {
                    Q.add(new Pair(i, j));
                    Map[i][j] = 0;
                    answer ++;
                    while(!Q.isEmpty()) {
                        Pair cur = Q.poll();
                        for(int k = 0; k < 4; k++) {
                            int nextY = cur.first + dy[k];
                            int nextX = cur.second + dx[k];
                            if(0 > nextY || nextY >= N || 0 > nextX || nextX >= M) continue;
                            if(Map[nextY][nextX] != 255) continue;
                            Map[nextY][nextX] = 0;
                            Q.add(new Pair(nextY, nextX));
                        }
                    }
                }
            }
        }
        System.out.print(answer);

    }

    static class Pair {
        int first, second;
        public Pair(int _first, int _second) {
            first = _first;
            second = _second;
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

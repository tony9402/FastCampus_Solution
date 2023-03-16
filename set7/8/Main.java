import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {
    static char [][][]Map = new char[4][501][501];
    static int  [][][]dist = new int[4][501][501];
    static final int dx[] = {-1,1,0,0};
    static final int dy[] = {0,0,-1,1};
    static int K, N;
    public static void main(String[] args) {
        FastReader rd = new FastReader();

        K = rd.nextInt();
        N = 4 * K;
        Pair start_point = new Pair(0, 0), end_point = new Pair(0, 0);
        for(int i = 0; i < N; ++i) {
            String line = rd.next();
            for(int j = 0; j < N; ++j) {
                if(line.charAt(j) == 'S') start_point = new Pair(i, j);
                if(line.charAt(j) == 'E') end_point = new Pair(i, j);
                Map[0][i][j] = line.charAt(j);
                for(int k = 0; k < 4; ++k) dist[k][i][j] = -1;
            }
        }

        for(int i = 0; i < N; i += 4) {
            for(int j = 0; j < N; j += 4) {
                for(int k = 1; k < 4; k++) {
                    turnRight(k, i, j);
                }
            }
        }

        Queue<Tuple> Q = new LinkedList<>();
        Q.add(new Tuple(0, start_point.a, start_point.b));
        dist[0][start_point.a][start_point.b] = 0;
        while(!Q.isEmpty()) {
            Tuple cur = Q.poll();
            int d = cur.a, y = cur.b, x = cur.c;
            int curk = getIdx(y, x);

            for(int k = 0; k < 4; k++) {
                int qy = y + dy[k];
                int qx = x + dx[k];
                int nd = (d + 1) % 4;
                int nxtk = getIdx(qy, qx);
                if(0 > qy || qy >= N || 0 > qx || qx >= N) continue;
                if(curk != nxtk) {
                    if(Map[0][qy][qx] == '#') continue;
                    Pair ret = rotate(qy, qx);
                    qy = ret.a; qx = ret.b;
                    if(dist[1][qy][qx] != -1) continue;
                    dist[1][qy][qx] = dist[d][y][x] + 1;
                    Q.add(new Tuple(1, qy, qx));
                }
                else {
                    if(Map[d][qy][qx] == '#') continue;
                    Pair ret = rotate(qy, qx);
                    qy = ret.a; qx = ret.b;
                    if(dist[nd][qy][qx] != -1) continue;
                    dist[nd][qy][qx] = dist[d][y][x] + 1;
                    Q.add(new Tuple(nd, qy, qx));
                }
            }
            // STAY
            int qy = y, qx = x, nd = (d + 1) % 4;
            Pair ret = rotate(qy, qx);
            qy = ret.a; qx = ret.b;
            if(dist[nd][qy][qx] == -1) {
                dist[nd][qy][qx] = dist[d][y][x] + 1;
                Q.add(new Tuple(nd, qy, qx));
            }
        }

        int ans = -1;
        for(int i = 0; i < 4; i++) {
            int ret = dist[i][end_point.a][end_point.b];
            end_point = rotate(end_point.a, end_point.b);
            if(ret == -1) continue;
            if(ans == -1) ans = ret;
            else ans = Math.min(ans, ret);
        }
        System.out.println(ans);
    }

    static public void turnRight(int t, int y, int x) {
        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 4; j++) {
                Map[t][y + j][x + 3 - i] = Map[t - 1][y + i][x + j];
            }
        }
    }

    static public Pair rotate(int y, int x) {
        int by = y / 4 * 4, bx = x / 4 * 4;
        y %= 4; x %= 4;
        int ny = x + by;
        int nx = 3 - y + bx;
        y = ny; x = nx;
        return new Pair(y, x);
    }
    
    static public int getIdx(int y, int x) {
        y /= 4; x /= 4;
        return y * K + x;
    }


    static public class Pair {
        int a, b;
        public Pair(int a, int b) {
            this.a = a;
            this.b = b;
        }
    }

    static public class Tuple {
        int a, b, c;
        public Tuple(int a, int b, int c) {
            this.a = a;
            this.b = b;
            this.c = c;
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
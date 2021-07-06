import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {
    static int[][] Map;
    static boolean[][][] visited;
    static final int dy[] = {-1,1,0,0};
    static final int dx[] = {0,0,-1,1};
    public static void main(String[] args) {
        FastReader rd = new FastReader();
        
        int N = rd.nextInt();
        int M = rd.nextInt();

        Map = new int[N][M];
        visited = new boolean[N][M][5];

        Queue<Pair> que = new LinkedList<>();
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                Map[i][j] = rd.nextInt();
                if(Map[i][j] == 9){
                    que.add(new Pair(i, j));
                }
            }
        }
        
        int answer = 0;
        while(que.isEmpty() == false) {
            Pair cur = que.poll();
            int y = cur.y, x = cur.x;
            if(!visited[y][x][4]) {
                visited[y][x][4] = true;
                answer ++;
            }

            for(int k=0;k<4;k++) {
                int dir = k;
                int cy = y, cx = x;
                if(visited[cy][cx][dir]) continue;
                visited[cy][cx][dir] = true;
                while(true) {
                    cy += dy[dir];
                    cx += dx[dir];
                    if(0 > cy || cy >= N || 0 > cx || cx >= M) break;
                    if(visited[cy][cx][dir])break;
                    visited[cy][cx][dir] = true;
                    if(!visited[cy][cx][4]){
                        visited[cy][cx][4] = true;
                        answer ++;
                    }
                    dir = changeDir(Map[cy][cx], dir);
                }
            }
        }
        System.out.println(answer);
    }

    static int changeDir(int type, int dir) {
        if(type == 1) {
            if(dir == 2) return 3;
            if(dir == 3) return 2;
        }
        if(type == 2) {
            if(dir == 0) return 1;
            if(dir == 1) return 0;
        }
        if(type == 3) {
            if(dir == 0) return 3;
            if(dir == 1) return 2;
            if(dir == 2) return 1;
            if(dir == 3) return 0;
        }
        if(type == 4) {
            if(dir == 0) return 2;
            if(dir == 1) return 3;
            if(dir == 2) return 0;
            if(dir == 3) return 1;
        }
        return dir;
    }

    static class Pair {
        int y, x;
        Pair() { y = x = 0; }
        Pair(int _y, int _x) {
            y = _y;
            x = _x;
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

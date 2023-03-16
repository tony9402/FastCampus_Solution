import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {
    static Pair Start, End;
    static ArrayList<Pair> point = new ArrayList<>();
    static boolean []used = new boolean[15];
    static int ans = 1 << 30;
    static int N, H, D;
    public static void main(String[] args) {
        FastReader rd = new FastReader();
        
        N = rd.nextInt();
        H = rd.nextInt();
        D = rd.nextInt();

        for(int i = 0; i < N; ++i) {
            String line = rd.next();
            for(int j = 0; j < N; ++j) {
                if(line.charAt(j) == 'S') Start = new Pair(i, j);
                else if(line.charAt(j) == 'E') End = new Pair(i, j);
                else if(line.charAt(j) == 'U') point.add(new Pair(i, j));
            }
        }
        dfs(Start.x, Start.y, H, 0, 0);
        if(ans == 1 << 30) ans = -1;
        System.out.println(ans);
    }

    static public void dfs(int y, int x, int h, int d, int t) {
        int to_end = Math.abs(y - End.x) + Math.abs(x - End.y);
        if(h + d - to_end >= 0) {
            ans = Math.min(ans, t + to_end);
            return;
        }
    
        for(int i = 0; i < (int)point.size(); i++) {
            if(used[i]) continue;
            Pair cur = point.get(i);
            int ny = cur.x, nx = cur.y;
            int dis = Math.abs(ny - y) + Math.abs(nx - x);
            if(h + d - dis < 0) continue;
            used[i] = true;
            int nxtH = h - Math.max(0, dis - d - 1);
            dfs(ny, nx, nxtH, D - 1, t + dis);
            used[i] = false;
        }
    }

    static class Pair {
        int x, y;
        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
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
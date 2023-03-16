import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {
    static ArrayList<Tuple> circle;
    static ArrayList<ArrayList<Integer>> tree;
    static int N;
    public static void main(String[] args) {
        FastReader rd = new FastReader();

        N = rd.nextInt();
        circle = new ArrayList<>();
        tree = new ArrayList<>();
        for(int i = 0; i <= N; ++i) tree.add(new ArrayList<>());
        for(int i = 0; i <  N; ++i) {
            int x = rd.nextInt(), y = rd.nextInt(), r = rd.nextInt();
            circle.add(new Tuple(r, x, y, i + 1));
        }
        Collections.sort(circle);
        circle.add(new Tuple(1000000000, 0, 0, 0));
        for(int i = 0; i <= N; ++i) {
            for(int j = i + 1; j <= N; ++j) {
                Tuple a = circle.get(i), b = circle.get(j);
                long mr = a.a - b.a;
                long d = (a.b - b.b) * (a.b - b.b) + (a.c - b.c) * (a.c - b.c);
                if((d == 0 && mr != 0) || d < mr * mr) {
                    tree.get(a.d).add(b.d);
                    tree.get(b.d).add(a.d);
                    break;
                }
            }
        }

        Pair x = BFS(0);
        Pair y = BFS(x.b);
        System.out.println(y.a);
    }

    static public Pair BFS(int S) {
        Pair ret = new Pair(0, 0);
        Queue<Pair> queue = new LinkedList<>();
        int []used = new int[N + 1];
        queue.add(new Pair(S, 0));
        used[S] = 1;
        while(!queue.isEmpty()) {
            Pair cur = queue.poll();
            if(ret.a < cur.b) ret = new Pair(cur.b, cur.a);
            for(int nxt : tree.get(cur.a)) {
                if(used[nxt] == 1) continue;
                used[nxt] = 1;
                queue.add(new Pair(nxt, cur.b + 1));
            }
        }
        return ret;
    }

    static class Pair {
        int a, b;
        public Pair(int a, int b) {
            this.a = a; this.b = b;
        }
    } 

    static class Tuple implements Comparable<Tuple> {
        long a, b, c;
        int d;
        public Tuple(long a, long b, long c, int d) {
            this.a = a; this.b = b; this.c = c;
            this.d = d;
        }
        @Override
        public int compareTo(Tuple o) {
            return Long.compare(this.a, o.a);
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
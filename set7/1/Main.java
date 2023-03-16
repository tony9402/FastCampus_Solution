import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {
    public static void main(String[] args) {
        FastReader rd = new FastReader();

        int N = rd.nextInt();
        ArrayList<Tuple> V = new ArrayList<>();
        boolean []points = new boolean[2222222];
        final int base = 1010000;
        for(int i = 1; i <= N; ++i) {
            int x = rd.nextInt();
            int r = rd.nextInt();
            V.add(new Tuple(x - r, 1, i));
            V.add(new Tuple(x + r, -1, i));
            if(points[x - r + base] || points[x + r + base]) {
                System.out.print("NO");
                System.exit(0);
            }
            points[x - r + base] = points[x + r + base] = true;
        }
        Collections.sort(V);
        Stack<Integer> stack = new Stack<>();
        boolean ans = true;
        for(int i = 0; i < 2 * N; ++i) {
            Tuple value = V.get(i);
            if(value.b == 1) stack.push(value.c);
            else {
                if(stack.empty()) {
                    ans = false;
                    break;
                }
                else if(stack.peek() == value.c) stack.pop();
                else {
                    ans = false;
                    break;
                }
            }
        }
        if(ans) System.out.println("YES");
        else System.out.println("NO");
    }

    static public class Tuple implements Comparable<Tuple> {
        int a, b, c;
        public Tuple(int a, int b, int c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }
        @Override
        public int compareTo(Tuple o) {
            if(this.a != o.a) return Integer.compare(this.a, o.a);
            return Integer.compare(this.b, o.b);
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
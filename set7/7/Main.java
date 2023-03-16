import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {
    public static void main(String[] args) {
        FastReader rd = new FastReader();

        int N = rd.nextInt();
        ArrayList<Tuple> circle = new ArrayList<>();
        for(int i = 0; i < N; ++i) {
            int k = rd.nextInt(), x = rd.nextInt(), r = rd.nextInt();
            circle.add(new Tuple(r, x, k));
        }
        Collections.sort(circle);
        circle.add(new Tuple(1000000000, 0, 0));
        int A = rd.nextInt(), B = rd.nextInt();
        int idxA = 0, idxB = 0;
        while(idxA <= N && circle.get(idxA).c != A) ++idxA;
        while(idxB <= N && circle.get(idxB).c != B) ++idxB;

        Stack<Integer> L = new Stack<>();
        Stack<Integer> R = new Stack<>();
        boolean []visited = new boolean[200005];

        L.push(A);
        visited[A] = true;
        int ax = circle.get(idxA).b, ar = circle.get(idxA).a;
        int bx = circle.get(idxB).b, br = circle.get(idxB).a;
        for(int i = idxA + 1; i <= N; ++i) {
            Tuple value = circle.get(i);
            if(value.a == ar) continue;
            if(Math.abs(ax - value.b) > Math.abs(ar - value.a)) continue;
            ax = value.b; ar = value.a;
            if(value.c == B) break;
            L.push(value.c);
            visited[value.c] = true;
        }
        R.push(B);
        for(int i = idxB + 1; i <= N; i++) {
            Tuple value = circle.get(i);
            if(value.a == br) continue;
            if(Math.abs(bx - value.b) > Math.abs(br - value.a)) continue; 
            bx = value.b; br = value.a;
            if(visited[value.c]) {
                while(L.peek() != value.c) L.pop();
                break;
            }
            R.push(value.c);
        }
        System.out.println(L.size() + R.size());
        for(Integer x : L) System.out.print(x + " ");
        Collections.reverse(R);
        for(Integer x : R) System.out.print(x + " ");
    }

    static public class Tuple implements Comparable<Tuple> {
        int a, b, c;
        public Tuple(int a, int b, int c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }
        public int compareTo(Tuple o) {
            if(o.a != this.a) return Integer.compare(this.a, o.a);
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
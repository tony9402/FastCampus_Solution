import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {
    public static void main(String[] args) {
        FastReader rd = new FastReader();

        int N = rd.nextInt();
        ArrayList<Pair> arr = new ArrayList<>();
        for(int i = 0; i < N; ++i) {
            String a = rd.next(), b = rd.next();
            arr.add(new Pair(a, 1));
            arr.add(new Pair(b, -1));
        }
        Collections.sort(arr);
        int sum = 0, mx = 0;
        for(int i = 0; i < 2 * N; ++i) {
            sum += arr.get(i).b;
            mx = Math.max(mx, sum);
        }
        System.out.println(mx);
    }

    static class Pair implements Comparable<Pair> {
        String a;
        int b;
        public Pair(String a, int b) {
            this.a = a;
            this.b = b;
        }
        @Override
        public int compareTo(Pair o) {
            return a.compareTo(o.a);
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
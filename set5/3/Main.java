import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {
    static int[] H, cnt, building;
    public static void main(String[] args) {
        FastReader rd = new FastReader();

        int N = rd.nextInt();
        H = new int[N + 1];
        cnt = new int[N + 1];
        building = new int[N + 1];

        for(int i = 1; i <= N; ++i) H[i] = rd.nextInt();
        func(1, N + 1, 1);
        func(N, 0, -1);

        for(int i = 1; i <= N; ++i) {
            if(cnt[i] != 0) System.out.println(cnt[i] + " " + building[i]);
            else System.out.println(0);
        }
    }

    public static void func(int start, int end, int step) {
        Stack<Integer> stack = new Stack<>();
        while(start != end) {
            while(!stack.isEmpty() && H[stack.peek()] <= H[start]) stack.pop();
            if(!stack.isEmpty() && (building[start] == 0 || Math.abs(stack.peek() - start) < Math.abs(building[start] - start))) building[start] = stack.peek();
            cnt[start] += stack.size();
            stack.push(start);
            start += step;
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
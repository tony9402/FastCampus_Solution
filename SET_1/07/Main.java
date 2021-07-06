import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {
    static int []arr;
    public static void main(String[] args) {
        FastReader rd = new FastReader();
        
        int N = rd.nextInt();
        arr = new int[N];

        for(int i=0;i<N;i++) {
            arr[i] = rd.nextInt();
        }

        int answer = 0;
        for(int i=0, j; i < N; ) {
            boolean found = false;
            for(j=i+1; j < N; j += 2) {
                int L = i, R = j;
                boolean isPalindrome = true;
                while(L < R) {
                    if(arr[L] != arr[R]) {
                        isPalindrome = false;
                        break;
                    }
                    L ++; R --;
                }
                if(isPalindrome) {
                    answer ++;
                    found = true;
                    break;
                }
            }

            if(found == false) {
                answer = -1;
                break;
            }

            i = j + 1;
        }

        System.out.print(answer);
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

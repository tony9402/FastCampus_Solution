import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {
    static TreeSet<Pair> problemList;
    static HashMap<Integer, Integer> problemInfo;
    static public void main(String[] args) {
        FastReader rd = new FastReader();

        problemList = new TreeSet<>();
        problemInfo = new HashMap<>();
        int N = rd.nextInt();
        for(int i = 0; i < N; ++i) {
            int problemId = rd.nextInt();
            int level     = rd.nextInt();
            problemList.add(new Pair(level, problemId));
            problemInfo.put(problemId, level);
        }

        int M = rd.nextInt();
        String answer = "";
        StringBuffer out = new StringBuffer();
        for(int i = 0; i < M; ++i) {
            String cmd = rd.next();
            if(cmd.compareTo("add") == 0) {
                int problemId = rd.nextInt();
                int level     = rd.nextInt();
                problemList.add(new Pair(level, problemId));
                problemInfo.put(problemId, level);
            }
            else if(cmd.compareTo("recommend") == 0) {
                int x     = rd.nextInt();
                if(x == -1) {
                    out.append(problemList.first().second + "\n");
                }
                else {
                    out.append(problemList.last().second + "\n");
                }
            }
            else if(cmd.compareTo("solved") == 0) {
                int problemId = rd.nextInt();
                int level = problemInfo.get(problemId); // level, group
                problemList.remove(new Pair(level, problemId));
                problemInfo.remove(problemId);
            }
        }
        System.out.println(out);
    }

    static class Pair implements Comparable<Pair> {
        int first;
        int second;
        public Pair() { first = second = 0; }
        public Pair(int a, int b) {
            first = a;
            second = b;
        }
        
        @Override
        public int compareTo(Pair o) {
            if(this.first != o.first) return this.first - o.first;
            return this.second - o.second;
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
import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {
    static TreeSet<Pair> problemAllList;
    static TreeSet<Pair>[] problemList;
    static HashMap<Integer, Pair> problemInfo;
    static public void main(String[] args) {
        FastReader rd = new FastReader();

        problemList    = new TreeSet[101];
        problemAllList = new TreeSet<>();
        problemInfo    = new HashMap<>();
        for(int i = 0; i <= 100; ++i) problemList[i] = new TreeSet<>();
        int N = rd.nextInt();
        for(int i = 0; i < N; ++i) {
            int problemId = rd.nextInt();
            int level     = rd.nextInt();
            int group     = rd.nextInt();
            problemList[group].add(new Pair(level, problemId));
            problemAllList.add(new Pair(level, problemId));
            problemInfo.put(problemId, new Pair(level, group));
        }

        int M = rd.nextInt();
        String answer = "";
        StringBuffer out = new StringBuffer();
        for(int i = 0; i < M; ++i) {
            String cmd = rd.next();
            if(cmd.compareTo("add") == 0) {
                int problemId = rd.nextInt();
                int level     = rd.nextInt();
                int group     = rd.nextInt();
                problemList[group].add(new Pair(level, problemId));
                problemAllList.add(new Pair(level, problemId));
                problemInfo.put(problemId, new Pair(level, group));
            }
            else if(cmd.compareTo("recommend") == 0) {
                int group = rd.nextInt();
                int x     = rd.nextInt();
                if(x == -1) {
                    out.append(problemList[group].first().second + "\n");
                }
                else {
                    out.append(problemList[group].last().second + "\n");
                }
            }
            else if(cmd.compareTo("recommend2") == 0) {
                int x = rd.nextInt();
                if(x == -1) {
                    out.append(problemAllList.first().second + "\n");
                }
                else {
                    out.append(problemAllList.last().second + "\n");
                }
            }
            else if(cmd.compareTo("recommend3") == 0) {
                int x = rd.nextInt();
                int L = rd.nextInt();
                if(x == -1) {
                    Pair cur = problemAllList.lower(new Pair(L, -1));
                    if(cur == null) out.append("-1\n");
                    else out.append(cur.second + "\n");
                }
                else {
                    Pair cur = problemAllList.higher(new Pair(L, -1));
                    if(cur == null) out.append("-1\n");
                    else out.append(cur.second + "\n");
                }
            }
            else if(cmd.compareTo("solved") == 0) {
                int problemId = rd.nextInt();
                Pair info = problemInfo.get(problemId); // level, group
                problemList[info.second].remove(new Pair(info.first, problemId));
                problemAllList.remove(new Pair(info.first, problemId));
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

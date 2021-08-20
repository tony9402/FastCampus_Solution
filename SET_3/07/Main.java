// Save Source Code
import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {
    static int[] used, index, rindex, array, temp;
    static ArrayList<ArrayList<Integer>> group;
    public static void main(String[] args) {
        FastReader rd = new FastReader();

        int N = rd.nextInt();
        long K = rd.nextLong();
        used = index = rindex = array = temp = new int[N + 1];
        group = new ArrayList<ArrayList<Integer>>();
        for(int i = 0; i <= N; i++) {
            group.add(new ArrayList<Integer>());
            used[i] = -1;
        }

        for(int i = 1; i <= N; i++) array[i] = rd.nextInt();
        for(int i = 1; i <= N; i++) index[i] = rd.nextInt();

        for(int i = 1; i <= N; i++) {
            if(used[i] == -1) dfs(i, i, 1);
            int idx  = rindex[i];
            int size = group.get(idx).size();
            int group_index = (int)(used[i] + K % size - 1) % size;
            int pre = group.get(idx).get(group_index);
            temp[pre] = array[i];
        }

        for(int i = 1; i <= N; i++) {
            System.out.print(temp[i] + " ");
        }
    }

    static void dfs(int cur, int root, int number) {
        used[cur] = number;
        rindex[cur] = root;
        group.get(root).add(cur);
        if(used[index[cur]] == -1) dfs(index[cur], root, number + 1);
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


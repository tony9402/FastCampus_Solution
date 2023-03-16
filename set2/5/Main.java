import java.util.*;
import java.lang.*;
import java.io.*;

class Main{
    static class pair {
        int siz, score;
        pair(int _siz, int _score) {
            this.siz = _siz;
            this.score = _score;
        }
    }

    static List<pair> ava[];
    static int DP[];
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String S = br.readLine();
        int len = S.length();
        DP = new int[len + 1];
        ava = new ArrayList[len];
        for(int i = 0; i < len; ++i)ava[i] = new ArrayList<>();
        int n = Integer.parseInt(br.readLine());

        for(int i = 0; i < n; ++i){
            String []str = br.readLine().split(" ");
            int score = Integer.parseInt(str[1]);
            int strLen = str[0].length();
            if(strLen >= score) continue;

            int idx = 0;
            while(idx < len) {
                int cur = S.indexOf(str[0], idx);
                if(cur == -1) break;
                ava[cur].add(new pair(strLen, score));
                idx = cur + 1;
            }
        }

        for(int i = 0; i < len; ++i){
            for(int j = 0; j < ava[i].size(); ++j){
                int siz = ava[i].get(j).siz;
                int score = ava[i].get(j).score;
                DP[i + siz] = Math.max(DP[i + siz], DP[i] + score);
            }
            DP[i + 1] = Math.max(DP[i + 1], DP[i] + 1);
        }

        System.out.println(DP[len]);
    }
}
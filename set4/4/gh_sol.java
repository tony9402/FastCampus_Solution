import java.io.*;
import java.lang.*;
import java.util.*;
public class Main {
    public static void main(String []args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        List <String> event = new ArrayList<>();
        String []parse;
        parse = bf.readLine().split(" ");
        int n = Integer.parseInt(parse[0]);
        int Q = Integer.parseInt(parse[1]);

        String []eventIdx = new String[n];
        int []eventLv = new int[n];

        String []queryS = new String[Q];
        String []queryE = new String[Q];
        int []queryLv = new int[Q];

        int [][]arr = new int[7][n + 2*Q + 1];
        int [][]nj = new int [7][n + 2*Q + 1];
        event.add("");
        for(int i=0;i<n;i++){
            parse = bf.readLine().split("#");
            eventIdx[i] = parse[0];
            eventLv[i] = Integer.parseInt(parse[1]);
            event.add(eventIdx[i]);
        }
        for(int i=0;i<Q;i++){
            parse = bf.readLine().split("#");
            queryS[i] = parse[0]; event.add(parse[0]);
            queryE[i] = parse[1]; event.add(parse[1]);
            queryLv[i] = Integer.parseInt(parse[2]);
        }
        Collections.sort(event);
        for(int i=0;i<n;i++){
            int lo = Collections.binarySearch(event, eventIdx[i]);
            arr[eventLv[i]][lo]++;
        }
        for(int i=1;i<=6;i++) {
            for (int j = 1; j <= n + 2 * Q; j++) {
                nj[i][j] = nj[i - 1][j] + nj[i][j - 1] - nj[i - 1][j - 1] + arr[i][j];
            }
        }
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<Q;i++){
            int s = Collections.binarySearch(event, queryS[i]);
            int e = Collections.binarySearch(event, queryE[i]);
            int lv = queryLv[i];

            int ans = calc(lv, s, e, nj);
            sb.append(ans + "\n");
        }
        System.out.println(sb.toString());
    }

    private static int calc(int x1, int y1, int y2, int[][] nj) {
        return nj[6][y2] - nj[x1-1][y2] - nj[6][y1-1] + nj[x1-1][y1-1];
    }
}
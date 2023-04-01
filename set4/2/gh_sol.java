import java.io.*;
import java.lang.*;
import java.util.*;
public class Main {
    private static int []op = new int[20];
    public static void main(String []args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String []parse;
        parse = bf.readLine().split(" ");
        int R = Integer.parseInt(parse[0]);
        int C = Integer.parseInt(parse[1]);
        String []str = new String[R];
        int T = Integer.parseInt(parse[2]);
        int sx = -1, sy = -1;
        for(int i=0;i<R;i++){
            str[i] = bf.readLine();
            if(str[i].indexOf('G') != -1){
                sx = i; sy = str[i].indexOf('G');
            }
        }
        int mmx = 0;
        for(int i=0; i<(1<<(2*T)); i++) {
            int cur = i;
            for(int j=0; j<T; j++){
                op[j] = cur%4;
                cur = cur/4;
            }
            int r = gogo(sx, sy, R, C, T, str);
            if(r > mmx)
                mmx = r;
        }
        System.out.println(mmx);
    }

    private static int gogo(int sx, int sy, int R, int C, int t, String []map)
    {
        int []dx = {1, 0, -1, 0};
        int []dy = {0, 1, 0, -1};
        int cx = sx;
        int cy = sy;
        int ans = 0;
        int kei = 202;
        Set <Integer> ss = new TreeSet<>();

        for(int i=0;i<t;i++){
            cx += dx[op[i]];
            cy += dy[op[i]];

            if(cx < 0 || cy < 0 || cx >= R || cy >= C)
                return 0;
            if(map[cx].charAt(cy) == '#')
                return 0;
            ss.add(cx * kei + cy);
        }
        for(int cur:ss){
            int xx = cur/kei; int yy = cur%kei;
            if(map[xx].charAt(yy) == 'S')
                ans++;
        }
        return ans;
    }
}
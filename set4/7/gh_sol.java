import java.io.*;
import java.util.*;
class Main {
    private static final int pidMax = 1000000;
    public static void main(String []args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(bf.readLine());
        String []parse = bf.readLine().split(" ");
        int []arr = new int[t];
        int []s = new int[pidMax + 1];
        for(int i=0; i<=pidMax; i++)
            s[i] = Integer.MAX_VALUE;
        int []e = new int[pidMax + 1];
        for(int i=0; i<t; i++)
            arr[i] = Integer.parseInt(parse[i]);
        int prev = -1, ti = 1;
        for(int i=0; i<t; i++)
        {
            if(prev >= arr[i])
                ti++;
            int pid = arr[i];
            s[pid] = Math.min(s[pid], ti);
            e[pid] = Math.max(e[pid], ti);
            prev = arr[i];
        }
        int count = 0;
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<=pidMax; i++){
            if(e[i] == 0)
                continue;
            count++;
            int b = e[i] - s[i] + 1;
            int c = pidMax - s[i];
            sb.append(i + " " + b + " " + c + "\n");
        }
        System.out.println(count);
        System.out.print(sb.toString());
    }
}

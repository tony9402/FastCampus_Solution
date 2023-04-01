import java.util.*;
import java.lang.*;
import java.io.*;
public class Main {
    public static void main(String []args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String []parse;
        Map <String, String> kMap = new HashMap<>();
        parse = bf.readLine().split(" ");
        int n = Integer.parseInt(parse[0]);
        int m = Integer.parseInt(parse[1]);
        for(int i=0;i<n;i++)
            kMap.put(bf.readLine(), "");
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<m;i++){
            for(String k:bf.readLine().split(","))
                kMap.computeIfPresent(k, (ke, ve)->null);
            sb.append(kMap.size()+"\n");
        }
        System.out.println(sb.toString());
    }
}
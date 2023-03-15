import java.io.*;
import java.util.*;
import java.lang.*;

public class Main {
    final static int days[] = {0,31,28,31,30,31,30,31,31,30,31,30,31};
    static public void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        String []line = br.readLine().split(" ");

        // Input
        int N = Integer.parseInt(line[0]);
        String []tmp = line[1].split("/");
        int day    = Integer.parseInt(tmp[0]);
        int hour   = Integer.parseInt(tmp[1].split(":")[0]);
        int minute = Integer.parseInt(tmp[1].split(":")[1]);
        long limit = (hour + day * 24) * 60 + minute;

        int money  = Integer.parseInt(line[2]);

        HashMap<String, HashMap<String, Long>> mp = new HashMap<>();
        HashMap<String, Long> fine = new HashMap<>();
        for(int i = 0; i < N; ++i) {
            line = br.readLine().split(" ");
            long curTime = getTime(line[0], line[1]);
            String nickname = line[3];
            String partname = line[2];
            
            if(mp.containsKey(nickname)) {
                if(mp.get(nickname).containsKey(partname)) {
                    long prev = mp.get(nickname).get(partname);
                    long dist = Math.max(0L, curTime - prev - limit);
                    
                    if(fine.containsKey(nickname)) {
                        long update = fine.get(nickname) + dist * money;
                        fine.put(nickname, update);
                    }
                    else {
                        fine.put(nickname, dist * money);
                    }

                    mp.get(nickname).remove(partname);
                }
                else {
                    mp.get(nickname).put(partname, curTime);
                }
            }
            else {
                mp.put(nickname, new HashMap<String, Long>());
                mp.get(nickname).put(partname, curTime);
            }
        }

        List<String> keyList = new ArrayList<>(fine.keySet());
        keyList.sort((s1, s2) -> s1.compareTo(s2));

        Boolean ans = false;
        StringBuilder out = new StringBuilder();
        for(String key: keyList) {
            if(fine.get(key) == 0)continue;
            out.append(key + " " + fine.get(key) + '\n');
            ans = true;
        }
        if(ans == false) {
            out.append("-1");
        }
        System.out.print(out);
    }

    static long getDate(String date) {
        String []line = date.split("-");
        int month = Integer.parseInt(line[1]);
        int day   = Integer.parseInt(line[2]);

        long ret = day - 1;
        for(int i = 0; i < month; ++i) ret += days[i];
        return ret;
    }

    static long getTime(String time) {
        String []line = time.split(":");
        int hh = Integer.parseInt(line[0]);
        int mm = Integer.parseInt(line[1]);
        return hh * 60 + mm;
    }

    static long getTime(String date, String time) {
        String []line = time.split(":");
        int hh = Integer.parseInt(line[0]);
        int mm = Integer.parseInt(line[1]);
        return (hh + getDate(date) * 24) * 60 + mm;
    }
}
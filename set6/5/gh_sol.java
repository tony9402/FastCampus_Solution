import java.util.*;
import java.lang.*;
import java.io.*;
class Dia{
    private final int []arr = new int[300];
    private final int []njT = new int[300];
    Dia(String []dat, int defaultTime, int stopTime){
        for(int i=1; i<300; i++)
            this.arr[i] = defaultTime + stopTime;

        for(String data : dat){
            String []tokens = data.split("\\s+");
            fill(tokens[0], tokens[2], 20);
            fill(tokens[3], tokens[5], 20);
        }

        for(int i=1; i<300; i++)
            this.njT[i] = this.njT[i-1] + this.arr[i];

    }
    public int getDuration(int s, int e){
        return this.njT[e-1] - this.njT[s-1];
    }
    private void fill(String stationNum, String time, int stopTime){
        int loc = Integer.parseInt(stationNum.substring(1));
        int minute = Integer.parseInt(time);
        this.arr[loc] = 60*minute + stopTime;
    }
}
class Train{
    private final int start;
    private final int end;
    private final int startSecond;
    public Train(String start, String end, String time){
        this.start = Integer.parseInt(start.substring(1));
        this.end = Integer.parseInt(end.substring(1));
        String []token = time.split(":");
        this.startSecond = Integer.parseInt(token[0]) * 60 * 60
                + Integer.parseInt(token[1]) * 60;
    }
    public int getStartStation(){
        return this.start;
    }
    public int getEndStation(){
        return this.end;
    }
    public int getStartSecond(){
        return this.startSecond;
    }
    public String toString(){
        return "[" + this.start + " ~ " + this.end + "]: " + this.startSecond;
    }
}
public class Main {
    public static void main(String []args) throws IOException {
        String []dat = {
                "K210	K211	3	K220	K221	4",
                "K221	K222	4	K222	K223	3",
                "K225	K226	3	K238	K239	3",
                "K245	K246	4	K247	K248	5",
                "K249	K250	4	K250	K251	3",
                "K256	K257	3	K266	K267	3"
        };
        Dia trainDia = new Dia(dat, 120, 20);

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String []parse;
        parse = bf.readLine().split(":");
        int hh = Integer.parseInt(parse[0]);
        int mm = Integer.parseInt(parse[1]);
        int ss = Integer.parseInt(parse[2]);
        int curSecond = 60*60*hh + 60*mm + ss;
        int n = Integer.parseInt(bf.readLine());
        Train []tr = new Train[n];

        for(int i=0;i<n;i++){
            parse = bf.readLine().split(" ");
            tr[i] = new Train(parse[0], parse[1], parse[2]);
        }

        int []rideStation = {225, 233, 246, 258, 272};
        for(int i=0; i<4; i++){
            int curStation = rideStation[i];
            int nextStation = rideStation[i+1];
            int mmnTime = 0x3f3f3f3f;
            for(Train t : tr){
                int ts = t.getStartStation();
                int te = t.getEndStation();
                if(te <= curStation || curStation < ts)
                    continue;
                int timeStart = t.getStartSecond();
                int leaveTime = timeStart + trainDia.getDuration(ts, curStation);
                if(curSecond < leaveTime && leaveTime < mmnTime)
                    mmnTime = leaveTime;
            }
            if(mmnTime == 0x3f3f3f3f){
                System.out.println("-1"); return;
            }
            curSecond = mmnTime + trainDia.getDuration(curStation, nextStation) - 20;
            //printTime(curSecond);
        }
        if(curSecond >= 86400)
            System.out.println(-1);
        else
            printTime(curSecond);
    }
    private static void printTime(int sec){
        int h = sec/3600;
        int m = (sec%3600)/60;
        int s = (sec%3600)%60;
        System.out.println(String.format("%02d",h)+":"
                +String.format("%02d",m)+":"+String.format("%02d",s));
    }
}
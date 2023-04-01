import java.io.*;
import java.lang.*;
import java.util.*;
class Proc implements Comparable<Proc>{
    private final int id;
    private int time;
    private int prio;
    Proc(int id,int time,int prio){
       this.id = id;
       this.time = time;
       this.prio = prio;
    }
    public int getId(){
        return this.id;
    }
    public int getPrio(){
        return this.prio;
    }
    public void execute(){
        this.time -= 1;
        this.prio -= 1;
    }
    public boolean isAbleToExecute(){
        return this.time > 0;
    }

    @Override
    public int compareTo(Proc o) {
        if(this.prio != o.getPrio())
            return o.getPrio() - this.prio;
        return this.getId() - o.getId();
    }

    public String toString(){
        return "["+this.id + ", " + this.prio + ", " + this.time + "]";
    }
}
public class Main {
    public static void main(String []args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String []parse;
        parse = bf.readLine().split(" ");
        int T = Integer.parseInt(parse[0]);
        int n = Integer.parseInt(parse[1]);
        PriorityQueue<Proc> pq = new PriorityQueue<>();
        for(int i=0;i<n;i++){
            parse = bf.readLine().split(" ");
            int id = Integer.parseInt(parse[0]);
            int time = Integer.parseInt(parse[1]);
            int prio = Integer.parseInt(parse[2]);
            Proc p = new Proc(id, time, prio);
            pq.add(p);
        }
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<T;i++){
            Proc p = pq.poll();
            sb.append(p.getId()+"\n");
            p.execute();
            if(p.isAbleToExecute())
                pq.add(p);
        }
        System.out.println(sb.toString());
    }
}
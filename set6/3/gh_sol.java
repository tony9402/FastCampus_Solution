import java.util.*;
import java.lang.*;
import java.io.*;
class People{
    private int workTime;
    private int needTime;
    private int workTimeLimit;
    private int id;
    People(int id, int needTime,int wLimit){
        this.needTime = needTime;
        this.id = id;
        this.workTime = 0;
        this.workTimeLimit = wLimit;
    }
    public int getState(){
        if(this.needTime == 0)
            return -1;
        else if(this.workTime >= workTimeLimit) {
            this.workTime = 0;
            return 1;
        }
        return 0;
    }
    public void work(){
        this.needTime--;
        this.workTime++;
    }
    public int getId(){
        return this.id;
    }
    public String toString(){
        return "[" + this.id + ", " + this.workTime + ", " + this.needTime + "]";
    }
}
public class Main {
    public static void main(String []args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String []parse;
        People []event = new People[200001];
        Map <String, String> kMap = new HashMap<>();
        Deque <People> waitQueue = new ArrayDeque<>();
        parse = bf.readLine().split(" ");
        int n = Integer.parseInt(parse[0]);
        int T = Integer.parseInt(parse[1]);
        int w = Integer.parseInt(parse[2]);
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<n;i++){
            parse = bf.readLine().split(" ");
            int id = Integer.parseInt(parse[0]);
            int needTime = Integer.parseInt(parse[1]);
            waitQueue.add(new People(id, needTime, T));
        }
        int m = Integer.parseInt(bf.readLine());
        for(int i=0;i<m;i++){
            parse = bf.readLine().split(" ");
            int id = Integer.parseInt(parse[0]);
            int needTime = Integer.parseInt(parse[1]);
            int comeTime = Integer.parseInt(parse[2]);
            if(comeTime <= 200000)
                event[comeTime] = new People(id, needTime, T);
        }
        for(int i=0;i<w;i++){
            if(event[i] != null)
                waitQueue.add(event[i]);

            People front = waitQueue.poll();

            //이제, 맨 앞에 있는 고객에 대해서 state 를 얻는다.
            //state 에 따라서, 바깥으로 나가거나, 뒤에 다시 가거나, 앞에 있게 된다.
            switch(front.getState()){
                case -1: break; //out of waitQueue
                case 1 : waitQueue.addLast(front); break;
                case 0 : waitQueue.addFirst(front); break;
            }
            front = waitQueue.peek();    front.work();
            sb.append(front.getId()+"\n");
        }
        System.out.println(sb.toString());
    }
}
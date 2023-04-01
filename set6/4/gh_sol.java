import java.io.*;
import java.util.*;
class Calc {
    public static long gcd(long x, long y) {
        long ny = x % y;
        if(x % y == 0)
            return y;
        return gcd(y, ny);
    }
}
class Direct{
    private final long x;
    private final long y;
    public Direct(long x, long y){
        if(x != 0 && y != 0){
            long g = Calc.gcd(Math.max(Math.abs(x), Math.abs(y)),
                    Math.min(Math.abs(x), Math.abs(y)));
            this.x = x/g;
            this.y = y/g;
        }
        else if(x == 0){
            this.y = ((y > 0)? 1: -1);
            this.x = 0;
        }
        else {
            this.x = ((x > 0)? 1: -1);
            this.y = 0;
        }
    }

    public long getX() {
        return x;
    }

    public long getY() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Direct direct = (Direct) o;
        return x == direct.getX() && y == direct.getY();
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
class Main {
    public static void main(String []args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String []parse;
        Direct b = new Direct(1, 1), att;
        parse = bf.readLine().split(" ");
        long n = Long.parseLong(parse[0]); long m = Long.parseLong(parse[1]);
        PriorityQueue<Long> pq = new PriorityQueue<>();
        for(int i=0; i<n; i++){
            parse = bf.readLine().split(" ");
            b = new Direct(Long.parseLong(parse[0]),
                    Long.parseLong(parse[1]));
            pq.add(Long.parseLong(parse[2]));
        }
        long njDamage = 0;
        StringBuilder ans = new StringBuilder();
        for(int i=0; i<m; i++){
            parse = bf.readLine().split(" ");
            att = new Direct(Long.parseLong(parse[0]),
                    Long.parseLong(parse[1]));
            if(att.equals(b)){
                njDamage += Long.parseLong(parse[2]);
                while(!pq.isEmpty() && pq.peek() <= njDamage)
                    pq.poll();
            }
            ans.append(pq.size() + "\n");
        }
        System.out.print(ans.toString());
    }
}
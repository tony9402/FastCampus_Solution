import java.lang.*;
import java.util.*;
public class Main {
    public static void main(String []args){
        Scanner sc = new Scanner(System.in);
        int R = sc.nextInt();
        int C = sc.nextInt();
        int ans = 0;
        int Rg = sc.nextInt();
        int Cg = sc.nextInt();
        int Rp = sc.nextInt();
        int Cp = sc.nextInt();
        sc.nextLine();
        for(int i=0;i<R;i++){
            String li = sc.nextLine().replaceAll("P","");
            ans = ans + C - li.length();
        }
        if(ans != Rp * Cp)
            System.out.println("1");
        else
            System.out.println("0");
    }
}
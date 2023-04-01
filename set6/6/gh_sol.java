import java.util.*;
class Main
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        String []token = sc.nextLine().split(" ");
        int n = Integer.parseInt(token[0]);
        int M = Integer.parseInt(token[1]);
        long []dp = new long[4002];
        dp[0] = dp[2] = 1L;
        for(int i=4; i<=4000; i=i+2)
        {
            dp[i] = dp[i-2];
            for(int j=2; j<i; j=j+2)
            {
                dp[i] = dp[i] + dp[j]*dp[i-j-2];
                dp[i] %= M;
            }
        }
        System.out.println(dp[n-2]+"");
    }
}

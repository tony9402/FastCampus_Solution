#include <stdio.h>
//#define M 1000000007LL
typedef long long ll;
ll dp[4002];
int main(void)
{
    ll n, M; scanf("%lld%lld",&n,&M);
    dp[0] = dp[2] = 1;
    for(ll i=4; i<=4000; i=i+2)
    {
        dp[i] = dp[i-2];
        for(ll j=2; j<i; j=j+2)
        {
            dp[i] = dp[i] + dp[j]*dp[i-j-2];
            dp[i] %= M;
        }
    }
    printf("%lld\n",dp[n-2]);
    return 0;
}
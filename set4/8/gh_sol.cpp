#include <stdio.h>
#include <algorithm>
#include <vector>
#include <map>
#define LIM 1000000000001LL
#define INF 8000000000000LL
using namespace std;
typedef long long ll;

//id, time, prio
struct moo
{
    ll id, time, prio;
};
typedef struct moo moo;
moo arr[100000];
ll n,Q;
vector <ll> v;
map <ll, ll> dp;
bool cmp(moo a,moo b)
{
    return a.id < b.id;
}
//prio, tot_T
ll f(ll x)
{
    if(dp.find(x) != dp.end())
        return dp[x];
    ll tot = 0;
    for(ll i=0;i<n;i++)
    {
        //prio ~>> prio-time+1
        ll s = arr[i].prio - arr[i].time + 1;
        ll e = arr[i].prio;
        
        //[s, e]
        if(x <= s)
            tot = tot + arr[i].time;
        else if(s < x && x <= e)
            tot = tot + (e-x+1);
    }
    dp[x] = tot;
    return tot;
}
void gahui(ll t)
{
    ll le = 0, ri = INF, mid; v.clear();
    while(le <= ri)
    {
        mid = (le + ri)/2;
        
        if(f(mid) >= t)
        {
            ll tot_T = f(mid+1);
            if(tot_T < t)
            {
                //printf("mid = %lld\n",mid);
                ll r = t-tot_T;
                
                //prio is mid?
                for(int i=0;i<n;i++)
                    if(arr[i].prio - arr[i].time < mid && mid <= arr[i].prio)
                        v.push_back(arr[i].id);
                printf("%lld\n",v[r-1]);
                break;
            }
            else
                le = mid + 1;
        }
        else
            ri = mid-1;
    }
}
int main(void)
{
    scanf("%lld%lld",&Q, &n);
    for(ll i=0;i<n;i++)
        scanf("%lld%lld%lld",&(arr[i].id), &(arr[i].time), &(arr[i].prio));
    for(ll i=0;i<n;i++)
        arr[i].prio += LIM;
    sort(arr, arr+n, cmp);
    for(ll i=0;i<Q;i++)
    {
        ll t; scanf("%lld",&t); gahui(t);
    }
    
    /*
    if Q < 10^5
    ? + ? + ?
    but I don't know it is help to prepare coding test.
    if ok. I will prepare Q larger at set 6.
    */
    return 0;
}
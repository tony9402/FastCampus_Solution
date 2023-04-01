#include <stdio.h>
#include <algorithm>
#include <vector>
#include <stdlib.h>
#define M 200000LL
using namespace std;
typedef long long ll;
vector <ll> vv;
struct event
{
    ll a;
    ll c;
};
typedef struct event event;
struct query
{
    ll a; 
    ll b;
    ll c;
};
typedef struct query query;
event e[M+1];
query q[M+1];
ll arr[7][3*M+1];
ll nj[7][3*M+1];
char str[100];
char str2[100];
ll conv(char *str){
    ll ret = 0;
    for(ll i=0;str[i];i++){
        if(str[i] < '0' || str[i] > '9')
            continue;
        ret *= 10;
        ret = ret + (str[i] - '0');
    }
    return ret;
}
ll CC(ll x1, ll y1, ll x2, ll y2)
{
    return nj[x2][y2] - nj[x2][y1-1] - nj[x1-1][y2] + nj[x1-1][y1-1];
}
int main(void)
{
    ll n,Q; scanf("%lld%lld",&n,&Q);
    ll YY1, MM1, DD1, hh1, mm1, ss1, lv;
    ll YY2, MM2, DD2, hh2, mm2, ss2;
    vv.push_back(-1LL);
    for(ll i=0;i<n;i++)
    {
        scanf("\n%[^#]#%lld",str,&lv);
        e[i].a = conv(str); e[i].c = lv;
        vv.push_back(e[i].a);
    }
    for(ll i=0;i<Q;i++)
    {
        scanf("\n%[^#]#%[^#]#%lld",str,str2,&lv);
        q[i].a = conv(str); q[i].b = conv(str2); q[i].c = lv;
        vv.push_back(q[i].a); vv.push_back(q[i].b);
    }
    sort(vv.begin(), vv.end());
    vv.erase(unique(vv.begin(), vv.end()), vv.end());
    for(ll i=0;i<n;i++)
    {
        ll a = (ll)(lower_bound(vv.begin(), vv.end(), e[i].a) - vv.begin());
        ll c = e[i].c;
        arr[c][a]++;
    }
    for(ll lv = 1; lv <= 6; lv++)
        for(ll u = 1; u <= 600000; u++)
            nj[lv][u] = nj[lv-1][u] + nj[lv][u-1] 
            - nj[lv-1][u-1] + arr[lv][u];
    for(ll i=0;i<Q;i++)
    {
        ll s1 = (ll)(lower_bound(vv.begin(), vv.end(), q[i].a) - vv.begin());
        ll e1 = (ll)(lower_bound(vv.begin(), vv.end(), q[i].b) - vv.begin());
        ll lv = q[i].c;
        //if(s1 <= 0 || e1 <= 0) while(1){}
        ll ans = CC(lv, s1, 6, e1);
        printf("%lld\n",ans);
    }
}
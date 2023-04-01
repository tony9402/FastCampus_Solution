#include <stdio.h>
#include <queue>
#include <vector>
#include <algorithm>
#define me 1000000000LL
#define ME 2020202020LL
using namespace std;
typedef long long ll;
struct balloon
{
    ll x,y,hp;
};
typedef struct balloon balloon;
struct att
{
    ll x,y,d;
};
typedef struct att att;
balloon b[200000];
att a[200000];
ll _gcd(ll x, ll y)
{
    if(y == 0)
        return x;
    if(x % y == 0)
        return y;
    return _gcd(y, x%y);
}
ll gcd(ll x,ll y)
{
    if(x > y)
        return _gcd(x, y);
    return _gcd(y, x);
}
vector <ll> ve;
void add(ll x, ll y)
{
    ll tx = x + me; ll ty = y + me;
    ve.push_back(ME*tx + ty);
}
ll f(ll x, ll y)
{
    ll tx = x + me; ll ty = y + me;
    ll lo = lower_bound(ve.begin(), ve.end(), ME*tx + ty) - ve.begin();
    return lo;
}
priority_queue <ll, vector <ll>, greater <ll>> pq[500005];
ll nj_D[500005];
int main(void)
{
    ll n, m; scanf("%lld%lld",&n,&m);
    for(ll i=0;i<n;i++)
    {
        scanf("%lld%lld%lld",&(b[i].x), &(b[i].y), &(b[i].hp));
        ll g = gcd(abs(b[i].x), abs(b[i].y));
        b[i].x /= g; b[i].y /= g;
        add(b[i].x, b[i].y);
    }
    for(ll i=0;i<m;i++)
    {
        scanf("%lld%lld%lld",&(a[i].x),&(a[i].y), &(a[i].d));
        ll g = gcd(abs(a[i].x), abs(a[i].y));
        a[i].x /= g; a[i].y /= g;
        add(a[i].x, a[i].y);
    }
    sort(ve.begin(), ve.end());
    ve.erase(unique(ve.begin(), ve.end()), ve.end());
    
    for(ll i=0;i<n;i++)
    {
        ll lo = f(b[i].x, b[i].y);
        pq[lo].push(b[i].hp);
    }
    ll tot = n;
    for(ll i=0;i<m;i++)
    {
        ll lo = f(a[i].x, a[i].y);
        nj_D[lo] += a[i].d;
        while(!pq[lo].empty()){
            ll curi = pq[lo].top();
            if(curi <= nj_D[lo])
            {
                pq[lo].pop();
                tot--;
            }
            else
                break;
        }
        printf("%lld\n",tot);
    }
}
#include <stdio.h>
#include <string.h>
#include <string>
#include <vector>
#include <algorithm>
using namespace std;
typedef long long ll;
char str[100];
ll fe[128];
int co[200000];
vector <ll> v;
void init()
{
    for(ll i=0;i<26;i++)
        fe[i + 'a'] = i+1;
    for(ll i=0;i<10;i++)
        fe[i + '0'] = i+27;
}
ll f(const char *str)
{
    ll reti = 0;
    for(ll i=0; str[i]; i++){
        reti = 37*reti + fe[str[i]];
    }
    return reti;
}
ll oioi(ll x)
{
    ll lo = lower_bound(v.begin(), v.end(), x) - v.begin();
    if(lo == (ll)v.size())
        return 0;
    if(v[lo] != x)
        return 0;
    if(co[lo] == 1)
        return 0;
    co[lo] = 1;
    return 1;
}
int main(void)
{
    ll n,m; scanf("%lld %lld\n",&n, &m); init();
    for(int i=0;i<n;i++)
    {
        scanf("%s\n",str); v.push_back(f(str));
    }
    sort(v.begin(), v.end());
    v.erase(unique(v.begin(), v.end()), v.end());
    ll ans = n;
    for(int i=0;i<m;i++)
    {
        scanf("%s\n",str); //strtok
        const char *t = strtok(str, ",");
        while(t != NULL)
        {
            ans = ans - oioi(f(t));
            t = strtok(NULL, ",");
        }
        printf("%lld\n",ans);
    }
    return 0;
}
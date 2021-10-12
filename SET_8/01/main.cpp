#include<bits/stdc++.h>

using namespace std;
typedef long long ll;

vector<ll> P;
const ll MAXN = 100000;
bool prime[MAXN];
int K, M, ans;

void init() {
    for(ll i = 2; i < MAXN; i++) prime[i] = true;
    for(ll i = 2; i < MAXN; i++) {
        if(prime[i] == false) continue;
        P.push_back(i);
        for(ll j = i * i; j < MAXN; j += i) prime[j] = false;
    }
}

bool check_first(ll x) {
    for(ll p : P) {
        if(x - p < 0) break;
        if(x - p != p && prime[x - p]) return true;
    }
    return false;
}

bool check_second(ll x) {
    while(x % M == 0) x /= M;
    for(ll p : P) {
        if(1LL * p * p > x) break;
        if(x % p) continue;
        if(prime[x / p]) return true;
    }
    return false;
}

void dfs(int cnt, int bit, int x) {
    if(cnt == K) {
        if(check_first(x) && check_second(x)) ans ++;
        return;
    }
    for(int i = !cnt; i < 10; i++) {
        if(bit & (1 << i)) continue;
        dfs(cnt + 1, bit | (1 << i), x * 10 + i);
    }
}

int main(){
    ios::sync_with_stdio(false);
    cin.tie(0);

    init();
    cin >> K >> M;
    dfs(0, 0, 0);
    cout << ans;

    return 0;
}

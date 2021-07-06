//#include<bits/stdc++.h>
#include<iostream>
#include<vector>

using namespace std;

typedef long long ll;

ll GCD(ll a, ll b) {
    if(b == 0)return a;
    return GCD(b, a % b);
}

ll LCM(ll a, ll b) {
    return a / GCD(a, b) * b;
}

bool isPrime(ll X) {
    for(ll i = 2; i * i <= X; i++) {
        if(X % i == 0)return false;
    }
    return true;
}

int main(){
    ios::sync_with_stdio(false);
    cin.tie(0);

    int N; cin >> N;
    ll ans = 1;
    for(int i = 0; i < N; i++) {
        ll X; cin >> X;
        if(isPrime(X)) ans = LCM(ans, X);
    }

    if(ans == 1) ans = -1;
    cout << ans;

    return 0;
}

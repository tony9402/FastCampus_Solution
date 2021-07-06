#include<iostream>
#include<vector>

using namespace std;
typedef long long ll;

ll GCD(ll a, ll b) {
    if(b == 0) return a;
    return GCD(b, a % b);
}

vector<ll> V;

int main(){
    ios::sync_with_stdio(false);
    cin.tie(0);

    ll answer = 0, cnt = 0;

    // Input
    int N; cin >> N;
    for(int i = 0; i < N; i++) {
        ll cur; cin >> cur;
        V.push_back(cur);
    }
    ll X; cin >> X;

    for(int i = 0; i < N; i++) {
        ll cur = V[i];
        if(GCD(cur, X) == 1) {
            answer += cur;
            cnt ++;
        }
    }
    cout.precision(6);
    cout << fixed << (double)answer / cnt;

    return 0;
}

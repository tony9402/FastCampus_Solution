#include<bits/stdc++.h>

using namespace std;
typedef long long ll;

ll V[5005];

int main(){
    ios::sync_with_stdio(0);
    cin.tie(0);

    int N; cin >> N;
    for(int i = 0; i < N; i++) cin >> V[i];
    int L = 1, R = 1000000;
    while(L <= R) {
        int mid = (L + R) / 2;
        vector<ll> DP(N + 1);
        DP[0] = 1;
        for(int i = 0; i < N - 1; i++) {
            for(int j = 1; j <= mid; j++) {
                if(i + j >= N) break;
                ll x = j * (1LL + abs(V[i] - V[i + j]));
                if(x <= mid) DP[i + j] |= DP[i];
            }
        }
        if(DP[N - 1]) R = mid - 1;
        else L = mid + 1;
    }
    cout << L;

    return 0;
}

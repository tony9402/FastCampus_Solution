#include<bits/stdc++.h>

using namespace std;

int V[5005], DP[5005];

int main(){
    ios::sync_with_stdio(false);
    cin.tie(0);

    int N, K; cin >> N >> K;
    for(int i = 0; i < N; i++) cin >> V[i];
    DP[0] = 1;
    for(int i = 1; i < N; i++) {
        for(int j = 1; i - j >= 0 && j <= K; j++) {
            int x = j * (1 + abs(V[i] - V[i - j]));
            if(x <= K) DP[i] |= DP[i - j];
        }
    }
    if(DP[N - 1]) cout << "YES";
    else cout << "NO";

    return 0;
}

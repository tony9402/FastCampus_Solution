#include<iostream>
#include<vector>
#include<algorithm>

using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(0);

    int N, K; cin >> N >> K;
    vector<vector<int>> DP(K + 1, vector<int>(N + 1));

    for(int i = 1; i <= N; ++i) {
        int x; cin >> x;
        if(x % 2 == 0) DP[0][i] = DP[0][i - 1] + 1;
        for(int j = 1; j <= K; j++) {
            if(x % 2 == 0) DP[j][i] = DP[j][i - 1] + 1;
            else DP[j][i] = DP[j - 1][i - 1];
        }
    }
    cout << *max_element(DP[K].begin() + 1, DP[K].end());

    return 0;
}
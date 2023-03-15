#include<iostream>
#include<vector>

using namespace std;

vector<pair<int, int>> G;

int solve(int cur) {
    if(G[cur].second != -1) return solve(G[cur].second) + 1;
    return 0;
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(0);

    int N; cin >> N;
    G.resize(N + 1);
    for(int i=0;i<N;i++) {
        int cur, left, right; cin >> cur >> left >> right;
        G[cur].first = left;
        G[cur].second = right;
    }
    cout << 2 * (N - 1) - solve(1);

    return 0;
}
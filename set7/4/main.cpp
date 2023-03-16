#include<iostream>
#include<vector>
#include<algorithm>

using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(0);

    int N; cin >> N;
    vector<int> V(N);
    for(int i= 0; i < N; ++i) cin >> V[i];

    int L = 0, R = N - 1;
    int ans = 0;
    while(L < R) {
        ans = max(ans, (R - L - 1) * min(V[L], V[R]));
        if(V[L] <= V[R]) ++L;
        else --R;
    }
    cout << ans;

    return 0;
}
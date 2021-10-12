#include<bits/stdc++.h>

using namespace std;

const int MAXN = 100005;
int arr[MAXN], L[MAXN], R[MAXN];

int main(){
    ios::sync_with_stdio(false);
    cin.tie(0);
    int N; cin >> N;
    for(int i = 1; i <= N; i++) {
        cin >> arr[i];
    }
    for(int i = 1; i <= N; i++) L[i] = max(L[i - 1], arr[i]);
    for(int i = N; i  > 0; i--) R[i] = max(R[i + 1], arr[i]);
    reverse(R + 1, R + N + 1);
    int ans = 0;
    for(int i = 1; i <= N; i++) {
        int Lidx = lower_bound(L + 1, L + i, arr[i]) - L;
        int Ridx = lower_bound(R + 1, R + N - 1 - i, arr[i]) - R;
        Ridx = N + 1 - Ridx;
        int cur = max({ (i - 2) * min(arr[i], arr[1]), (N - i - 1) * min(arr[i], arr[N]), (i - Lidx - 1) * arr[i], (Ridx - i - 1) * arr[i] });
        ans = max(cur, ans);
    }
    cout << ans;
    return 0;
}

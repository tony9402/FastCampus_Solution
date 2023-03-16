#include<iostream>
#include<vector>

using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(0);

    int N, K; cin >> N >> K;
    vector<int> idx(N + 1), arr(N + 1), tmp(N + 1);
    for(int i = 1; i <= N; i++)cin >> arr[i];
    for(int i = 1; i <= N; i++)cin >> idx[i];
    for(int j = 1; j <= K; j++) {
        for(int i = 1; i <= N; i++) tmp[idx[i]] = arr[i];
        for(int i = 1; i <= N; i++) arr[i] = tmp[i];
    }
    for(int i = 1; i <= N; i++) cout << arr[i] << ' ';

    return 0;
}
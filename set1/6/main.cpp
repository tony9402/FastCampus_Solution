#include<iostream>
#include<climits>

using namespace std;

int arr[1003][1003];
int DP[2][1003][1003];

int main(){
    ios::sync_with_stdio(false);
    cin.tie(0);

    int N, M; cin >> N >> M;
    for(int i = 1; i <= N; ++i) {
        for(int j = 1; j <= M; ++j) {
            cin >> arr[i][j];
        }
    }
    for(int i = 0; i <= N + 1; ++i) {
        for(int j = 0; j <= M + 1; ++j) {
            DP[0][i][j] = DP[1][i][j] = INT_MIN / 2;
        }
    }

    DP[0][N][1] = arr[N][1];
    DP[1][N][M] = arr[N][M];
    for(int i = N; i >= 1; --i) {
        for(int j = 1; j <= M; ++j) {
            if(i == N && j == 1) continue;
            DP[0][i][j] = max(DP[0][i + 1][j], DP[0][i][j - 1]) + arr[i][j];
        }
    }
    for(int i = N; i >= 1; --i) {
        for(int j = M; j >= 1; --j) {
            if(i == N && j == M) continue;
            DP[1][i][j] = max(DP[1][i + 1][j], DP[1][i][j + 1]) + arr[i][j];
        }
    }

    int ans = INT_MIN / 2;
    for(int i = 1; i <= N; ++i) {
        for(int j = 1; j <= M; ++j) {
            ans = max(ans, DP[0][i][j] + DP[1][i][j]);
        }
    }
    cout << ans;

    return 0;
}
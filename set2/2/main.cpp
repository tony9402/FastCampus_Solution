#include<iostream>
#include<queue>

using namespace std;

const int dy[] = {-1,1,0,0,-1,-1,1,1};
const int dx[] = {0,0,-1,1,-1,1,-1,1};
int arr[1001][1001];

int main(){
    ios::sync_with_stdio(false);
    cin.tie(0);

    int N, M; cin >> N >> M;
    for (int i = 0; i < N; ++i) {
        for (int j = 0; j < M; ++j) {
            for (int k = 0; k < 3; ++k) {
                int x;cin >> x;
                arr[i][j] += x;
            }
        }
    }
    int T; cin >> T;
    for (int i = 0; i < N; ++i) {
        for (int j = 0; j < M; ++j) {
            if(arr[i][j] >= T * 3) arr[i][j] = 1;
            else arr[i][j] = 0;
        }
    }
    int answer = 0;
    for (int i = 0; i < N; ++i) {
        for (int j = 0; j < M; ++j) {
            if(arr[i][j] == 1) {
                queue<pair<int, int>> q;
                q.emplace(i, j);
                arr[i][j] = 0;
                answer ++;
                while(!q.empty()) {
                    auto [y, x] = q.front(); q.pop();
                    for (int k = 0; k < 4; ++k) {
                        int qy = y + dy[k];
                        int qx = x + dx[k];
                        if(0 > qy || qy >= N || 0 > qx || qx >= M)continue;
                        if(arr[qy][qx] == 0)continue;
                        arr[qy][qx] = 0;
                        q.emplace(qy, qx);
                    }
                }
            }
        }
    }
    cout << answer;

    return 0;
}
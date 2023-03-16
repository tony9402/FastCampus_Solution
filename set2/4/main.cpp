#include<iostream>
#include<vector>
#include<climits>
#include<algorithm>

using namespace std;

const int INF = 0x3f3f3f3f;

int main(){
    ios::sync_with_stdio(false);
    cin.tie(0);

    int N, M; cin >> N >> M;

    vector<vector<int>> arr(N + 1, vector<int>(N + 1));
    const int INF = INT_MAX / 2;
    vector<vector<int>> dist(N + 1, vector<int>(N + 1, INF));
    for(int i = 0; i <= N; ++i) dist[i][i] = 0;

    for(int i = 0; i < M; ++i) {
        int u, v, w; cin >> u >> v >> w;
        dist[u][v] = w;
    }

    for(int k = 1; k <= N; ++k) {
        for(int i = 1; i <= N; ++i) {
            for(int j = 1; j <= N; ++j) {
                if(dist[i][j] > dist[i][k] + dist[k][j]) {
                    dist[i][j] = dist[i][k] + dist[k][j];
                }
            }
        }
    }

    int K; cin >> K;
    vector<int> friends(K);
    for(int i = 0; i < K; ++i) cin >> friends[i];
    
    int mx = INF;
    vector<int> answer;
    for(int i = 1; i <= N; ++i) {
        int cur = 0;
        for(int j = 0; j < K; ++j) cur = max(cur, dist[friends[j]][i] + dist[i][friends[j]]);
        if(cur >= INF) cur = INF;
        if(mx > cur) {
            answer.clear();
            answer.push_back(i);
            mx = cur;
        }
        else if(mx == cur) {
            answer.push_back(i);
        }
    }
    for(const auto &x: answer) cout << x << ' ';

    return 0;
}
#include<iostream>
#include<vector>
#include<queue>

using namespace std;

vector<pair<int, int>> G[100001];

int main() {
    ios::sync_with_stdio(false);
    cin.tie(0); 

    int N; cin >> N;
    int A, B, C; cin >> A >> B >> C;
    int M; cin >> M;
    for(int i = 0; i < M; ++i) {
        int u, v, w; cin >> u >> v >> w;
        G[u].emplace_back(v, w);
        G[v].emplace_back(u, w);
    }
    
    priority_queue<pair<int, int>> pq;
    const int INF = 1 << 30;
    vector<int> dist(N + 1, INF);

    for(int x : {A, B, C}) {
        dist[x] = 0;
        pq.emplace(-dist[x], x);
    }
    while(!pq.empty()) {
        auto [d, cur] = pq.top(); pq.pop();
        if(dist[cur] != -d) continue;
        for(auto [nxt, W]: G[cur]) {
            if(dist[nxt] > dist[cur] + W) {
                dist[nxt] = dist[cur] + W;
                pq.emplace(-dist[nxt], nxt);
            }
        }
    }
    
    int mx = 0, ans = 0;
    for(int i = 1; i <= N; ++i) {
        if(mx < dist[i]) {
            mx = dist[i];
            ans = i;
        }
    }
    cout << ans;
    
    return 0;
}
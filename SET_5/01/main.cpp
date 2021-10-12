#include<bits/stdc++.h>

using namespace std;

const int INF = 0x3f3f3f3f;
vector<vector<pair<int, int>>> graph;
vector<vector<int>> dist;
int N, M;

void dijkstra(int idx, int start) {
    priority_queue<pair<int, int>> pq;
    dist[idx][start] = 0;
    pq.emplace(0, start);
    while(!pq.empty()) {
        auto [cost, cur] = pq.top(); pq.pop();
        if(dist[idx][cur] != -cost) continue;
        for(auto [nxt, nc]: graph[cur]) {
            if(dist[idx][nxt] > dist[idx][cur] + nc) {
                dist[idx][nxt] = dist[idx][cur] + nc;
                pq.emplace(-dist[idx][nxt], nxt);
            }
        }
    }
}

int main(){
    ios::sync_with_stdio(false);
    cin.tie(0);

    cin >> N;
    graph.resize(N);
    dist = vector<vector<int>>(3, vector<int>(N, INF));
    int A, B, C; cin >> A >> B >> C;
    --A;--B;--C;
    cin >> M;
    for(int i = 0; i < M; i++) {
        int a, b, c; cin >> a >> b >> c;
        graph[--a].emplace_back(--b, c);
        graph[b].emplace_back(a, c);
    }
    dijkstra(0, A); dijkstra(1, B); dijkstra(2, C);
    int mx = 0, answer = 0;
    for(int i = 0; i < N; i++) {
        int d = min({ dist[0][i], dist[1][i], dist[2][i] });
        if(mx < d) {
            mx = d;
            answer = i;
        }
    }
    cout << answer + 1;

    return 0;
}

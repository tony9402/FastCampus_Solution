#include<iostream>
#include<vector>

using namespace std;

vector<vector<int>> graph;
vector<int> visited;

void dfs(int cur) {
    visited[cur] = 1;
    for(int nxt : graph[cur]) if(visited[nxt] == 0) dfs(nxt);
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(0);

    int N, M; cin >> N >> M;
    graph.resize(N + 1);
    visited.resize(N + 1);
    for(int i = 0; i < M; ++i) {
        int u, v; cin >> u >> v;
        graph[v].push_back(u);
    }

    int X; cin >> X;
    dfs(X);

    int ans = 0;
    for(int i = 1; i <= N; ++i) {
        if(i != X && visited[i] == 1) ++ ans;
    }
    cout << ans;

    return 0;
}
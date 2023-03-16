#include<iostream>
#include<queue>
#include<vector>
#include<algorithm>

using namespace std;

vector<vector<int>> graph;
vector<int> dist;
vector<int> used;
vector<int> pre;

int BFS(int S, int E) {
    queue<int> Q;
    Q.push(S);
    used[S] = 0;
    while(!Q.empty()) {
        int cur = Q.front(); Q.pop();
        for(int nxt : graph[cur]) {
            if(used[nxt] != -1) continue;
            used[nxt] = used[cur] + 1;
            Q.push(nxt);
            pre[nxt] = cur;
        }
    }
    return used[E];
}

void chk(int S, int E){
    if(S == pre[E]) return;
    used[pre[E]] = 0;
    chk(S, pre[E]);
}

int main(){
    ios::sync_with_stdio(false);
    cin.tie(0);

    int N, M; cin >> N >> M;
    graph.resize(N);
    pre.resize(N, -1);
    used.resize(N, -1);
    for(int i = 0; i < M; i++) {
        int a, b; cin >> a >> b;
        graph[--a].emplace_back(--b);
        graph[b].emplace_back(a);
    }
    for(int i = 0; i < N; i++) sort(graph[i].begin(), graph[i].end());
    int S, E; cin >> S >> E;
    S--; E--;
    int dist1 = BFS(S, E);
    used = vector<int>(N, -1);
    chk(S, E);
    int dist2 = BFS(E, S);
    if(dist1 == -1 || dist2 == -1) cout << -1;
    else cout << dist1 + dist2;

    return 0;
}
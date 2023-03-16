#include<iostream>
#include<queue>
#include<climits>
#include<algorithm>

using namespace std;

int N, M, K;
int lastNode;
vector<long long> V; 
vector<int> indegree;
vector<vector<int>> graph;

long long ans = LLONG_MAX;

void getLastNode() {
    vector<int> ind = indegree;
    queue<int> Q;
    Q.emplace(0);

    while(!Q.empty()) {
        int cur = Q.front(); Q.pop();
        lastNode = cur;
        for(auto &nxt: graph[cur]) if(--ind[nxt] == 0) Q.push(nxt);
    }
}

long long go() {
    vector<int> ind = indegree;
    vector<long long> DP(N);
    queue<int> Q;
    Q.emplace(0);

    DP[0] = V[0];
    while(!Q.empty()) {
        int cur = Q.front(); Q.pop();
        for(auto &nxt: graph[cur]) {
            if(--ind[nxt] == 0) Q.push(nxt);
            DP[nxt] = max(DP[nxt], DP[cur] + V[nxt]);
        }
    }
    return *max_element(DP.begin(), DP.end());
}

void select(int idx, int cnt) {
    if(cnt == K) {
        ans = min(ans, go());
        return;
    }

    for(int i = idx; i < N; i++) {
        if(i == lastNode) continue;
        int tmp = V[i];
        V[i] = 0;
        select(i + 1, cnt + 1);
        V[i] = tmp;
    }
}

int main(){
    ios::sync_with_stdio(false);
    cin.tie(0);

    cin >> N >> M >> K;
    V.resize(N);
    indegree.resize(N);
    graph.resize(N);
    for(int i = 0; i < N; i++) {
        cin >> V[i];
    }
    for(int i = 0; i < M; i++) {
        int a, b; cin >> a >> b;
        graph[--a].push_back(--b);
        indegree[b] ++;
    }

    getLastNode();
    select(1, 0);
    cout << ans;

    return 0;
}
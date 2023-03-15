#include<iostream>
#include<vector>
#include<tuple>
#include<algorithm>

using namespace std;

vector<int> par;
int find(int x) {
    if(par[x] < 0) return x;
    return par[x] = find(par[x]);
}

bool merge(int u, int v) {
    u = find(u); v = find(v);
    if(u == v) return false;
    par[v] = u;
    return true;
}

int main(){
    ios::sync_with_stdio(false);
    cin.tie(0);

    int N, M; cin >> N >> M;
    par = vector<int>(N + 1, -1);

    long long ans = 0;
    vector<tuple<int, int, int>> edges;
    for(int i = 0; i < M; ++i) {
        int u, v, w; cin >> u >> v >> w;
        edges.emplace_back(w, u, v);
        ans += w;
    }
    sort(edges.begin(), edges.end());

    for(const auto [w, u, v]: edges) if(merge(u, v)) ans -= w;

    for(int i = 2; i <= N; ++i) if(find(1) != find(i)) ans = -1;
    cout << ans;

    return 0;
}
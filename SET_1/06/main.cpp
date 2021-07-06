// #include<bits/stdc++.h>
#include<iostream>
#include<vector>
#include<algorithm>

using namespace std;
typedef long long ll;

vector<tuple<int, int, int>> edges;
vector<int> uf;

int find(int x) {
    if(uf[x] < 0) return x;
    return uf[x] = find(uf[x]);
}

bool merge(int a, int b) {
    a = find(a);
    b = find(b);
    if(a == b)return false;
    uf[b] = a;
    return true;
}

bool same(int a, int b) {
    return find(a) == find(b);
}

int main(){
    ios::sync_with_stdio(false);
    cin.tie(0);

    int N, M; cin >> N >> M;
    uf.resize(N, -1);

    ll ans = 0;
    for(int i=0;i<M;i++){
        int a, b, c; cin >> a >> b >> c;
        ans += c;
        edges.emplace_back(c, --a, --b);
    }
    sort(edges.begin(), edges.end());
    
    for(auto &[c, a, b]: edges) if(merge(a, b))ans -= c;
    for(int i=1;i<N;i++) if(!same(0, i)) ans = -1;
    cout << ans;

    return 0;
}

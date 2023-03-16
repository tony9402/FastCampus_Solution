#include<iostream>
#include<queue>
#include<tuple>
#include<vector>
#include<algorithm>

using namespace std;

vector<tuple<long long, long long, long long, int>> circle;
vector<vector<int>> tree;

pair<int, int> BFS(int S) {
    pair<int, int> ret;
    queue<pair<int, int>> Q;
    vector<int> used(tree.size());
    Q.emplace(S, 0);
    used[S] = 1;
    while(!Q.empty()) {
        auto [cur, d] = Q.front(); Q.pop();
        if(ret.first < d) {
            ret.first = d;
            ret.second = cur;
        }
        for(int nxt : tree[cur]) {
            if(used[nxt]) continue;
            used[nxt] = true;
            Q.emplace(nxt, d + 1);
        }
    }
    return ret;
}

int main(){
    ios::sync_with_stdio(false);
    cin.tie(0);

    int N; cin >> N;
    tree.resize(N + 1);
    for(int i = 0; i < N; i++) {
        int x, y, r; cin >> x >> y >> r;
        circle.emplace_back(r, x, y, i + 1);
    }
    sort(circle.begin(), circle.end());
    circle.emplace_back(1000000000, 0, 0, 0);
    for(int i = 0; i <= N; i++) {
        for(int j = i + 1; j <= N; j++) {
            auto [ar, ax, ay, aidx] = circle[i];
            auto [br, bx, by, bidx] = circle[j];
            long long mr = ar - br;
            long long d  = (ax - bx) * (ax - bx) + (ay - by) * (ay - by);
            if((d == 0 && mr != 0) || d < mr * mr) {
                tree[aidx].push_back(bidx);
                tree[bidx].push_back(aidx);
                break;
            }
        }
    }

    auto cur = BFS(0);
    auto cur2 = BFS(cur.second);
    cout << cur2.first;

    return 0;
}